package com.jimmyss.slabel.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseVO {
    @NotBlank(message="用户名为不能为空")
    private String username;

    @NotEmpty(message="密码不能为空")
    private String password;

    private String confirmPassword;
}
