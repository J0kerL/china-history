package com.history.service.impl;

import com.history.common.exception.BizException;
import com.history.common.result.PageResult;
import com.history.mapper.RelicMapper;
import com.history.model.vo.RelicVO;
import com.history.service.RelicService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 历史遗迹服务实现类
 * @author Diamond
 */
@Slf4j
@Service
public class RelicServiceImpl implements RelicService {

    private static final String RELIC_LIST_KEY = "relic:list";
    private static final String RELIC_DETAIL_KEY = "relic:detail:";
    private static final String RELIC_DYNASTY_KEY = "relic:dynasty:";
    private static final long CACHE_EXPIRE_DAYS = 7;

    @Resource
    private RelicMapper relicMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<RelicVO> getAllRelics() {
        // 先从Redis缓存获取
        List<RelicVO> cachedList = (List<RelicVO>) redisTemplate.opsForValue().get(RELIC_LIST_KEY);
        if (cachedList != null) {
            log.debug("从Redis缓存获取遗迹列表");
            return cachedList;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询遗迹列表");
        List<RelicVO> result = relicMapper.selectAllWithDetails();

        // 存入Redis缓存
        redisTemplate.opsForValue().set(RELIC_LIST_KEY, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    public RelicVO getRelicById(Long id) {
        // 先从Redis缓存获取
        String cacheKey = RELIC_DETAIL_KEY + id;
        RelicVO cached = (RelicVO) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.debug("从Redis缓存获取遗迹详情: {}", id);
            return cached;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询遗迹详情: {}", id);
        RelicVO result = relicMapper.selectByIdWithDetails(id);
        if (result == null) {
            throw new BizException("遗迹不存在");
        }

        // 存入Redis缓存
        redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RelicVO> getRelicsByDynastyId(Long dynastyId) {
        // 先从Redis缓存获取
        String cacheKey = RELIC_DYNASTY_KEY + dynastyId;
        List<RelicVO> cachedList = (List<RelicVO>) redisTemplate.opsForValue().get(cacheKey);
        if (cachedList != null) {
            log.debug("从Redis缓存获取朝代遗迹列表: {}", dynastyId);
            return cachedList;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询朝代遗迹列表: {}", dynastyId);
        List<RelicVO> result = relicMapper.selectByDynastyId(dynastyId);

        // 存入Redis缓存
        redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    public List<RelicVO> searchRelics(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllRelics();
        }
        return relicMapper.search(keyword.trim());
    }

    @Override
    public PageResult<RelicVO> getRelicPage(int page, int size) {
        int offset = (page - 1) * size;
        List<RelicVO> list = relicMapper.selectPage(offset, size);
        long total = relicMapper.count();

        return new PageResult<>(total, page, size, list);
    }
}
