package com.history.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 古今地名实体类
 * @author Diamond
 */
@Data
public class PlaceName {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 古代地名
     */
    private String ancientName;

    /**
     * 现代地名
     */
    private String modernName;

    /**
     * 现代地理位置
     */
    private String modernLocation;

    /**
     * 地名说明
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
