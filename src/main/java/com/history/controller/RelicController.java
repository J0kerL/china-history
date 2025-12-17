package com.history.controller;

import com.history.common.result.PageResult;
import com.history.common.result.Result;
import com.history.model.vo.RelicVO;
import com.history.service.RelicService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 历史遗迹控制器
 * @author Diamond
 */
@Slf4j
@RestController
@RequestMapping("/relic")
public class RelicController {

    @Resource
    private RelicService relicService;

    /**
     * 获取所有遗迹列表
     */
    @GetMapping("/list")
    public Result<List<RelicVO>> getAllRelics() {
        List<RelicVO> relics = relicService.getAllRelics();
        return Result.ok(relics);
    }

    /**
     * 根据ID获取遗迹详情
     */
    @GetMapping("/{id}")
    public Result<RelicVO> getRelicById(@PathVariable Long id) {
        RelicVO relic = relicService.getRelicById(id);
        return Result.ok(relic);
    }

    /**
     * 根据朝代ID获取遗迹列表
     */
    @GetMapping("/dynasty/{dynastyId}")
    public Result<List<RelicVO>> getRelicsByDynastyId(@PathVariable Long dynastyId) {
        List<RelicVO> relics = relicService.getRelicsByDynastyId(dynastyId);
        return Result.ok(relics);
    }

    /**
     * 搜索遗迹
     */
    @GetMapping("/search")
    public Result<List<RelicVO>> searchRelics(@RequestParam(required = false) String keyword) {
        List<RelicVO> relics = relicService.searchRelics(keyword);
        return Result.ok(relics);
    }

    /**
     * 分页查询遗迹
     */
    @GetMapping("/page")
    public Result<PageResult<RelicVO>> getRelicPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageResult<RelicVO> pageResult = relicService.getRelicPage(page, size);
        return Result.ok(pageResult);
    }
}
