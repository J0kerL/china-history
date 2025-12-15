package com.history.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 修改密码DTO
 * @author Diamond
 */
@Data
public class UpdatePasswordDTO {

    @NotBlank(message = "当前密码不能为空")
    private String currentPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 12, message = "新密码长度必须在6-12位之间")
    private String newPassword;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 6, max = 12, message = "确认密码长度必须在6-12位之间")
    private String confirmPassword;
}
