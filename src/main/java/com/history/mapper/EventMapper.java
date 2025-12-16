package com.history.mapper;

import com.history.model.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 历史事件Mapper接口
 * @author Diamond
 */
@Mapper
public interface EventMapper {

    /**
     * 根据朝代ID查询事件列表
     */
    List<Event> selectByDynastyId(@Param("dynastyId") Long dynastyId);

    /**
     * 根据ID查询
     */
    Event selectById(Long id);

    /**
     * 查询所有事件
     */
    List<Event> selectAll();
}
