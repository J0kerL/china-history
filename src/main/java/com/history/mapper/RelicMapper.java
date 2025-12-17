package com.history.mapper;

import com.history.model.entity.Relic;
import com.history.model.vo.RelicVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 历史遗迹Mapper接口
 * @author Diamond
 */
@Mapper
public interface RelicMapper {

    /**
     * 查询所有遗迹（带朝代和事件信息）
     */
    List<RelicVO> selectAllWithDetails();

    /**
     * 根据ID查询（带朝代和事件信息）
     */
    RelicVO selectByIdWithDetails(Long id);

    /**
     * 根据ID查询
     */
    Relic selectById(Long id);

    /**
     * 根据朝代ID查询
     */
    List<RelicVO> selectByDynastyId(Long dynastyId);

    /**
     * 根据名称模糊查询
     */
    List<RelicVO> selectByName(@Param("name") String name);

    /**
     * 综合搜索（名称或地点）
     */
    List<RelicVO> search(@Param("keyword") String keyword);

    /**
     * 分页查询
     */
    List<RelicVO> selectPage(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询总数
     */
    long count();
}
