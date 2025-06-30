package com.magicgate.link.domain.client.customized;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.fastjson2.JSON;
import com.magicgate.common.utils.JobManager;
import com.magicgate.link.domain.client.AbstractLLMChatClient;
import com.magicgate.link.domain.client.LLMProviderProperties;
import com.magicgate.link.domain.dto.Dialogue;
import com.magicgate.link.exception.LLMChatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * @Author yangyangsheep
 * @Description 灵积百炼对话客户端-skd调用方式
 * @CreateTime 2025/6/29 23:50
 */
@Component
public class DashScopeChatClient extends AbstractLLMChatClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashScopeChatClient.class);


    protected DashScopeChatClient(LLMProviderProperties providerProperties) {
        super(providerProperties);
    }

    /**
     * 单轮对话文本生成
     *
     * @param dialogue 对话参数
     *
     * @return {@link String } 生成的文本
     */
    @Override
    public String getChatClientByModelAndDoAnswer(Dialogue dialogue) {
        LLMProviderProperties.ProviderConfig directConfig = getDirectConfig();
        Generation gen = new Generation();
        String jobDescription = JobManager.getJobDescription(dialogue.getPromptName());
        Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content(jobDescription).build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content(dialogue.getQuestion()).build();
        GenerationParam param = GenerationParam.builder()
                .apiKey(directConfig.getApiKey())
                // 此处以qwen-plus为例，可按需更换模型名称。模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                .model(dialogue.getModel()).messages(Arrays.asList(systemMsg, userMsg)).resultFormat(GenerationParam.ResultFormat.MESSAGE).build();
        try {
            GenerationResult call = gen.call(param);
            return JSON.toJSONString(call);
        } catch (NoApiKeyException | InputRequiredException e) {
            throw new LLMChatException("LLM Chat Error: " + e.getMessage());
        }
    }

    /**
     * 多轮对话
     *
     * @param dialogue 对话参数
     *
     * @return {@link Flux }<{@link ServerSentEvent }<{@link String }>>
     */
    @Override
    public Flux<ServerSentEvent<String>> getChatClientByModelAndDoChat(Dialogue dialogue) {
        LLMProviderProperties.ProviderConfig directConfig = getDirectConfig();
        Generation gen = new Generation();
        String jobDescription = JobManager.getJobDescription(dialogue.getPromptName());
        Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content(jobDescription).build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content(dialogue.getQuestion()).build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用阿里云百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey(directConfig.getApiKey())
                .model(dialogue.getModel())
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                // Qwen3模型通过enable_thinking参数控制思考过程（开源版默认True，商业版默认False）
                // 使用Qwen3开源版模型时，若未启用流式输出，请将下行取消注释，否则会报错
                //.enableThinking(false)
                .incrementalOutput(true)
                .build();
        try {
            //转换为标准的SSE流返回
            return Flux.from(gen.streamCall(param))
                    .map(data -> {
                        LOGGER.info("streamOutut:{}", JSON.toJSONString(data));
                        return ServerSentEvent.<String>builder()
                                .data(data.getOutput().getChoices().get(0).getMessage().getContent())
                                .build();
                    });
        } catch (NoApiKeyException | InputRequiredException e) {
            throw new LLMChatException("LLM Chat Error: " + e.getMessage());
        }
    }

    /**
     * 获取当前的对话客户端
     *
     * @param dialogue 对话参数
     *
     * @return {@link ChatClient }
     */
    @Override
    protected ChatClient getClient(Dialogue dialogue) {
        //SDK 的调用方式不适用于ChatClient
        return null;
    }

    /**
     * 获取支持的模型
     *
     * @return {@link List }<{@link String }>
     */
    @Override
    public List<String> getSupportedModels() {
        LLMProviderProperties.ProviderConfig directConfig = getDirectConfig();
        return directConfig.getModels();
    }

    /**
     * 获取Bean指定的配置项
     *
     * @return {@link LLMProviderProperties.ProviderConfig }
     */
    @Override
    protected LLMProviderProperties.ProviderConfig getDirectConfig() {
        return getProviderProperties().getProviders().getOrDefault("dashscope", new LLMProviderProperties.ProviderConfig());
    }

}
