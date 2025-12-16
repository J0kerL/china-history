package com.history.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 朝代实体类
 * @author Diamond
 */
@Data
public class Dynasty {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 朝代名称
     */
    private String name;

    /**
     * 开国年份（公元纪年，可为负数表示公元前）
     */
    private Integer startYear;

    /**
     * 灭亡年份（公元纪年，可为负数表示公元前）
     */
    private Integer endYear;

    /**
     * 都城名称
     */
    private String capital;

    /**
     * 朝代概述
     */
    private String overview;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
