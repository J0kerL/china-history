package com.history.service;

import com.history.model.vo.EventVO;

import java.util.List;

/**
 * 历史事件服务接口
 * @author Diamond
 */
public interface EventService {

    /**
     * 获取所有事件
     */
    List<EventVO> getAllEvents();

    /**
     * 根据ID获取事件详情
     */
    EventVO getEventById(Long id);
}
