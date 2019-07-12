package com.star.contractprovider.controller;

import com.star.contractprovider.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @PostMapping(value = "/insertUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String insertUser(@RequestBody User user) {
        log.info(user.toString());
        return "添加用户成功";
    }
}
