package com.history.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 朝代详情视图对象（包含相关人物和事件）
 * @author Diamond
 */
@Data
public class DynastyDetailVO {

    /**
     * 朝代基本信息
     */
    private DynastyVO dynasty;

    /**
     * 相关历史人物
     */
    private List<PersonVO> persons;

    /**
     * 相关历史事件
     */
    private List<EventVO> events;
}
