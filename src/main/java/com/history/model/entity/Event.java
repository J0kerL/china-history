package com.history.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 历史事件实体类
 * @author Diamond
 */
@Data
public class Event {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 事件标题
     */
    private String title;

    /**
     * 开始年份
     */
    private Integer startYear;

    /**
     * 结束年份
     */
    private Integer endYear;

    /**
     * 所属朝代ID
     */
    private Long dynastyId;

    /**
     * 事件类型
     */
    private String category;

    /**
     * 事件概要
     */
    private String summary;

    /**
     * 事件详情
     */
    private String details;

    /**
     * 事件地点ID
     */
    private Long locationPlaceId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
