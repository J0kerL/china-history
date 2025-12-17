package com.history.controller;

import com.history.model.dto.ChatRequest;
import com.history.service.AIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * AI助手控制器
 * @author Diamond
 */
@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    /**
     * 流式聊天接口
     * @param request 聊天请求
     * @return SSE流式响应
     */
    @PostMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@Valid @RequestBody ChatRequest request) {
        log.info("收到AI聊天请求: {}", request.getMessage());
        return aiService.chatStream(request)
                .map(content -> content);
    }
}
