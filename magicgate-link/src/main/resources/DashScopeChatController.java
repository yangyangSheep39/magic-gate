package com.magicgate.link.controller;

import com.magicgate.common.request.DialogueRequest;
import com.magicgate.link.service.DashScopeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author yangyangsheep
 * @Description 百炼对话Controller
 * @CreateTime 2025/6/29 13:37
 */
@RestController
@RequestMapping
public class DashScopeChatController {

    @Autowired
    DashScopeChatService dashScopeChatService;

    /**
     * 单轮对话接口
     *
     * @param request
     *
     * @return
     */
    /**
     * 单论对话获取结果信息
     *
     * @param request 对话参数
     *
     * @return {@link String }
     /

    @GetMapping("/chat/single")
    public ResponseEntity<String> singleAnswer(@RequestBody DialogueRequest request) {
        String answer = dashScopeChatService.singleAnswer(request);
        return ResponseEntity.ok("answer");
    }


    /**
     * 单轮对话接口
     *
     * @param request
     *
     * @return
     */
    @GetMapping("/chat")
    public Flux<String> chat(@RequestBody DialogueRequest request) {
        dashScopeChatService.chat(request);
        return null;
    }
}
