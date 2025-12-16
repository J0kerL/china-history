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
     * 姓名（常用称呼）
     */
    private String name;

    /**
     * 姓氏
     */
    private String surname;

    /**
     * 名（本名）
     */
    private String givenName;

    /**
     * 字
     */
    private String courtesyName;

    /**
     * 号
     */
    private String artName;

    /**
     * 谥号
     */
    private String posthumousName;

    /**
     * 庙号
     */
    private String templeName;

    /**
     * 所属朝代ID
     */
    private Long dynastyId;

    /**
     * 出生年份（公元纪年，可为负数表示公元前）
     */
    private Integer birthYear;

    /**
     * 去世年份（公元纪年，可为负数表示公元前）
     */
    private Integer deathYear;

    /**
     * 人物简介
     */
    private String summary;

    /**
     * 主要成就（多个成就用逗号分隔）
     */
    private String achievements;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
