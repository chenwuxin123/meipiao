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
 * pageInfo 参数解释：
 * pageNum：当前页   pageSize：每页的数量  total：总记录数 pages：总页数 list：结果集 size：当前页的数量
 * hasPreviousPage：是否有前一页 hasNextPage：是否有下一页  prePage：前一页 nextPage：下一页
 * 上一页下一页使用方法{@link #}person.html
 * (上一页):/method(pageNum=${pageInfo.hasPreviousPage}?${pageInfo.prePage}:1)
 * (下一页):/method(pageNum=${pageInfo.hasNextPage}?${pageInfo.nextPage}::${pageInfo.pages})
 * @Author: Chenwx
 * @Date: 2020/5/8 15:53
 * @Des: 分页的使用
 *
 *
 */
@RestController
public class PageHelperController {

    @Resource
    PersonDao personDao;

    /**
     * @param model
     * @param pageNum：pageNum前端传递的数据，代表查询第几页  默认第一页
     * @param pageSize：pageSize前端传递的数据，代表一页多少条数据 默认5条
     * @return
     */
    @GetMapping("/getAllPerson")
    public PageInfo<Person> getAllPerson(Model model,
                                     @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                     @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Person> list = personDao.queryAll();
        PageInfo<Person> pageInfo = new PageInfo<Person>(list);
        //查看参数
        return pageInfo;
        //效果展示
//        model.addAttribute("pageInfo", pageInfo);
//        return new ModelAndView("person.html");
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
