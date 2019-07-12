package com.star.contractprovider.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping(value = "/info")
    public String hello(@RequestParam("id") String id) {
        return "hello ".concat(id);
    }

}
