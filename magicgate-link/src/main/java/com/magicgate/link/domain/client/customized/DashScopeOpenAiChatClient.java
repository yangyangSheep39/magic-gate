package com.magicgate.link.domain.client.customized;

import com.magicgate.link.domain.client.AbstractLLMChatClient;
import com.magicgate.link.domain.client.LLMProviderProperties;
import com.magicgate.link.domain.dto.Dialogue;
import lombok.Getter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author yangyangsheep
 * @ClassName DashScopeOpenAiChatClient.java
 * @Description 千问的OpenAi兼容调用方式
 * @CreateTime 2025/6/30 23:48
 */

@Component
@Getter
public class DashScopeOpenAiChatClient extends AbstractLLMChatClient {


    protected DashScopeOpenAiChatClient(LLMProviderProperties providerProperties) {
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
        return "";
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
        return null;
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
        return null;
    }

    /**
     * 获取支持的模型
     *
     * @return {@link List }<{@link String }>
     */
    @Override
    public List<String> getSupportedModels() {
        return List.of();
    }

    /**
     * 获取Bean指定的配置项
     *
     * @return {@link LLMProviderProperties.ProviderConfig }
     */
    @Override
    protected LLMProviderProperties.ProviderConfig getDirectConfig() {
        return null;
    }
}


