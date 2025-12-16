package com.history.service.impl;

import com.history.common.exception.BizException;
import com.history.common.util.VOConverter;
import com.history.mapper.EventMapper;
import com.history.model.entity.Event;
import com.history.model.vo.EventVO;
import com.history.service.EventService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 历史事件服务实现类
 * @author Diamond
 */
@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private static final String EVENT_LIST_KEY = "event:list";
    private static final String EVENT_DETAIL_KEY = "event:detail:";
    private static final long CACHE_EXPIRE_DAYS = 7;

    @Resource
    private EventMapper eventMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<EventVO> getAllEvents() {
        List<EventVO> cachedList = (List<EventVO>) redisTemplate.opsForValue().get(EVENT_LIST_KEY);
        if (cachedList != null) {
            log.debug("从Redis缓存获取事件列表");
            return cachedList;
        }

        log.debug("从数据库查询事件列表");
        List<Event> events = eventMapper.selectAll();
        List<EventVO> result = events.stream()
                .map(VOConverter::toVO)
                .collect(Collectors.toList());

        redisTemplate.opsForValue().set(EVENT_LIST_KEY, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    public EventVO getEventById(Long id) {
        String cacheKey = EVENT_DETAIL_KEY + id;
        EventVO cached = (EventVO) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.debug("从Redis缓存获取事件详情: {}", id);
            return cached;
        }

        log.debug("从数据库查询事件详情: {}", id);
        Event event = eventMapper.selectById(id);
        if (event == null) {
            throw new BizException("事件不存在");
        }

        EventVO result = VOConverter.toVO(event);
        redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }
}
