package com.star.contractconsumer.controller;

import com.star.contractconsumer.model.User;
import com.star.contractconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    /**
     * 使用RestTemplate方式调用服务
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/hello")
    public String hello(@RequestParam("id") String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/info?id=" + id, HttpMethod.GET,
                new HttpEntity<>(httpHeaders), String.class);

        return responseEntity.getBody().toString();

    }

    /**
     * 使用FeignClient的方式调用服务
     *
     * @return
     */
    @GetMapping(value = "/hello2")
    public String hello2(@RequestParam("id") String id) {
        return helloService.hello(id);
    }

    @PostMapping(value = "/addUser")
    public String insertUser(@RequestBody User user) {
        return helloService.insertUser(user);
    }

}
