package com.history.mapper;

import com.history.model.entity.Dynasty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 朝代Mapper接口
 * @author Diamond
 */
@Mapper
public interface DynastyMapper {

    /**
     * 查询所有朝代（按开始年份排序）
     */
    List<Dynasty> selectAll();

    /**
     * 根据ID查询
     */
    Dynasty selectById(Long id);

    /**
     * 插入朝代
     */
    int insert(Dynasty dynasty);

    /**
     * 更新朝代
     */
    int update(Dynasty dynasty);

    /**
     * 删除朝代
     */
    int deleteById(Long id);
}
