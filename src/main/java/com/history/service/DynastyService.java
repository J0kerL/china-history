package com.history.service;

import com.history.model.vo.DynastyDetailVO;
import com.history.model.vo.DynastyVO;

import java.util.List;

/**
 * 朝代服务接口
 * @author Diamond
 */
public interface DynastyService {

    /**
     * 获取所有朝代列表
     */
    List<DynastyVO> getAllDynasties();

    /**
     * 根据ID获取朝代详情（包含相关人物和事件）
     */
    DynastyDetailVO getDynastyDetail(Long id);
}
