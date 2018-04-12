package com.dauble.wow.controller;


import com.dauble.wow.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdviceConteollerDemo {
    @RequestMapping("/advice")
    public @ResponseBody
    DemoObj getJson(DemoObj obj,
                    @ModelAttribute("sysTime") long sysTime) throws Exception {
        throw  new Exception("出错了,非常抱歉"+"sysTime=="+sysTime);
    }
}
