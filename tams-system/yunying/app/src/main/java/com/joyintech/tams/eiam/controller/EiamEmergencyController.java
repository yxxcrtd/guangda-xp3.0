package com.joyintech.tams.eiam.controller;

import com.joyintech.tams.eiam.service.EiamConfig;
import com.joyintech.tams.eiam.ws.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/eiam/emergency")
@Controller
public class EiamEmergencyController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping(value = "/open")
    @ResponseBody
    public String open(HttpServletRequest request, HttpServletResponse response) {
        operatorService.setState(EiamConfig.EMERGENCY_MODE);
        return "开启应急模式成功";
    }

    @RequestMapping(value = "/close")
    @ResponseBody
    public String close(HttpServletRequest request, HttpServletResponse response) {
        operatorService.setState(EiamConfig.NORMAL_MODE);
        return "关闭应急模式成功";
    }

}
