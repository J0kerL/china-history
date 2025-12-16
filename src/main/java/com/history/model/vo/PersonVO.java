package com.history.model.vo;

import lombok.Data;

/**
 * 历史人物视图对象
 * @author Diamond
 */
@Data
public class PersonVO {

    private Long id;
    private String name;
    private String styleName;
    private Long dynastyId;
    private Integer birthYear;
    private Integer deathYear;
    private String summary;
    private String imageUrl;
}
