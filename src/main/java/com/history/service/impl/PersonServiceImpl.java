package com.history.service.impl;

import com.history.common.exception.BizException;
import com.history.common.util.VOConverter;
import com.history.mapper.DynastyMapper;
import com.history.mapper.PersonMapper;
import com.history.mapper.PersonRelationMapper;
import com.history.model.entity.Dynasty;
import com.history.model.entity.Person;
import com.history.model.entity.PersonRelation;
import com.history.model.vo.PersonVO;
import com.history.model.vo.PersonRelationVO;
import com.history.service.PersonService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 历史人物服务实现类
 * @author Diamond
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private static final String PERSON_LIST_KEY = "person:list";
    private static final String PERSON_DETAIL_KEY = "person:detail:";
    private static final String PERSON_RELATIONS_KEY = "person:relations:";
    // 缓存1天
    private static final long CACHE_EXPIRE_DAYS = 1;

    @Resource
    private PersonMapper personMapper;

    @Resource
    private PersonRelationMapper personRelationMapper;

    @Resource
    private DynastyMapper dynastyMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonVO> getRandomPersons(int count) {
        // 先从缓存获取所有人物
        List<PersonVO> allPersons = (List<PersonVO>) redisTemplate.opsForValue().get(PERSON_LIST_KEY);

        if (allPersons == null) {
            // 缓存未命中，从数据库查询
            log.debug("从数据库查询人物列表");
            List<Person> persons = personMapper.selectAll();

            // 获取所有朝代信息用于填充朝代名称
            List<Dynasty> dynasties = dynastyMapper.selectAll();
            Map<Long, String> dynastyMap = dynasties.stream()
                    .collect(Collectors.toMap(Dynasty::getId, Dynasty::getName));

            allPersons = persons.stream()
                    .map(p -> VOConverter.toVO(p, dynastyMap))
                    .collect(Collectors.toList());

            // 存入缓存
            redisTemplate.opsForValue().set(PERSON_LIST_KEY, allPersons, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        }

        if (allPersons.isEmpty()) {
            return Collections.emptyList();
        }

        // 随机打乱并取前count个
        List<PersonVO> shuffled = new ArrayList<>(allPersons);
        Collections.shuffle(shuffled);
        return shuffled.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public PersonVO getPersonById(Long id) {
        String cacheKey = PERSON_DETAIL_KEY + id;
        PersonVO cached = (PersonVO) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.debug("从Redis缓存获取人物详情: {}", id);
            return cached;
        }

        Person person = personMapper.selectById(id);
        if (person == null) {
            throw new BizException("人物不存在");
        }

        // 获取朝代名称
        Dynasty dynasty = dynastyMapper.selectById(person.getDynastyId());
        String dynastyName = dynasty != null ? dynasty.getName() : null;

        PersonVO vo = VOConverter.toVO(person, dynastyName);
        redisTemplate.opsForValue().set(cacheKey, vo, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return vo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonVO> getAllPersons() {
        List<PersonVO> cached = (List<PersonVO>) redisTemplate.opsForValue().get(PERSON_LIST_KEY);
        if (cached != null) {
            return cached;
        }

        List<Person> persons = personMapper.selectAll();
        List<Dynasty> dynasties = dynastyMapper.selectAll();
        Map<Long, String> dynastyMap = dynasties.stream()
                .collect(Collectors.toMap(Dynasty::getId, Dynasty::getName));

        List<PersonVO> result = persons.stream()
                .map(p -> VOConverter.toVO(p, dynastyMap))
                .collect(Collectors.toList());

        redisTemplate.opsForValue().set(PERSON_LIST_KEY, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonRelationVO> getPersonRelations(Long personId) {
        String cacheKey = PERSON_RELATIONS_KEY + personId;
        List<PersonRelationVO> cached = (List<PersonRelationVO>) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            log.debug("从Redis缓存获取人物关系: {}", personId);
            return cached;
        }

        // 获取当前人物信息
        Person person = personMapper.selectById(personId);
        String personName = person != null ? person.getName() : "";

        // 查询关系
        List<PersonRelation> relations = personRelationMapper.selectByPersonId(personId);
        
        // 获取所有人物用于填充名称
        List<Person> allPersons = personMapper.selectAll();
        Map<Long, String> personNameMap = allPersons.stream()
                .collect(Collectors.toMap(Person::getId, Person::getName));

        List<PersonRelationVO> result = relations.stream().map(r -> {
            PersonRelationVO vo = new PersonRelationVO();
            vo.setId(r.getId());
            vo.setPersonId(r.getPersonId());
            vo.setPersonName(personName);
            vo.setRelatedPersonId(r.getRelatedPersonId());
            // 如果有关联人物ID，从map获取名称；否则使用存储的名称
            if (r.getRelatedPersonId() != null) {
                vo.setRelatedPersonName(personNameMap.getOrDefault(r.getRelatedPersonId(), r.getRelatedPersonName()));
            } else {
                vo.setRelatedPersonName(r.getRelatedPersonName());
            }
            vo.setRelationType(r.getRelationType());
            vo.setDescription(r.getDescription());
            return vo;
        }).collect(Collectors.toList());

        redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_DAYS, TimeUnit.DAYS);
        return result;
    }
}
