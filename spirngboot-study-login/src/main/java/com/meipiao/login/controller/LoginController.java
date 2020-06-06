package com.meipiao.login.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
