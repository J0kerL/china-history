package com.history.controller;

import com.history.common.result.Result;
import com.history.model.vo.PersonVO;
import com.history.model.vo.PersonRelationVO;
import com.history.service.PersonService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 历史人物控制器
 *
 * @author Diamond
 */
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    /**
     * 获取随机人物列表（用于首页展示）
     *
     * @param count 需要获取的人物数量，默认6个
     */
    @GetMapping("/random")
    public Result<List<PersonVO>> getRandomPersons(
            @RequestParam(defaultValue = "6") int count) {
        List<PersonVO> persons = personService.getRandomPersons(count);
        return Result.ok(persons);
    }

    /**
     * 根据ID获取人物详情
     */
    @GetMapping("/{id}")
    public Result<PersonVO> getPersonById(@PathVariable Long id) {
        PersonVO person = personService.getPersonById(id);
        return Result.ok(person);
    }

    /**
     * 获取所有人物列表
     */
    @GetMapping("/list")
    public Result<List<PersonVO>> getAllPersons() {
        List<PersonVO> persons = personService.getAllPersons();
        return Result.ok(persons);
    }

    /**
     * 获取人物关系列表
     */
    @GetMapping("/{id}/relations")
    public Result<List<PersonRelationVO>> getPersonRelations(@PathVariable Long id) {
        List<PersonRelationVO> relations = personService.getPersonRelations(id);
        return Result.ok(relations);
    }
}
