package com.history.mapper;

import com.history.model.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 历史人物Mapper接口
 * @author Diamond
 */
@Mapper
public interface PersonMapper {

    /**
     * 根据朝代ID查询人物列表
     */
    List<Person> selectByDynastyId(@Param("dynastyId") Long dynastyId);

    /**
     * 根据ID查询
     */
    Person selectById(Long id);

    /**
     * 查询所有人物
     */
    List<Person> selectAll();
}
