package com.history.model.vo;

import lombok.Data;

/**
 * 朝代视图对象
 * @author Diamond
 */
@Data
public class DynastyVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 朝代名称
     */
    private String name;

    /**
     * 开国年份
     */
    private Integer startYear;

    /**
     * 灭亡年份
     */
    private Integer endYear;

    /**
     * 都城名称
     */
    private String capital;

    /**
     * 朝代概述
     */
    private String description;
}
