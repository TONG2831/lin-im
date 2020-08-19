package com.xyz.im.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xyz
 * @date 2019-09-26
 */
@Controller
public class CommonController {

    /**
     * 错误页面跳转
     *
     * @param toPage
     * @return
     */
    @RequestMapping(value = "/error/{toPage}")
    public String errorForm(@PathVariable String toPage) {
        return "error/" + toPage;
    }

}
