package com.history.service;

import com.history.common.result.PageResult;
import com.history.model.vo.PlaceNameVO;

import java.util.List;

/**
 * 古今地名服务接口
 * @author Diamond
 */
public interface PlaceNameService {

    /**
     * 获取所有地名列表
     */
    List<PlaceNameVO> getAllPlaceNames();

    /**
     * 根据ID获取地名详情
     */
    PlaceNameVO getPlaceNameById(Long id);

    /**
     * 搜索地名（古代地名或现代地名）
     */
    List<PlaceNameVO> searchPlaceNames(String keyword);

    /**
     * 分页查询地名
     */
    PageResult<PlaceNameVO> getPlaceNamePage(int page, int size);
}
