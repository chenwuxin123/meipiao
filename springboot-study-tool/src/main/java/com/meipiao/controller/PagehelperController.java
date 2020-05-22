package com.meipiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meipiao.dao.PersonDao;
import com.meipiao.entity.Person;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Chenwx
 * @Date: 2020/5/8 15:53
 * @Des: 分页的使用
 */
@RestController
public class PagehelperController {

    @Resource
    PersonDao personDao;

    @GetMapping("/getAllPerson")
    public ModelAndView getAllPerson(Model model,
                                     @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Person> list = personDao.queryAll();
        PageInfo<Person> pageInfo = new PageInfo<Person>(list);
        model.addAttribute("pageInfo", pageInfo);
        return new ModelAndView("person.html");
    }

    @GetMapping("/test")
    public String test() {
        List<Person> list = personDao.queryAll();
        for (Person person : list) {
            System.err.println("我的打工仔信息：" + person.toString());
        }
        return "index";
    }
}
