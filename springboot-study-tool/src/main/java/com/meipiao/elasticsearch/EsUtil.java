package com.meipiao.elasticsearch;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.*;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import io.searchbox.strings.StringUtils;
import org.anyline.entity.DataRow;
import org.anyline.entity.DataSet;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/5/9 15:24
 */
public class EsUtil {
    private static final Logger log = LoggerFactory.getLogger(EsUtil.class);
    JestClient jestClient = ApplicationContextUtil.getBean(JestClient.class);
    private final String INDEX_TYPE = "_doc";

    private EsUtil() {
    }

    public static EsUtil getEsUtil() {
        return EsUtil.Builder.esUtil;
    }

    public void createIndex(String indexName) {
        try {
            boolean indexExists = this.jestClient.execute((new io.searchbox.indices.IndicesExists.Builder(indexName)).build()).isSucceeded();
            if (indexExists) {
                this.jestClient.execute((new io.searchbox.indices.DeleteIndex.Builder(indexName)).build());
            }

            this.jestClient.execute((new io.searchbox.indices.CreateIndex.Builder(indexName)).build());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public boolean deleteIndex(String indexName) {
        boolean bool = false;

        try {
            JestResult jr = this.jestClient.execute((new io.searchbox.indices.DeleteIndex.Builder(indexName)).build());
            bool = jr.isSucceeded();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return bool;
    }

    public String getIndexMapping(String indexName) {
        GetMapping getMapping = (new GetMapping.Builder()).addIndex(indexName).addType("_doc").build();
        JestResult jr = null;
        String string = "";

        try {
            jr = this.jestClient.execute(getMapping);
            string = jr.getJsonString();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return string;
    }

    public boolean createIndexMapping(String indexName, String source) {
        PutMapping putMapping = (new io.searchbox.indices.mapping.PutMapping.Builder(indexName, "_doc", source)).build();
        JestResult jr = null;
        boolean bool = false;

        try {
            jr = this.jestClient.execute(putMapping);
            bool = jr.isSucceeded();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return bool;
    }

    public DataRow query(String indexName, String id) {
        Get get = (new Get.Builder(indexName, id)).type("_doc").build();
        DataRow row = null;

        try {
            JestResult result = this.jestClient.execute(get);
            row = DataRow.parseJson(result.getSourceAsString());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return row;
    }

    public JsonObject queryForJson(String indexName, String id) {
        Get get = (new Get.Builder(indexName, id)).type("_doc").build();
        JsonObject jsonObject = null;

        try {
            JestResult result = this.jestClient.execute(get);
            jsonObject = result.getJsonObject().get("_source").getAsJsonObject();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return jsonObject;
    }

    public boolean deleteIndexDoc(String indexName, String id) {
        boolean bool = false;

        try {
            DocumentResult dr = this.jestClient.execute((new Delete.Builder(id)).index(indexName).type("_doc").build());
            bool = dr.isSucceeded();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return bool;
    }

    public boolean deleteIndexDocBySearch(String indexName, String search) {
        boolean bool = false;

        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.queryStringQuery(search).defaultOperator(Operator.AND));
            log.info(sourceBuilder.toString());
            Search sb = (new Search.Builder(sourceBuilder.toString())).addIndex(indexName).addType("_doc").build();
            log.info(sb.toString());
            DocumentResult dr = this.jestClient.execute((new Delete.Builder(sb.toString())).index(indexName).type("_doc").build());
            bool = dr.isSucceeded();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return bool;
    }

    public boolean insert(String indexName, DataRow row) {
        return this.save(indexName, null, row);
    }

    public boolean save(String indexName, DataRow row) {
        return this.save(indexName, row.getId(), row);
    }

    public boolean save(String indexName, String id, DataRow row) {
        Index index = (new Index.Builder(row)).index(indexName).type("_doc").id(id).build();
        boolean bool = false;

        try {
            JestResult jr = this.jestClient.execute(index);
            bool = jr.isSucceeded();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return bool;
    }

    public boolean insertAsync(String indexName, DataRow row) {
        return this.saveAsync(indexName, null, row);
    }

    public boolean saveAsync(String indexName, DataRow row) {
        return this.saveAsync(indexName, row.getId(), row);
    }

    public boolean saveAsync(String indexName, String id, DataRow row) {
        Index index = (new Index.Builder(row)).index(indexName).type("_doc").id(id).build();
        this.jestClient.executeAsync(index, new JestResultHandler<JestResult>() {
            public void completed(JestResult result) {
            }

            public void failed(Exception e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    public boolean insert(String indexName, DataSet dataSet) {
        io.searchbox.core.Bulk.Builder bulk = (new io.searchbox.core.Bulk.Builder()).defaultIndex(indexName).defaultType("_doc");
        Iterator br = dataSet.iterator();

        while (br.hasNext()) {
            DataRow row = (DataRow) br.next();
            Index index = (new io.searchbox.core.Index.Builder(row)).build();
            bulk.addAction(index);
        }

        br = null;
        boolean boll = false;

        try {
            BulkResult br2 = this.jestClient.execute(bulk.build());
            boll = br2.isSucceeded();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return boll;
    }

    public boolean save(String indexName, DataSet dataSet) {
        List<Index> actions = new ArrayList();

        assert dataSet != null;

        dataSet.forEach((item) -> {
            actions.add((new Index.Builder(item)).id(item.getId()).build());
        });
        Bulk bulk = (new io.searchbox.core.Bulk.Builder()).defaultIndex(indexName).defaultType("_doc").addAction(actions).build();
        boolean bool = false;

        try {
            JestResult jr = this.jestClient.execute(bulk);
            bool = jr.isSucceeded();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return bool;
    }

    public void facetsSearch(String indexName, String field, String facetsField) {
        SumAggregationBuilder sumBuilder = AggregationBuilders.sum(field).field(facetsField);
        Search sb = (new Search.Builder(sumBuilder.toString())).addIndex(indexName).addType("_doc").build();
        SearchResult var6 = null;

        try {
            var6 = this.jestClient.execute(sb);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public Double count(String indexName, String search) {
        Count count = (new Count.Builder()).addIndex(indexName).addType("_doc").query(search).build();
        CountResult cr = null;
        Double db = 0.0D;

        try {
            cr = this.jestClient.execute(count);
            db = cr.getCount();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return db;
    }

    public String getIndexDocIds(String indexName, String search) {
        indexName = StringUtils.isBlank(indexName) ? "*" : indexName;
        search = StringUtils.isBlank(search) ? "*" : search;
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.queryStringQuery(search).defaultOperator(Operator.AND));
        sourceBuilder.sort((new ScoreSortBuilder()).order(SortOrder.DESC));
        Search sb = (new Search.Builder(sourceBuilder.toString())).addIndex(indexName).addType("_doc").build();
        SearchResult result = null;

        try {
            result = this.jestClient.execute(sb);
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        StringBuilder sb1 = new StringBuilder();
        if (result != null && result.isSucceeded()) {
            long totalCount = result.getTotal();
            if (totalCount > 0L) {
                JsonArray jsonArray = result.getJsonObject().get("hits").getAsJsonObject().get("hits").getAsJsonArray();

                for (int i = 0; i < jsonArray.size(); ++i) {
                    String id = jsonArray.get(i).getAsJsonObject().get("_id").toString();
                    sb1.append(id + ",");
                }
            }
        }

        return StringUtils.isNotBlank(sb1.toString()) ? sb1.toString().substring(0, sb1.toString().length() - 1) : "";
    }

    public DataSet baseSearch(String indexName, String search) throws Exception {
        return this.baseSearch(DataRow.KEY_CASE.SRC, indexName, search);
    }

    public DataSet baseSearch(DataRow.KEY_CASE key_case, String indexName, String search) throws Exception {
        indexName = StringUtils.isBlank(indexName) ? "*" : indexName;
        search = StringUtils.isBlank(search) ? "{\n  \"query\": {\n    \"match_all\": {}\n  }\n}" : search;
        Search sb = (new Search.Builder(search)).addIndex(indexName).addType("_doc").build();
        log.info("查询条件:{}", search);
        SearchResult result = null;

        try {
            result = this.jestClient.execute(sb);
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        log.info("查询结果:{}", result);
        DataSet set = new DataSet();
        if (result != null && result.isSucceeded()) {
            DataRow resultRow = DataRow.parseJson(key_case, result.getJsonString());
            DataRow hitsRow = resultRow.getRow("hits");
            long totalCount = hitsRow.getRow("total").getLong("value");
            if (totalCount > 0L) {
                DataSet hitsSet = hitsRow.getSet("hits");

                for (int i = 0; i < hitsSet.size(); ++i) {
                    DataRow row = hitsSet.getRow(i).getRow("_source");
                    set.add(row);
                }
            }
        }

        return set;
    }

    public Page querys(String indexName) throws Exception {
        return this.querys(indexName, null);
    }

    public Page querys(String indexName, String search) throws Exception {
        return this.querys(indexName, search, 1, 10);
    }

    public Page querys(String indexName, Integer pageNo, Integer pageSize) throws Exception {
        return this.querys(indexName, null, pageNo, pageSize);
    }

    public Page querys(String indexName, String search, Integer pageNo, Integer pageSize) throws Exception {
        indexName = StringUtils.isBlank(indexName) ? "*" : indexName;
        search = StringUtils.isBlank(search) ? "*" : search;
        pageNo = pageNo != null && pageNo >= 1 ? pageNo : 1;
        pageSize = pageSize == null ? 20 : pageSize;
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.queryStringQuery(search).defaultOperator(Operator.AND));
        sourceBuilder.from(startIndex).size(pageSize);
        sourceBuilder.sort((new ScoreSortBuilder()).order(SortOrder.DESC));
        Search sb = (new Search.Builder(sourceBuilder.toString())).addIndex(indexName).addType("_doc").build();
        SearchResult result = null;

        try {
            result = this.jestClient.execute(sb);
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return this.getPage(pageNo, pageSize, result);
    }

    private Page getPage(Integer pageNo, Integer pageSize, SearchResult result) throws Exception {
        long totalCount = 0L;
        DataSet set = new DataSet();
        if (result != null && result.isSucceeded()) {
            DataRow resultRow = DataRow.parseJson(DataRow.KEY_CASE.UPPER, result.getJsonString());
            DataRow hitsRow = resultRow.getRow("HITS");
            totalCount = hitsRow.getRow("TOTAL").getLong("VALUE");
            if (totalCount > 0L) {
                DataSet hitsSet = hitsRow.getSet("HITS");

                for (int i = 0; i < hitsSet.size(); ++i) {
                    DataRow row = hitsSet.getRow(i).getRow("_source");
                    set.add(row);
                }
            }
        }

        return new Page(pageNo, pageSize, totalCount, set);
    }

    public Page querys(String indexName, String search, Integer pageNo, Integer pageSize, String sortField, String sortRule) throws Exception {
        indexName = StringUtils.isBlank(indexName) ? "*" : indexName;
        search = StringUtils.isBlank(search) ? "*" : search;
        pageNo = pageNo != null && pageNo >= 1 ? pageNo : 1;
        pageSize = pageSize == null ? 20 : pageSize;
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.queryStringQuery(search).defaultOperator(Operator.AND));
        sourceBuilder.from(startIndex).size(pageSize);
        if (StringUtils.isNotBlank(sortField)) {
            String[] sf = sortField.split(",");
            String[] sr = sortRule.split(",");
            new StringBuilder();

            for (int i = 0; i < sf.length; ++i) {
                if ("ASC".equals(sr[i])) {
                    sourceBuilder.sort((new FieldSortBuilder(sf[i])).order(SortOrder.ASC));
                } else {
                    sourceBuilder.sort((new FieldSortBuilder(sf[i])).order(SortOrder.DESC));
                }
            }
        } else {
            sourceBuilder.sort((new ScoreSortBuilder()).order(SortOrder.DESC));
        }

        Search sb = (new Search.Builder(sourceBuilder.toString())).addIndex(indexName).addType("_doc").build();
        SearchResult result = null;

        try {
            result = this.jestClient.execute(sb);
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return this.getPage(pageNo, pageSize, result);
    }

    public boolean updateEsData(String indexName, DataRow row, String[] fields, String[] index) {
        DataRow hmap = new DataRow();

        for (int j = 0; j < fields.length; ++j) {
            if (!fields[j].contains(":")) {
                hmap.put(index[j], row.get(fields[j]));
            } else {
                String[] sa = fields[j].split(":");
                StringBuffer sb = new StringBuffer();
                String[] var9 = sa;
                int var10 = sa.length;

                for (int var11 = 0; var11 < var10; ++var11) {
                    String st = var9[var11];
                    String var10001 = row.getString(st);
                    sb.append(var10001 + ";");
                }

                hmap.put(index[j], sb.toString());
            }
        }

        if (indexName.equals("car_model") && null != row.get("style_year")) {
            hmap.put("style_year_suffix", row.getString("style_year").substring(2));
        }

        boolean bool = this.save(indexName, "_doc", hmap);
        return bool;
    }

    private static class Builder {
        private static EsUtil esUtil = new EsUtil();

        private Builder() {
        }
    }
}

