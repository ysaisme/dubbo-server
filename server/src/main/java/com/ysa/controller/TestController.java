package com.ysa.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ysa.service.TestService;
import com.ysa.util.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
    public BaseResult getInfo() {

        try (Entry entry = SphU.entry("hello")) {
            return BaseResult.success(testService.getInfo());
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("系统繁忙！");
        }
    }

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setRefResource("hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @GetMapping("/getName")
    public BaseResult getName() {
        log.info("-> getName~~~");
        return BaseResult.success("Ysd");
    }

    @GetMapping("/getYsa")
    @SentinelResource(value = "getYsa", blockHandler = "exceptionHandler")
    public BaseResult getYsa() {
        log.info("-> getYsa~~~");
        return BaseResult.success("getYsa");
    }

    public BaseResult exceptionHandler(BlockException e) {
        log.error("-> {}", e.getRule());
        return BaseResult.fail("系统繁忙，请稍后处理~");
    }
}
