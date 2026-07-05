package cn.astriva.common.enums;

import cn.astriva.common.basic.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证错误码枚举
 *
 * @author Mr. Tao
 */
@Getter
@AllArgsConstructor
public enum SysLoginError implements IErrorCode {
    /**
     * 系统登录异常
     */
    SYS_LOGIN_ERROR(400, "系统登录异常"),
    /**
     * 验证码已过期
     */
    CAPTCHA_EXPIRED(400, "验证码已过期，请重新获取"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR(400, "验证码错误！"),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(400, "当前用户不存在，请检查账号是否正确"),
    /**
     * 用户状态异常
     */
    USER_STATUS_ERROR(400, "当前用户状态异常，请联系管理员"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(400, "密码错误，请重新输入"),
    ;

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误消息
     */
    private final String msg;

}
