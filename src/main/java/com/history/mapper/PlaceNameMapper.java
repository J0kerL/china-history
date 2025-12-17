package com.history.mapper;

import com.history.model.entity.PlaceName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 古今地名Mapper接口
 * @author Diamond
 */
@Mapper
public interface PlaceNameMapper {

    /**
     * 查询所有地名
     */
    List<PlaceName> selectAll();

    /**
     * 根据ID查询
     */
    PlaceName selectById(Long id);

    /**
     * 根据古代地名模糊查询
     */
    List<PlaceName> selectByAncientName(@Param("ancientName") String ancientName);

    /**
     * 根据现代地名模糊查询
     */
    List<PlaceName> selectByModernName(@Param("modernName") String modernName);

    /**
     * 综合搜索（古代地名或现代地名）
     */
    List<PlaceName> search(@Param("keyword") String keyword);

    /**
     * 分页查询
     */
    List<PlaceName> selectPage(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询总数
     */
    long count();
}
