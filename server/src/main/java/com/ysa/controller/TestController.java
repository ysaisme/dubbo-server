package com.ysa.controller;

import com.ysa.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 11:23 2020/10/19
 * Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/getInfo")
    public String getHandbookConfigList() {
        return testService.getInfo();
    }
}
