package com.meipiao.dao;

import com.meipiao.entity.Person;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/5/8 15:55
 */
public interface PersonDao {

    @Select("select *from person")
    List<Person> queryAll();

}
