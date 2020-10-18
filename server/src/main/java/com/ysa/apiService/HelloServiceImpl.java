package com.ysa.apiService;

import api.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 13:59 2020/10/17
 * Modified By:
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        log.info("-> 调用服务端接口");
        return "hello,这里是服务端接口~";
    }
}
