package com.example.practice.controller;

import com.example.practice.entity.Test;
import com.example.practice.service.TestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * (Test)表控制层
 *
 * @author makejava
 * @since 2020-05-22 17:37:33
 */
@RestController
public class TestController {
    /**
     * 服务对象
     */
    @Resource
    private TestService testService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("test")
    public List<Test> test() {
        System.out.println("Coming....");
        List<Test> tests = testService.queryAll();
        return tests;
    }

}