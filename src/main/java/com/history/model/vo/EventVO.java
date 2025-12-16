package com.history.model.vo;

import lombok.Data;

/**
 * 历史事件视图对象
 * @author Diamond
 */
@Data
public class EventVO {

    private Long id;
    private String title;
    private Integer startYear;
    private Integer endYear;
    private Long dynastyId;
    private String category;
    private String summary;
    private String details;
}
