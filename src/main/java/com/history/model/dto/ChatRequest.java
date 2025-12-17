package com.history.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * AI聊天请求DTO
 * @author Diamond
 */
@Data
public class ChatRequest {

    /**
     * 用户消息
     */
    @NotBlank(message = "消息内容不能为空")
    private String message;

    /**
     * 历史消息列表
     */
    private List<ChatMessage> history;

    @Data
    public static class ChatMessage {
        private String role;
        private String content;
    }
}
