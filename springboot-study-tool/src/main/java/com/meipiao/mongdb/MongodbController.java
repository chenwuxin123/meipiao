package com.meipiao.mongdb;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.CountOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * Aggregation aggregation =Aggregation.newAggregation(...)构造函数，写入查询条件
 *   !!!!!!重要:如果想要返回结果，new CountOperation.CountOperationBuilder(); 在查询添加中使用as("别名")方法
 * collectionName:mongodb中集合名(相当于mysql中table名)
 * @Author: Chenwx
 * @Date: 2020/5/27 8:08
 */
@RestController
public class MongodbController {

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/mongoTest")
    public String MongoTest() {
        //日期范围
        CountOperation.CountOperationBuilder countOperationBuilder = new CountOperation.CountOperationBuilder();
        Criteria criteria = Criteria.where("Task.CompleteTime").
                gt(new DateTime("2020-05-26T07:28:09+08:00")).
                lt(new DateTime("2020-05-26T07:28:10+08:00"));

        Aggregation aggregation =
                Aggregation.newAggregation(
                        Aggregation.match(criteria),
                        Aggregation.project("details.price"),
//                        Aggregation.unwind("details"),
//                        Aggregation.project("details"),
                        countOperationBuilder.as("count")
                );

        System.err.println("开始拉取数据....");
        AggregationResults<BasicDBObject> count = mongoTemplate.aggregate(aggregation, "Spider_HotelsPrice", BasicDBObject.class);
        System.err.println("拉取结束....");
        for (BasicDBObject basicDBObject : count) {
            System.err.println(JSON.toJSONString(basicDBObject));
        }

//        Aggregation aggregation = Aggregation.newAggregation(
//                    Aggregation.match(Criteria.where("IsStop").is(true)),Aggregation.unwind("HotelCodes"),
//                countOperationBuilder.as("count")
//        );
//        AggregationResults<BasicDBObject> outputTypeCount =
//                mongoTemplate.aggregate(aggregation, "Spider_SpiderTask", BasicDBObject.class);
//
//        for (BasicDBObject basicDBObject : outputTypeCount) {
//            System.err.println(JSON.toJSONString(basicDBObject));
//        }
        return "success";
    }


}
