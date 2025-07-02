package com.magicgate.rag.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author yangyangsheep
 * @Description bean注入
 * @CreateTime 2025/7/2 21:25
 */
@AutoConfiguration
public class BailianAutoconfiguration {

    /**
     * 百炼调用时需要配置 DashScope API，对 dashScopeApi 强依赖。
     *
     * @return
     */
    @Bean
    public DashScopeApi dashScopeApi() {
        return DashScopeApi.builder().apiKey("-----------------").build();
    }

}
