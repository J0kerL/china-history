package com.history.service;

import com.history.model.dto.ChatRequest;
import reactor.core.publisher.Flux;

/**
 * AI服务接口
 * @author Diamond
 */
public interface AIService {

    /**
     * 流式聊天
     * @param request 聊天请求
     * @return 流式响应
     */
    Flux<String> chatStream(ChatRequest request);
}
