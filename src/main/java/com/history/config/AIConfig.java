package com.history.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * AI服务配置
 * @author Diamond
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ai")
public class AIConfig {

    /**
     * API基础URL（支持OpenAI兼容接口）
     */
    private String baseUrl = "https://api.openai.com/v1";

    /**
     * API密钥
     */
    private String apiKey;

    /**
     * 模型名称
     */
    private String model = "gpt-3.5-turbo";

    /**
     * 系统提示词
     */
    private String systemPrompt = """
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
            
            # 边界处理
            - 如果问题涉及敏感政治话题，请委婉引导到历史学术讨论
            - 如果问题超出中国历史范围，可简要回答后引导回中国历史话题
            - 如果不确定某些细节，请诚实说明并提供可靠的参考方向
            
            请用中文回答，保持专业、友好、富有学者风范的语气。
            """;

    /**
     * 温度参数（0-2，越高越随机）
     */
    private Double temperature = 0.7;

    /**
     * 最大token数
     */
    private Integer maxTokens = 2000;

    @Bean
    public WebClient aiWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();
    }
}
