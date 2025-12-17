package com.history.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 历史遗迹实体类
 * @author Diamond
 */
@Data
public class Relic {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 遗迹名称
     */
    private String name;

    /**
     * 所在地
     */
    private String location;

    /**
     * 相关朝代ID
     */
    private Long dynastyId;

    /**
     * 相关历史事件ID
     */
    private Long relatedEventId;

    /**
     * 遗迹介绍
     */
    private String description;

    /**
     * 经纬度坐标
     */
    private String coordinates;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
