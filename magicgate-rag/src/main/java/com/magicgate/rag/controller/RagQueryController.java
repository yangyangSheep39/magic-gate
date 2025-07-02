package com.magicgate.rag.controller;

import com.alibaba.cloud.ai.advisor.DocumentRetrievalAdvisor;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yangyangsheep
 * @Description 查询使用的Rag测试
 * @CreateTime 2025/7/2 20:33
 */
@RestController
@Slf4j
public class RagQueryController {
    private final ChatClient chatClient;

    private final DashScopeApi dashscopeApi;
    private static final String retrievalSystemTemplate = """
            Context information is below.
            ---------------------
            {question_answer_context}
            ---------------------
            Given the context and provided history information and not prior knowledge,
            reply to the user comment. If the answer is not in the context, inform
            the user that you can't answer the question.
            """;

    public RagQueryController(ChatClient.Builder builder, DashScopeApi dashscopeApi) {
        DocumentRetriever retriever = new DashScopeDocumentRetriever(dashscopeApi, DashScopeDocumentRetrieverOptions.builder().withIndexName("codeRagApiTest").build());

        this.dashscopeApi = dashscopeApi;
        this.chatClient = builder.defaultAdvisors(new DocumentRetrievalAdvisor(retriever, new SystemPromptTemplate(retrievalSystemTemplate))).build();
    }

    @GetMapping("/rag")
    public void importDocuments() {
        String path = "/Users/yangyangsheep/Downloads/泰国风俗.docx";

        // 1. import and split documents
        DocumentReader reader = new DashScopeDocumentCloudReader(path, dashscopeApi, null);
        List<Document> documentList = reader.get();
        log.info("{} documents loaded and split", documentList.size());

        // 1. add documents to DashScope cloud storage
        VectorStore vectorStore = new DashScopeCloudStore(dashscopeApi, new DashScopeStoreOptions("codeRagApiTest"));
        vectorStore.add(documentList);
        log.info("{} documents added to dashscope cloud vector store", documentList.size());
    }
}
