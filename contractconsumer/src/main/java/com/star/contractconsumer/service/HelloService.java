package com.star.contractconsumer.service;

import com.star.contractconsumer.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "contractprovider")
public interface HelloService {

    @GetMapping(value = "/info")
    String hello(@RequestParam("id") String id);

    @PostMapping(value = "/user/insertUser")
    String insertUser(@RequestBody User user);

}
