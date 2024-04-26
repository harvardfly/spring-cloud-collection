package com.kt.w.contentcenter.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class MyUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        ErrorMsg errorMsg = null;
        if (e instanceof FlowException) {
            // 流控异常
            errorMsg = ErrorMsg.builder()
                    .status(100)
                    .msg("限流异常")
                    .build();

        } else if (e instanceof DegradeException) {
            // 降级异常
            errorMsg = ErrorMsg.builder()
                    .status(101)
                    .msg("降级异常")
                    .build();

        } else if (e instanceof ParamFlowException) {
            // 热点异常
            errorMsg = ErrorMsg.builder()
                    .status(102)
                    .msg("热点异常")
                    .build();

        } else if (e instanceof SystemBlockException) {
            // 系统异常
            errorMsg = ErrorMsg.builder()
                    .status(103)
                    .msg("系统异常")
                    .build();

        } else if (e instanceof AuthorityException) {
            // 授权异常
            errorMsg = ErrorMsg.builder()
                    .status(104)
                    .msg("授权异常")
                    .build();

        }
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        // spring mvc 自带的json操作工具，叫jackson
        new ObjectMapper()
                .writeValue(
                        response.getWriter(),
                        errorMsg
                );
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorMsg {

    private Integer status;
    private String msg;

}