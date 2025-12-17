package com.history.service;

import com.history.model.vo.PersonVO;
import com.history.model.vo.PersonRelationVO;

import java.util.List;

/**
 * 历史人物服务接口
 * @author Diamond
 */
public interface PersonService {

    /**
     * 获取随机人物列表（用于首页展示）
     * @param count 需要获取的人物数量
     * @return 随机人物列表
     */
    List<PersonVO> getRandomPersons(int count);

    /**
     * 根据ID获取人物详情
     * @param id 人物ID
     * @return 人物详情
     */
    PersonVO getPersonById(Long id);

    /**
     * 获取所有人物列表
     * @return 所有人物列表
     */
    List<PersonVO> getAllPersons();

    /**
     * 获取人物关系列表
     * @param personId 人物ID
     * @return 人物关系列表
     */
    List<PersonRelationVO> getPersonRelations(Long personId);
}
