package com.example.privilegedemo.controller;

import com.example.privilegedemo.data.AuthModel;
import com.example.privilegedemo.data.User;
import com.example.privilegedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("hi")
@Slf4j
public class HelloController {
    @RequestMapping("hi1")
    @AuthModel(codeList = {"a1", "a2"})
    public String hi1() {
        log.info("h1");
        return "h1";
    }

    @RequestMapping("hi2")
    @AuthModel(codeList = {"a4"})
    public String hi2() {
        log.info("h2");
        return "h2";
    }

    @Resource
    private UserService userService;

    @RequestMapping("hi3")
    @AuthModel(codeList = {"a3"})
    public List<User> user() {
        return userService.findUserList("ruimaj");
    }
}
