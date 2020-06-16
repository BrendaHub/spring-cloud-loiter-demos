package com.wx.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 17:53 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Controller
@RequestMapping("/error")
public class ErrorController {
    @GetMapping(value = "/404")
    public String error404() {
        return "error";
    }

    @GetMapping(value = "/500")
    public String error500() {
        return "error";
    }
}
