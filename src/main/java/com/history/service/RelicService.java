package com.history.service;

import com.history.common.result.PageResult;
import com.history.model.vo.RelicVO;

import java.util.List;

/**
 * 历史遗迹服务接口
 * @author Diamond
 */
public interface RelicService {

    /**
     * 获取所有遗迹列表
     */
    List<RelicVO> getAllRelics();

    /**
     * 根据ID获取遗迹详情
     */
    RelicVO getRelicById(Long id);

    /**
     * 根据朝代ID获取遗迹列表
     */
    List<RelicVO> getRelicsByDynastyId(Long dynastyId);

    /**
     * 搜索遗迹（名称或地点）
     */
    List<RelicVO> searchRelics(String keyword);

    /**
     * 分页查询遗迹
     */
    PageResult<RelicVO> getRelicPage(int page, int size);
}
