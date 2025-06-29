package com.magicgate.link.service;

import com.magicgate.common.request.DialogueRequest;

/**
 * @Author yangyangsheep
 * @Description 灵积百炼对话服务
 * @CreateTime 2025/6/29 13:37
 */
public interface DashScopeChatService {

    /**
     * 单论对话获取结果信息
     *
     * @param request 对话参数
     *
     * @return {@link String }
     */
    String singleAnswer(DialogueRequest request);
}
