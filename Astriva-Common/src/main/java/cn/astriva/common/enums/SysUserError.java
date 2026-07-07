package cn.astriva.common.enums;

import cn.astriva.common.basic.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统用户错误码枚举
 *
 * @author Mr. Tao
 */
@Getter
@AllArgsConstructor
public enum SysUserError implements IErrorCode {
    /**
     * 传入数据值异常 -> 状态非法
     */
    STATUS_ERROR(400, "传入的状态范围超出"),
    /**
     * 手机号格式异常 -> 手机号非法
     */
    PHONE_ERROR(400, "手机号格式异常，请检查后重试"),
    /**
     * 被修改的系统用户不存在 -> 用户不存在
     */
    USER_NOT_FOUND(400, "当前系统用户不存在"),
    /**
     * 更新密码时旧密码错误 -> 密码错误
     */
    PASSWORD_ERROR(400, "旧密码错误，请检查后重试"),
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
