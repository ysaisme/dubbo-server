package com.ysa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 13:51 2020/10/17
 * Modified By:
 */
@MapperScan(basePackages = {"com.ysa.model.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
