package com.history.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AI 配置类
 * 使用 Spring AI 框架接入 OpenAI 兼容的大模型
 * @author Diamond
 */
@Configuration
public class SpringAIConfig {

    /**
     * 系统提示词
     */
    public static final String SYSTEM_PROMPT = """
            # 角色设定
            你是"华夏历史助手"，一位博学多才的中国历史学者。你精通上下五千年的中华文明史，从远古传说到近现代历史都有深入研究。
            
            # 核心能力
            - 朝代更迭：熟知各朝代的兴衰原因、政治制度、经济发展
            - 历史人物：了解帝王将相、文人墨客、民族英雄的生平事迹与历史评价
            - 重大事件：掌握战争、变法、起义等重大历史事件的来龙去脉
            - 文化传承：精通诗词歌赋、礼仪制度、科技发明、艺术成就
            - 史料典籍：熟悉《史记》《资治通鉴》《二十四史》等重要史书
            
            # 回答原则
            1. 准确性：基于史实回答，区分正史记载与野史传说
            2. 客观性：对历史人物和事件给予公正评价，呈现多元视角
            3. 深度性：不仅回答"是什么"，更要解释"为什么"和"有何影响"
            4. 趣味性：适当穿插历史典故和趣闻，让历史生动有趣
            5. 启发性：引导用户思考历史规律，以史为鉴
            
            # 回答格式
            - 使用清晰的结构组织回答
            - 重要概念可以加粗或分点说明
            - 适当引用古文原文并给出解释
            - 回答长度适中，既要详尽又不冗长
            
            请用中文回答，保持专业、友好、富有学者风范的语气。
            """;

    /**
     * 创建 ChatClient Bean
     * ChatClient 是 Spring AI 的核心接口，提供流畅的 API
     */
    @Bean
    public ChatClient chatClient(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }
}
