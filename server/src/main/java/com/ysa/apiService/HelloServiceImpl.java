package com.ysa.apiService;

import api.HelloService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

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
        LocalDateTime date = LocalDateTime.now();
        log.info("-> 调用服务端接口 {}", JSON.toJSONString(date));
        return "hello,这里是服务端接口~ " + JSON.toJSONString(date);
    }
}
