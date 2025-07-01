# magic-gate 项目说明

## 目录结构以及关键类说明
```text
magic-gate/
├── magicgate-common/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/magicgate/common/
│   │   │   │               ├── request/
│   │   │   │               │   ├── DialogueHistoryRequest.java
│   │   │   │               │   └── DialogueRequest.java
│   │   │   │               ├── utils/
│   │   │   │                   ├── BeanCopyUtils.java  //BeanUtils增强
│   │   │   │                   └── JobManager.java  //临时职责管理类，用来管理prompt提示词
│   │   │   ├── resources/
│   │   │       ├── META-INF/
│   │   │           └── spring.factories
│   │   ├── test/
│   │       ├── java/
│   └── pom.xml
├── magicgate-link/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/
│   │   │   │       ├── magicgate/
│   │   │   │           ├── link/
│   │   │   │               ├── controller/
│   │   │   │               │   └── DashScopeChatController.java
│   │   │   │               ├── domain/
│   │   │   │               │   ├── client/
│   │   │   │               │   │   ├── customized/
│   │   │   │               │   │   │   ├── DashScopeChatClient.java
│   │   │   │               │   │   │   ├── DashScopeOpenAiChatClient.java
│   │   │   │               │   │   │   └── OpenAiChatClient.java
│   │   │   │               │   │   ├── AbstractLLMChatClient.java
│   │   │   │               │   │   ├── CommonDialogueParamConstants.java
│   │   │   │               │   │   ├── LLMChatClientFactory.java
│   │   │   │               │   │   └── LLMProviderProperties.java
│   │   │   │               │   ├── dto/
│   │   │   │               │   │   └── Dialogue.java
│   │   │   │               │   ├── model/
│   │   │   │               │   │   └── ChatClientModel.java
│   │   │   │               │   ├── process/
│   │   │   │               │       ├── impl/
│   │   │   │               │       │   ├── DialogueProcessImpl.java
│   │   │   │               │       │   └── KnowledgeBaseProcessImpl.java
│   │   │   │               │       ├── DialogueProcess.java
│   │   │   │               │       └── KnowledgeBaseProcess.java
│   │   │   │               ├── exception/
│   │   │   │               │   └── LLMChatException.java
│   │   │   │               ├── handler/
│   │   │   │               │   └── GlobalExceptionHandler.java
│   │   │   │               ├── infrastructure/
│   │   │   │               │   ├── impl/
│   │   │   │               │   │   └── RAGClientImpl.java
│   │   │   │               │   └── RAGClient.java
│   │   │   │               ├── service/
│   │   │   │               │   ├── impl/
│   │   │   │               │   │   └── DashScopeChatServiceImpl.java
│   │   │   │               │   └── DashScopeChatService.java
│   │   │   │               ├── utils/
│   │   │   │               │   └── FluxUtils.java
│   │   │   │               └── MagicGateLinkApplication.java
│   │   │   ├── resources/
│   │   │       ├── job/
│   │   │       │   └── AiTeacher.job
│   │   │       └── application.yml
│   │   ├── test/
│   │       ├── java/
│   └── pom.xml
├── README.md
└── pom.xml

```