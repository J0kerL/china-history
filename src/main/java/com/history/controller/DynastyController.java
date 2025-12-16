package com.history.controller;

import com.history.common.result.Result;
import com.history.model.vo.DynastyDetailVO;
import com.history.model.vo.DynastyVO;
import com.history.service.DynastyService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 朝代控制器
 * @author Diamond
 */
@Slf4j
@RestController
@RequestMapping("/dynasty")
public class DynastyController {

    @Resource
    private DynastyService dynastyService;

    /**
     * 获取所有朝代列表
     */
    @GetMapping("/list")
    public Result<List<DynastyVO>> getAllDynasties() {
        List<DynastyVO> dynasties = dynastyService.getAllDynasties();
        return Result.ok(dynasties);
    }

    /**
     * 根据ID获取朝代详情（包含相关人物和事件）
     */
    @GetMapping("/{id}")
    public Result<DynastyDetailVO> getDynastyDetail(@PathVariable Long id) {
        DynastyDetailVO detail = dynastyService.getDynastyDetail(id);
        return Result.ok(detail);
    }
}
