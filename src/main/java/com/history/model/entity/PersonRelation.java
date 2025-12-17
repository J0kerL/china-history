package com.history.model.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 人物关系实体类
 * @author Diamond
 */
@Data
public class PersonRelation {
    private Long id;
    private Long personId;
    private Long relatedPersonId;
    private String relatedPersonName;
    private String relationType;
    private String description;
    private LocalDateTime createdAt;
}
