//package com.magicgate.link.domain.client.customized;
//
//import com.magicgate.link.domain.client.AbstractLLMChatClient;
//import com.magicgate.link.domain.client.LLMProviderProperties;
//import com.magicgate.link.domain.dto.Dialogue;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
/// **
// * @Author yangyangsheep
// * @Description openAi的调用方式
// * @CreateTime 2025/6/30 20:25
// */
//@Component
//public class OpenAiChatClient extends AbstractLLMChatClient {
//
//    public OpenAiChatClient(LLMProviderProperties providerProperties) {
//        super(providerProperties);
//    }
//
//    /**
//     * 获取当前的对话客户端
//     *
//     * @param dialogue 对话参数
//     *
//     * @return {@link ChatClient }
//     */
//    @Override
//    protected ChatClient getClient(Dialogue dialogue) {
//        Map<String, LLMProviderProperties.ProviderConfig> providers = getProviderProperties().getProviders();
//        return null;
//    }
//
//    /**
//     * 获取支持的模型
//     *
//     * @return {@link List }<{@link String }>
//     */
//    @Override
//    public List<String> getSupportedModels() {
//        LLMProviderProperties.ProviderConfig providerConfig = getProviderProperties().getProviders().getOrDefault("openai", null);
//        return providerConfig.getModels();
//    }
//
//
//}
