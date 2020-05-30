package com.meipiao.index;

/**
 * 关于index的知识
 * @Author: Chenwx
 * @Date: 2020/5/30 17:21
 */
public class IndexInfo {

    /*
     *
     * 1. 在where 从句，group by 从句，order by 从句，on 从句中出现的列；
     *
     * 2. 索引字段越小越好；
     *
     * 3. 离散度大的列放到联合索引的前面；比如：
     *
     *     select * from payment where staff_id = 2 and customer_id = 236;
     *
     *     针对上面的查询是  index(sftaff_id, customer_id) 好？还是index(customer_id, staff_id)好？
     *
     *     因为customer_id的离散度更大，因此用后面的更合适！！
     *
     * 那么问题来了。怎么判断离散度呢，可以使用 select count(distinct customer_id), count(distinct staff_id) from 表名
     *
     * 谁的值大，说明这一些列的离散度更高！
     *
     *
     */
}
