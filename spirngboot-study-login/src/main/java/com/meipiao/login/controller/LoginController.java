package com.meipiao.login.controller;

import com.meipiao.login.config.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Chenwx
 * @Date: 2020/6/5 17:28
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    public String set(HttpServletRequest request) {
        List<String> list = (List<String>) request.getSession().getAttribute("list");
        if (list == null) {
            list = new ArrayList<>();
            request.getSession().setAttribute("list", list);
        }
        list.add("xxxxx");

        String id = request.getSession().getId();
        return id;
    }


}
