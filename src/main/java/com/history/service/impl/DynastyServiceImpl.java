package com.history.service.impl;

import com.history.common.exception.BizException;
import com.history.common.util.VOConverter;
import com.history.mapper.DynastyMapper;
import com.history.mapper.EventMapper;
import com.history.mapper.PersonMapper;
import com.history.model.entity.Dynasty;
import com.history.model.entity.Event;
import com.history.model.entity.Person;
import com.history.model.vo.DynastyDetailVO;
import com.history.model.vo.DynastyVO;
import com.history.model.vo.EventVO;
import com.history.model.vo.PersonVO;
import com.history.service.DynastyService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 朝代服务实现类
 * @author Diamond
 */
@Slf4j
@Service
public class DynastyServiceImpl implements DynastyService {

    private static final String DYNASTY_LIST_KEY = "dynasty:list";
    private static final String DYNASTY_DETAIL_KEY = "dynasty:detail:";
    // 缓存7天
    private static final long CACHE_EXPIRE_DAYS = 7;

    @Resource
    private DynastyMapper dynastyMapper;

    @Resource
    private PersonMapper personMapper;

    @Resource
    private EventMapper eventMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<DynastyVO> getAllDynasties() {
        // 先从Redis缓存获取
        List<DynastyVO> cachedList = (List<DynastyVO>) redisTemplate.opsForValue().get(DYNASTY_LIST_KEY);
        if (cachedList != null) {
            log.debug("从Redis缓存获取朝代列表");
            return cachedList;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询朝代列表");
        List<Dynasty> dynasties = dynastyMapper.selectAll();
        List<DynastyVO> result = dynasties.stream()
                .map(VOConverter::toVO)
                .collect(Collectors.toList());

        // 存入Redis缓存
        redisTemplate.opsForValue().set(DYNASTY_LIST_KEY, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    public DynastyDetailVO getDynastyDetail(Long id) {
        // 先从Redis缓存获取
        String cacheKey = DYNASTY_DETAIL_KEY + id;
        DynastyDetailVO cached = (DynastyDetailVO) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.debug("从Redis缓存获取朝代详情: {}", id);
            return cached;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询朝代详情: {}", id);
        
        // 获取朝代基本信息
        Dynasty dynasty = dynastyMapper.selectById(id);
        if (dynasty == null) {
            throw new BizException("朝代不存在");
        }
        DynastyVO dynastyVO = VOConverter.toVO(dynasty);
        
        // 获取相关人物
        List<Person> persons = personMapper.selectByDynastyId(id);
        List<PersonVO> personVOs = persons != null ? 
                persons.stream().map(VOConverter::toVO).collect(Collectors.toList()) : 
                Collections.emptyList();
        
        // 获取相关事件
        List<Event> events = eventMapper.selectByDynastyId(id);
        List<EventVO> eventVOs = events != null ? 
                events.stream().map(VOConverter::toVO).collect(Collectors.toList()) : 
                Collections.emptyList();
        
        // 组装结果
        DynastyDetailVO result = new DynastyDetailVO();
        result.setDynasty(dynastyVO);
        result.setPersons(personVOs);
        result.setEvents(eventVOs);

        // 存入Redis缓存
        redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }
}
