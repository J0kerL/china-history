package com.history.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 历史遗迹VO
 * @author Diamond
 */
@Data
public class RelicVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
     * 相关朝代名称
     */
    private String dynastyName;

    /**
     * 相关历史事件ID
     */
    private Long relatedEventId;

    /**
     * 相关历史事件标题
     */
    private String relatedEventTitle;

    /**
     * 遗迹介绍
     */
    private String description;

    /**
     * 经纬度坐标
     */
    private String coordinates;
}
