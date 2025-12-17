package com.history.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 人物关系VO
 * @author Diamond
 */
@Data
public class PersonRelationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long personId;
    private String personName;
    private Long relatedPersonId;
    private String relatedPersonName;
    private String relationType;
    private String description;
}
