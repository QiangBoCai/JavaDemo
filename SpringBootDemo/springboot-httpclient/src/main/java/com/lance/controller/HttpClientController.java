package com.lance.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller
public class HttpClientController {
    @Resource
    private HttpAPIService httpAPIService;

    @RequestMapping("httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }
}
