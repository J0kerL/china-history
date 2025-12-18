package com.history.service.impl;

import com.history.model.dto.ChatRequest;
import com.history.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于 Spring AI 的 AI 服务实现
 * @author Diamond
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpringAIServiceImpl implements AIService {

    private final ChatClient chatClient;

    @Override
    public Flux<String> chatStream(ChatRequest request) {
        try {
            // 构建消息历史
            List<Message> messages = buildMessages(request);
            
            // 使用 ChatClient 进行流式对话
            return chatClient.prompt()
                    .messages(messages)
                    .stream()
                    .content()
                    .onErrorResume(e -> {
                        log.error("Spring AI 服务调用失败", e);
                        return Flux.just("[ERROR]" + e.getMessage());
                    });
        } catch (Exception e) {
            log.error("构建 Spring AI 请求失败", e);
            return Flux.just("[ERROR]" + e.getMessage());
        }
    }

    /**
     * 构建消息列表（包含历史消息和当前消息）
     */
    private List<Message> buildMessages(ChatRequest request) {
        List<Message> messages = new ArrayList<>();
        
        // 添加历史消息
        if (request.getHistory() != null) {
            for (ChatRequest.ChatMessage msg : request.getHistory()) {
                if ("user".equals(msg.getRole())) {
                    messages.add(new UserMessage(msg.getContent()));
                } else if ("assistant".equals(msg.getRole())) {
                    messages.add(new AssistantMessage(msg.getContent()));
                }
            }
        }
        
        // 添加当前用户消息
        messages.add(new UserMessage(request.getMessage()));
        
        return messages;
    }
}
