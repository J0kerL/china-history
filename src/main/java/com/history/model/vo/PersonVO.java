package com.history.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 历史人物视图对象
 * @author Diamond
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
     * 所属朝代名称
     */
    private String dynastyName;

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
     * 主要成就列表
     */
    private List<String> achievements;
}
