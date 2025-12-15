package com.history.controller;

import com.history.common.result.Result;
import com.history.model.dto.LoginDTO;
import com.history.model.dto.RegisterDTO;
import com.history.model.dto.UpdatePasswordDTO;
import com.history.model.dto.UpdateProfileDTO;
import com.history.model.vo.LoginVO;
import com.history.model.vo.UserVO;
import com.history.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户认证控制器
 * @author Diamond
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<UserVO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        UserVO userVO = userService.register(registerDTO);
        return Result.ok(userVO);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        LoginVO loginVO = userService.login(loginDTO);
        return Result.ok(loginVO);
    }

    /**
     * 用户退出登录
     */
    @PostMapping("/logout")
    public Result<Void> logout() {
        userService.logout();
        return Result.ok();
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/me")
    public Result<UserVO> getCurrentUser() {
        UserVO userVO = userService.getCurrentUser();
        return Result.ok(userVO);
    }

    /**
     * 修改个人信息
     */
    @PutMapping("/profile")
    public Result<UserVO> updateProfile(@Valid @RequestBody UpdateProfileDTO updateProfileDTO) {
        UserVO userVO = userService.updateProfile(updateProfileDTO);
        return Result.ok(userVO);
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public Result<Void> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return Result.ok();
    }
}
