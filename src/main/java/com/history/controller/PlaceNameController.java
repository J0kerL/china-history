package com.history.controller;

import com.history.common.result.PageResult;
import com.history.common.result.Result;
import com.history.model.vo.PlaceNameVO;
import com.history.service.PlaceNameService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 古今地名控制器
 * @author Diamond
 */
@Slf4j
@RestController
@RequestMapping("/place-name")
public class PlaceNameController {

    @Resource
    private PlaceNameService placeNameService;

    /**
     * 获取所有地名列表
     */
    @GetMapping("/list")
    public Result<List<PlaceNameVO>> getAllPlaceNames() {
        List<PlaceNameVO> placeNames = placeNameService.getAllPlaceNames();
        return Result.ok(placeNames);
    }

    /**
     * 根据ID获取地名详情
     */
    @GetMapping("/{id}")
    public Result<PlaceNameVO> getPlaceNameById(@PathVariable Long id) {
        PlaceNameVO placeName = placeNameService.getPlaceNameById(id);
        return Result.ok(placeName);
    }

    /**
     * 搜索地名
     */
    @GetMapping("/search")
    public Result<List<PlaceNameVO>> searchPlaceNames(@RequestParam(required = false) String keyword) {
        List<PlaceNameVO> placeNames = placeNameService.searchPlaceNames(keyword);
        return Result.ok(placeNames);
    }

    /**
     * 分页查询地名
     */
    @GetMapping("/page")
    public Result<PageResult<PlaceNameVO>> getPlaceNamePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageResult<PlaceNameVO> pageResult = placeNameService.getPlaceNamePage(page, size);
        return Result.ok(pageResult);
    }
}
