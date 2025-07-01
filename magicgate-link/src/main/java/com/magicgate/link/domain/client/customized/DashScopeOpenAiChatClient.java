package com.magicgate.link.domain.client.customized;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.magicgate.common.utils.JobManager;
import com.magicgate.link.domain.advisor.SafeGuardAdvisor;
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
        ChatClient client = getClient(dialogue);
        String jobDescription = JobManager.getJobDescription(dialogue.getPromptName());
        //CallResponse是一个Spring Ai通用的返回模型
        String content = client.prompt(jobDescription)
                .advisors(SafeGuardAdvisor.builder().sensitiveWords(List.of("Fool")).build()) //这里可以使用增强处理，阶段大模型的返回并做一些对应的操作，比如用户输入敏感词的检测，可以在这里完成
                .user(dialogue.getQuestion())
                .call().content();
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
        LLMProviderProperties.ProviderConfig directConfig = getDirectConfig();
        //构建LLM接入鉴权参数以及地址
        DashScopeApi dashScopeApi = DashScopeApi.builder().apiKey(directConfig.getApiKey()).baseUrl(directConfig.getBaseUrl()).build();
        DashScopeChatModel dashScopeChatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .defaultOptions(
                        //大模型的调用参数
                        DashScopeChatOptions.builder()
                                .withModel(dialogue.getModel())  // 使用视觉模型
                                //.withMultiModel(true)             // 启用多模态
                                //.withVlHighResolutionImages(true) // 启用高分辨率图片处理
                                .withTemperature(dialogue.getTemperature())
                                .build()
                )
                .build();
        return ChatClient.builder(dashScopeChatModel).build();

    }

    /**
     * 获取Bean指定的配置项
     *
     * @return {@link LLMProviderProperties.ProviderConfig }
     */
    @Override
    protected LLMProviderProperties.ProviderConfig getDirectConfig() {
        return getProviderProperties().getProviders().getOrDefault("dashscope-openai", new LLMProviderProperties.ProviderConfig());
    }
}


