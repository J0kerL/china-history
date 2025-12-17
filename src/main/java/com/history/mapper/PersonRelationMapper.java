package com.history.mapper;

import com.history.model.entity.PersonRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人物关系Mapper接口
 * @author Diamond
 */
@Mapper
public interface PersonRelationMapper {

    /**
     * 根据人物ID查询其所有关系
     */
    List<PersonRelation> selectByPersonId(@Param("personId") Long personId);

    /**
     * 查询所有关系
     */
    List<PersonRelation> selectAll();
}
