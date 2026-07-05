package cn.astriva.common.exceptions;

import cn.astriva.common.basic.ServiceException;
import cn.astriva.common.enums.SysLoginError;

import java.io.Serial;

/**
 * 系统登录异常类
 *
 * @author Mr. Tao
 */
public class SysLoginException extends ServiceException {
    @Serial
    private static final long serialVersionUID = -7811946305596665945L;

    /**
     * 构造系统登录异常
     *
     * @param errorCode 错误码枚举
     */
    public SysLoginException(SysLoginError errorCode) {
        super(errorCode);
    }

    /**
     * 构造系统登录异常
     *
     * @param errorCode 错误码枚举
     * @param message 错误消息
     */
    public SysLoginException(SysLoginError errorCode, String message) {
        super(errorCode, message);
    }
}
