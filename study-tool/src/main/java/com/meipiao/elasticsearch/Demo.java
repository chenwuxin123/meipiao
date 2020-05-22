package com.meipiao.elasticsearch;

import org.anyline.entity.DataRow;
import org.anyline.entity.DataSet;
import org.anyline.util.BasicUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Chenwx
 * @Date: 2020/5/11 8:47
 */
@RestController
@RequestMapping("demo")
public class Demo {
    @RequestMapping("1")
    public boolean demo() {
        DataRow row = new DataRow();
        row.put("id", BasicUtil.getRandomNumber(10000, 100000));
        row.put("name", "张三" + UUID.randomUUID());
        row.put("sex", 1);
        return EsUtil.getEsUtil().save("test", row);
    }

    @RequestMapping("2")
    public DataRow demo2() {
        return EsUtil.getEsUtil().query("test", "z6vNpHEB1cBhMzEvOq2k");
    }

    @RequestMapping("3")
    public String demo3() {
        EsUtil.getEsUtil().createIndex("test2");
        return "success";
    }

    @RequestMapping("4")
    public boolean demo4() throws IOException {
        DataSet set = new DataSet();
        DataRow row = new DataRow();
        row.put("id", 1);
        row.put("name", "张三11");
        row.put("sex", 1);
        set.add(row);
        row = new DataRow();
        row.put("id", 2);
        row.put("name", "李四11");
        row.put("sex", 0);
        set.add(row);
        return EsUtil.getEsUtil().save("test2", set);
    }

    @RequestMapping("5")
    public boolean demo5() {
        return EsUtil.getEsUtil().deleteIndexDoc("test2", "1qvxpXEB1cBhMzEvfq17");
    }

    @RequestMapping("6")
    public boolean demo6() {
        return EsUtil.getEsUtil().deleteIndex("test2");
    }

    /*@RequestMapping("7")
    public boolean demo7() {
        return EsUtil.getEsUtil().deleteIndexDocBySearch("test2", "11");
    }*/

    @RequestMapping("8")
    public boolean demo8() {
        DataRow row = new DataRow();
        row.put("id", 1);
        row.put("name", "张三22222222");
        row.put("sex", 1);
        return EsUtil.getEsUtil().insert("test2", row);
    }

    @RequestMapping("9")
    public Page demo9() throws Exception {
        return EsUtil.getEsUtil().querys("test", null, 2, 5);
    }
}
