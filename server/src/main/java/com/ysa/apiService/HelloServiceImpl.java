package com.ysa.apiService;

import api.HelloService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.time.LocalDateTime;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 13:59 2020/10/17
 * Modified By:
 */
@Slf4j
@Service
@RefreshScope
public class HelloServiceImpl implements HelloService {

    @Value("${ysa.name}")
    private String ysa;

    @Override
    public String hello(String name) {
        LocalDateTime date = LocalDateTime.now();
        log.info("-> 调用服务端接口 {} {} {}", name, JSON.toJSONString(date), ysa);
        return "hello,这里是服务端接口~ " + name + JSON.toJSONString(date) + ysa;
    }
}
