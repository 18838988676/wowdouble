package com.dauble.wow.controller;

import com.dauble.wow.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestControllerTest {

    @RequestMapping(value = "/json",produces = "application/json;charset=utf-8")
    public DemoObj jsonRest(DemoObj obj){
        return obj;
    }

    @RequestMapping(produces = "application/xml;charset=utf-8",value = "/xml")
    public DemoObj xmlRest(DemoObj obj)
    {
        return obj;
    }
}
