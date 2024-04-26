package com.kt.w.gateway;

/*
 * 创建配置类
*/

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeBeweenConfig {
    private LocalTime start;
    private LocalTime end;
}
