package cn.astriva.auth.pojo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统登录表单
 *
 * @author 棠野·Lyrical
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SysLoginDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1390925798688992441L;
    /**
     * 用户名/手机号/邮箱
     */
    @NotNull(message = "请填写账号信息")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "请填写您的密码")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9][a-zA-Z0-9_]{5,19}$", message = "密码不合规，请重新输入")
    private String password;

    /**
     * 用户输入的验证码
     */
    @NotNull(message = "请填写验证码")
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$", message = "验证码格式错误，请重新输入")
    private String inputCaptcha;

    /**
     * 验证码-key
     */
    @NotNull(message = "验证码key不能为空")
    private String captchaKey;
}
