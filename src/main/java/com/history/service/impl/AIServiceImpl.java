package com.history.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.history.config.AIConfig;
import com.history.model.dto.ChatRequest;
import com.history.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * AI服务实现类
 * @author Diamond
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final AIConfig aiConfig;
    private final WebClient aiWebClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Flux<String> chatStream(ChatRequest request) {
        try {
            ObjectNode requestBody = buildRequestBody(request);
            
            return aiWebClient.post()
                    .uri("/chat/completions")
                    .bodyValue(requestBody.toString())
                    .retrieve()
                    .bodyToFlux(String.class)
                    .filter(line -> !line.isBlank() && !"[DONE]".equals(line))
                    .map(this::extractContent)
                    .filter(content -> content != null && !content.isEmpty())
                    .onErrorResume(e -> {
                        log.error("AI服务调用失败", e);
                        return Flux.just("[ERROR]" + e.getMessage());
                    });
        } catch (Exception e) {
            log.error("构建AI请求失败", e);
            return Flux.just("[ERROR]" + e.getMessage());
        }
    }

    /**
     * 构建请求体
     */
    private ObjectNode buildRequestBody(ChatRequest request) {
        ObjectNode body = objectMapper.createObjectNode();
        body.put("model", aiConfig.getModel());
        body.put("stream", true);
        body.put("temperature", aiConfig.getTemperature());
        body.put("max_tokens", aiConfig.getMaxTokens());

        ArrayNode messages = body.putArray("messages");

        // 添加系统提示词
        ObjectNode systemMessage = messages.addObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", aiConfig.getSystemPrompt());

        // 添加历史消息
        if (request.getHistory() != null) {
            for (ChatRequest.ChatMessage msg : request.getHistory()) {
                ObjectNode historyMessage = messages.addObject();
                historyMessage.put("role", msg.getRole());
                historyMessage.put("content", msg.getContent());
            }
        }

        // 添加当前用户消息
        ObjectNode userMessage = messages.addObject();
        userMessage.put("role", "user");
        userMessage.put("content", request.getMessage());

        return body;
    }

    /**
     * 从SSE响应中提取内容
     */
    private String extractContent(String line) {
        try {
            // 处理SSE格式：data: {...}
            String data = line;
            if (line.startsWith("data:")) {
                data = line.substring(5).trim();
            }
            
            if (data.isEmpty() || "[DONE]".equals(data)) {
                return "";
            }

            JsonNode json = objectMapper.readTree(data);
            JsonNode choices = json.get("choices");
            if (choices != null && choices.isArray() && !choices.isEmpty()) {
                JsonNode delta = choices.get(0).get("delta");
                if (delta != null && delta.has("content")) {
                    return delta.get("content").asText();
                }
            }
            return "";
        } catch (Exception e) {
            log.debug("解析响应失败: {}", line);
            return "";
        }
    }
}
