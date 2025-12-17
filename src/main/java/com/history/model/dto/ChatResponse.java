package com.history.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AI聊天响应DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    /**
     * 响应内容
     */
    private String content;

    /**
     * 是否完成
     */
    private boolean done;

    /**
     * 错误信息
     */
    private String error;

    public static ChatResponse content(String content) {
        return new ChatResponse(content, false, null);
    }

    public static ChatResponse done() {
        return new ChatResponse(null, true, null);
    }

    public static ChatResponse error(String error) {
        return new ChatResponse(null, true, error);
    }
}
