package com.joyintech.tams.eiam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/eiam/login")
@Controller
public class EiamLoginController {

    @RequestMapping(value = "/denied")
    public ModelAndView denied(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cebbank/login/loginDenied");
        return mv;
    }

}
