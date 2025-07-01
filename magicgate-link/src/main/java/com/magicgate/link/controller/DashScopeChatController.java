package com.magicgate.link.controller;

//import com.alibaba.dashscope.aigc.generation.Generation;
//import com.alibaba.dashscope.aigc.generation.GenerationParam;
//import com.alibaba.dashscope.aigc.generation.GenerationResult;
//import com.alibaba.dashscope.common.Message;
//import com.alibaba.dashscope.common.Role;
//import com.alibaba.dashscope.exception.ApiException;
//import com.alibaba.dashscope.exception.InputRequiredException;
//import com.alibaba.dashscope.exception.NoApiKeyException;
import com.magicgate.common.request.DialogueRequest;
import com.magicgate.link.service.DashScopeChatService;
//import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
     * 单论对话获取结果信息
     *
     * @param request 对话参数
     *
     * @return {@link String }
     */
    @PostMapping("/chat/single")
    public ResponseEntity<String> singleAnswer(@RequestBody @Validated DialogueRequest request) {
        String answer = dashScopeChatService.singleAnswer(request);
        return ResponseEntity.ok(answer);
    }


    /**
     * SSE 多轮对话接口
     *
     * @param request 对话参数
     *
     * @return {@link Flux }
     */
    @PostMapping(path = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> chat(@RequestBody DialogueRequest request) {
        return dashScopeChatService.chat(request);
    }

}
