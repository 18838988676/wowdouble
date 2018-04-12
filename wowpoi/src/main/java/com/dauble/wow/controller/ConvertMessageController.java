package com.dauble.wow.controller;

import com.dauble.wow.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertMessageController {

    @RequestMapping(value = "/convert",produces = "application/x-wisely")
    public @ResponseBody DemoObj convert(@RequestBody DemoObj obj){

        return obj;
    }
}
