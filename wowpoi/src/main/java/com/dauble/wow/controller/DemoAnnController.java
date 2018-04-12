package com.dauble.wow.controller;

import com.dauble.wow.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/anno")
public class DemoAnnController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String index(HttpServletRequest request) {
        System.out.println("anno can acess");
        return "url:" + request.getRequestURL() + "can access";
    }

    @RequestMapping(produces = "text/plain;charset=utf-8",
            value = "/pathvar/{str}")
    public @ResponseBody String demoPathVar
            (@PathVariable String str, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + "can access";
    }

    @RequestMapping(produces = "text/plain;charset=utf-8" ,value = "/requestParam")
    public @ResponseBody  String  passRequestParam(Long id
            ,HttpServletRequest request){

        return "url:"+request.getRequestURL()+" can access + id="+id ;
    }

    @RequestMapping(value = "/requestObj",produces = "text/plain;charset=utf-8")
    public @ResponseBody String passRequestObj(DemoObj obj , HttpServletRequest request){
        return "url:"+request.getRequestURL()+"can access +obj = "+obj;
    }

    @RequestMapping(value = {"/name1","/name2"} ,produces = "text/plain;charset=utf-8")
    public @ResponseBody String pass(HttpServletRequest request){
        return "url:"+request.getRequestURL()+"can access ";
    }
}
