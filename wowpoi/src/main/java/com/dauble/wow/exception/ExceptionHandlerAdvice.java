package com.dauble.wow.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 增强controller,作用于controller 中带有RequestMapping 的注解的方法
 */
@ControllerAdvice
public class ExceptionHandlerAdvice  {

    /**
     * 统一的异常处理.其实拦截器同样可以做到
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handException(Exception e, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errormsg",e.getMessage());
        System.out.println("errorMsg");
        return modelAndView;
    }

    /**
     *  添加额外的信息
     * @param model
     */
    @ModelAttribute
    public  void  addAttribute(Model model){
        model.addAttribute("user","当前用户");
        model.addAttribute("sysTime",System.currentTimeMillis());
    }

    /**
     * 增强 参数的绑定
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.getFieldDefaultPrefix();
        webDataBinder.setDisallowedFields("id");
    }
}
