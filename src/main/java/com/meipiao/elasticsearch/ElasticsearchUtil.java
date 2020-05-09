package com.meipiao.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/5/8 16:42
 * @Des: es查询
 */
@Component
@Slf4j
public class ElasticsearchUtil {
    //es查询(客户端，开始时间，结束时间，查询索引名称,账号id)
    private static List<String> queryByTime(JestClient jestClient, String startTime, String endTime,
                                            String indexName, Integer PlatformAccountID, Integer SalePlatformHotelID) throws ParseException, IOException {
        final String INDEX_TYPE = "_doc";  //索引类型
        //获取时间戳
        Long temp = null;
        Long startTimeStamp = getTimeStamp(startTime);
        Long endTimeStamp = getTimeStamp(endTime);
        if (startTimeStamp > endTimeStamp) {
            temp = startTimeStamp;
            startTimeStamp = endTimeStamp;
            endTimeStamp = temp;
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("PlatformAccountID", PlatformAccountID))
                .must(QueryBuilders.termQuery("SalePlatformHotelID", SalePlatformHotelID))
                .must(QueryBuilders.rangeQuery("TimeStamp").gte(startTimeStamp).lte(endTimeStamp)));

        //查看查询语句
        log.info("es查询语句:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(indexName)  //indexName
                .addType(INDEX_TYPE).build();
        SearchResult result = jestClient.execute(search);
        return result.getSourceAsStringList();
    }

    //String to 时间戳
    private static Long getTimeStamp(String date) throws ParseException {
        Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(date);
        long timestamp = time.getTime();
        return timestamp;
    }
}
