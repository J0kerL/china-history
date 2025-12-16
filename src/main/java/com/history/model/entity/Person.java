package com.history.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 历史人物实体类
 * @author Diamond
 */
@Data
public class Person {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 字/号
     */
    private String styleName;

    /**
     * 所属朝代ID
     */
    private Long dynastyId;

    /**
     * 出生年份
     */
    private Integer birthYear;

    /**
     * 去世年份
     */
    private Integer deathYear;

    /**
     * 人物简介
     */
    private String summary;

    /**
     * 头像URL
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
