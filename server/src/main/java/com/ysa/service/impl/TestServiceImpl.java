package com.ysa.service.impl;

import api.UserService;
import com.ysa.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 11:25 2020/10/19
 * Modified By:
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Reference
    UserService userService;

    @Override
    public String getInfo() {
        log.info("-> 调用 userService");
        return userService.getInfo();
    }
}
