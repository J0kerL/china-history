package com.history.controller;

import com.history.common.result.Result;
import com.history.model.vo.EventVO;
import com.history.service.EventService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 历史事件控制器
 * @author Diamond
 */
@RestController
@RequestMapping("/events")
public class EventController {

    @Resource
    private EventService eventService;

    /**
     * 获取所有事件
     */
    @GetMapping
    public Result<List<EventVO>> getAllEvents() {
        return Result.ok(eventService.getAllEvents());
    }

    /**
     * 根据ID获取事件详情
     */
    @GetMapping("/{id}")
    public Result<EventVO> getEventById(@PathVariable Long id) {
        return Result.ok(eventService.getEventById(id));
    }
}
