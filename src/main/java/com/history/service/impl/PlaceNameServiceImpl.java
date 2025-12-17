package com.history.service.impl;

import com.history.common.exception.BizException;
import com.history.common.result.PageResult;
import com.history.common.util.VOConverter;
import com.history.mapper.PlaceNameMapper;
import com.history.model.entity.PlaceName;
import com.history.model.vo.PlaceNameVO;
import com.history.service.PlaceNameService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 古今地名服务实现类
 * @author Diamond
 */
@Slf4j
@Service
public class PlaceNameServiceImpl implements PlaceNameService {

    private static final String PLACE_NAME_LIST_KEY = "placename:list";
    private static final String PLACE_NAME_DETAIL_KEY = "placename:detail:";
    private static final long CACHE_EXPIRE_DAYS = 7;

    @Resource
    private PlaceNameMapper placeNameMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<PlaceNameVO> getAllPlaceNames() {
        // 先从Redis缓存获取
        List<PlaceNameVO> cachedList = (List<PlaceNameVO>) redisTemplate.opsForValue().get(PLACE_NAME_LIST_KEY);
        if (cachedList != null) {
            log.debug("从Redis缓存获取地名列表");
            return cachedList;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询地名列表");
        List<PlaceName> placeNames = placeNameMapper.selectAll();
        List<PlaceNameVO> result = placeNames.stream()
                .map(VOConverter::toVO)
                .collect(Collectors.toList());

        // 存入Redis缓存
        redisTemplate.opsForValue().set(PLACE_NAME_LIST_KEY, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    public PlaceNameVO getPlaceNameById(Long id) {
        // 先从Redis缓存获取
        String cacheKey = PLACE_NAME_DETAIL_KEY + id;
        PlaceNameVO cached = (PlaceNameVO) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.debug("从Redis缓存获取地名详情: {}", id);
            return cached;
        }

        // 缓存未命中，从数据库查询
        log.debug("从数据库查询地名详情: {}", id);
        PlaceName placeName = placeNameMapper.selectById(id);
        if (placeName == null) {
            throw new BizException("地名不存在");
        }

        PlaceNameVO result = VOConverter.toVO(placeName);
        // 存入Redis缓存
        redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    public List<PlaceNameVO> searchPlaceNames(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllPlaceNames();
        }
        List<PlaceName> placeNames = placeNameMapper.search(keyword.trim());
        return placeNames.stream()
                .map(VOConverter::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResult<PlaceNameVO> getPlaceNamePage(int page, int size) {
        int offset = (page - 1) * size;
        List<PlaceName> placeNames = placeNameMapper.selectPage(offset, size);
        long total = placeNameMapper.count();

        List<PlaceNameVO> list = placeNames.stream()
                .map(VOConverter::toVO)
                .collect(Collectors.toList());

        return new PageResult<>(total, page, size, list);
    }
}
