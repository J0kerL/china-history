package com.history.service.impl;

import com.history.common.exception.BizException;
import com.history.common.util.BCryptUtil;
import com.history.common.util.JwtUtil;
import com.history.common.util.RedisUtil;
import com.history.common.util.UserContext;
import com.history.mapper.UserMapper;
import com.history.model.dto.LoginDTO;
import com.history.model.dto.RegisterDTO;
import com.history.model.dto.UpdatePasswordDTO;
import com.history.model.dto.UpdateProfileDTO;
import com.history.model.entity.User;
import com.history.model.vo.LoginVO;
import com.history.model.vo.UserVO;
import com.history.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户账号服务实现
 *
 * @author Diamond
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 将 User 实体转换为 UserVO
     */
    private UserVO convertToUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setPassword("******");
        userVO.setEmail(user.getEmail());
        userVO.setCreatedAt(user.getCreatedAt());
        return userVO;
    }

    /**
     * 用户注册
     */
    @Override
    public UserVO register(RegisterDTO registerDTO) {
        // 检查用户名是否已存在
        User existUser = userMapper.selectByUsername(registerDTO.getUsername());
        if (existUser != null) {
            throw new BizException("用户名已存在");
        }
        // 创建用户
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(BCryptUtil.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        int result = userMapper.insert(user);
        if (result <= 0) {
            throw new BizException("注册失败");
        }
        log.info("用户注册成功：username={}", registerDTO.getUsername());
        User account = userMapper.selectByUsername(registerDTO.getUsername());
        return convertToUserVO(account);
    }

    /**
     * 用户登录
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new BizException("该用户不存在");
        }

        // 验证密码
        if (!BCryptUtil.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        // 生成Token
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        // 将Token存入Redis（有效期与JWT Token一致）
        String redisKey = "token:" + user.getId();
        redisUtil.set(redisKey, token, jwtUtil.getExpiration() / 1000);

        log.info("用户登录成功：userId={}, username={}", user.getId(), user.getUsername());

        // 返回登录信息
        return new LoginVO(token, user.getId(), user.getUsername());
    }

    /**
     * 用户退出登录
     */
    @Override
    public void logout() {
        Long userId = UserContext.getUserId();
        if (userId != null) {
            // 从Redis中删除Token
            String redisKey = "token:" + userId;
            redisUtil.delete(redisKey);
            log.info("用户退出登录：userId={}", userId);
        }
    }

    /**
     * 获取当前用户信息
     */
    @Override
    public UserVO getCurrentUser() {
        Long userId = UserContext.getUserId();
        if (userId == null) {
            throw new BizException("用户未登录");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException("用户不存在");
        }

        return convertToUserVO(user);
    }

    /**
     * 修改个人信息
     */
    @Override
    public UserVO updateProfile(UpdateProfileDTO updateProfileDTO) {
        Long userId = UserContext.getUserId();
        if (userId == null) {
            throw new BizException("用户未登录");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException("用户不存在");
        }

        // 检查用户名是否被其他用户占用
        if (!user.getUsername().equals(updateProfileDTO.getUsername())) {
            User existUser = userMapper.selectByUsername(updateProfileDTO.getUsername());
            if (existUser != null) {
                throw new BizException("用户名已被占用");
            }
        }

        // 更新用户信息
        user.setUsername(updateProfileDTO.getUsername());
        user.setEmail(updateProfileDTO.getEmail());
        int result = userMapper.update(user);
        if (result <= 0) {
            throw new BizException("更新失败");
        }

        log.info("用户信息更新成功：userId={}", userId);

        return convertToUserVO(user);
    }

    /**
     * 修改密码
     */
    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        Long userId = UserContext.getUserId();
        if (userId == null) {
            throw new BizException("用户未登录");
        }

        // 验证新密码和确认密码是否一致
        if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword())) {
            throw new BizException("两次输入的密码不一致");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException("用户不存在");
        }

        // 验证当前密码
        if (!BCryptUtil.matches(updatePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new BizException("当前密码错误");
        }

        // 更新密码
        user.setPassword(BCryptUtil.encode(updatePasswordDTO.getNewPassword()));
        int result = userMapper.update(user);
        if (result <= 0) {
            throw new BizException("密码更新失败");
        }

        log.info("用户密码更新成功：userId={}", userId);
    }
}
