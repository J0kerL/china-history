package com.history.service;


import com.history.model.dto.LoginDTO;
import com.history.model.dto.RegisterDTO;
import com.history.model.vo.LoginVO;
import com.history.model.vo.UserVO;

/**
 * 用户账号服务接口
 * @author Diamond
 */
public interface UserService {

    /**
     * 用户注册
     */
    UserVO register(RegisterDTO registerDTO);

    /**
     * 用户登录
     */
    LoginVO login(LoginDTO loginDTO);
}
