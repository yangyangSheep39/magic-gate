package com.magicgate.rag.controller;

/**
 * @Author yangyangsheep
 * @Description 查询使用的Rag测试
 * @CreateTime 2025/7/2 20:33
 */
public class RagQueryController {
    llm-vie3wwctzt25zpie  业务空间id
            1542d9d3fff641108ee4a95c9d758999_p_efm  agent


    public static com.aliyun.bailian20231229.Client createClient() throws Exception {
        // 工程代码建议使用更安全的无AK方式，凭据配置方式请参见：https://help.aliyun.com/document_detail/378657.html。
        com.aliyun.credentials.Client credential = new com.aliyun.credentials.Client();
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setCredential(credential);
        // Endpoint 请参考 https://api.aliyun.com/product/bailian
        config.endpoint = "bailian.cn-beijing.aliyuncs.com";
        return new com.aliyun.bailian20231229.Client(config);
    }

}
