package com.history.common.util;

import com.history.model.entity.Dynasty;
import com.history.model.entity.Event;
import com.history.model.entity.Person;
import com.history.model.vo.DynastyVO;
import com.history.model.vo.EventVO;
import com.history.model.vo.PersonVO;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * 实体类与VO对象转换工具类
 *
 * @author Diamond
 */
public class VOConverter {

    private VOConverter() {
    }

    /**
     * Dynasty -> DynastyVO
     */
    public static DynastyVO toVO(Dynasty dynasty) {
        if (dynasty == null) {
            return null;
        }
        DynastyVO vo = new DynastyVO();
        vo.setId(dynasty.getId());
        vo.setName(dynasty.getName());
        vo.setStartYear(dynasty.getStartYear());
        vo.setEndYear(dynasty.getEndYear());
        vo.setCapital(dynasty.getCapital());
        vo.setDescription(dynasty.getOverview());
        return vo;
    }

    /**
     * Event -> EventVO
     */
    public static EventVO toVO(Event event) {
        if (event == null) {
            return null;
        }
        EventVO vo = new EventVO();
        vo.setId(event.getId());
        vo.setTitle(event.getTitle());
        vo.setStartYear(event.getStartYear());
        vo.setEndYear(event.getEndYear());
        vo.setDynastyId(event.getDynastyId());
        vo.setCategory(event.getCategory());
        vo.setSummary(event.getSummary());
        vo.setDetails(event.getDetails());
        return vo;
    }

    /**
     * Person -> PersonVO (不含朝代名称)
     */
    public static PersonVO toVO(Person person) {
        return toVO(person, (String) null);
    }

    /**
     * Person -> PersonVO (通过朝代Map获取朝代名称)
     */
    public static PersonVO toVO(Person person, Map<Long, String> dynastyMap) {
        if (person == null) {
            return null;
        }
        String dynastyName = dynastyMap != null ? dynastyMap.get(person.getDynastyId()) : null;
        return toVO(person, dynastyName);
    }

    /**
     * Person -> PersonVO (指定朝代名称)
     */
    public static PersonVO toVO(Person person, String dynastyName) {
        if (person == null) {
            return null;
        }
        PersonVO vo = new PersonVO();
        vo.setId(person.getId());
        vo.setName(person.getName());
        vo.setSurname(person.getSurname());
        vo.setGivenName(person.getGivenName());
        vo.setCourtesyName(person.getCourtesyName());
        vo.setArtName(person.getArtName());
        vo.setPosthumousName(person.getPosthumousName());
        vo.setTempleName(person.getTempleName());
        vo.setDynastyId(person.getDynastyId());
        vo.setDynastyName(dynastyName);
        vo.setBirthYear(person.getBirthYear());
        vo.setDeathYear(person.getDeathYear());
        vo.setSummary(person.getSummary());
        // 将逗号分隔的成就字符串转换为列表
        if (person.getAchievements() != null && !person.getAchievements().isEmpty()) {
            vo.setAchievements(Arrays.asList(person.getAchievements().split(",")));
        } else {
            vo.setAchievements(Collections.emptyList());
        }
        return vo;
    }
}
