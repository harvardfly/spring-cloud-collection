package com.kingsoft.woa.contentcenter.controller.share;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.kingsoft.woa.contentcenter.domain.entity.content.Share;
import com.kingsoft.woa.contentcenter.domain.entity.content.dto.content.ShareDto;
import com.kingsoft.woa.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shares")
public class ShareController {
    private final ShareService shareService;

    @GetMapping("/{id}")
    public ShareDto findById(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    @SentinelResource("hot")
    @GetMapping("/test-hot")
    public String testHot(@RequestParam(required = false) String a, @RequestParam(required = false) String b) {
        return a + b;
    }

    @GetMapping("test-add-flow-rule")
    public String testAddRule() {
        this.initFlowQpsRule();
        return "success";
    }

    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule("/shares/{id}");
        // set limit qps to 1
        rule.setCount(1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
    
}
