package com.dauble.wow.controller;

import com.dauble.wow.service.AsyncPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class AsyncController {

    @Autowired
    private AsyncPushService asyncPushService;

    @RequestMapping(value = "/defer")
    @ResponseBody
    public DeferredResult<String> defer(){
        System.out.println("defer.=================");
       return  asyncPushService.getDeferredResult();
    }
}
