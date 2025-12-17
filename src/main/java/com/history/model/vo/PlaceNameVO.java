package com.history.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 古今地名VO
 * @author Diamond
 */
@Data
public class PlaceNameVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
}
