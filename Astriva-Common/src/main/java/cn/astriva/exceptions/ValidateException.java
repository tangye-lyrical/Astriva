package cn.astriva.exceptions;

import cn.astriva.basic.ServiceException;
import cn.astriva.enums.SysLoginError;

import java.io.Serial;

/**
 * 验证异常类
 *
 * @author Mr. Tao
 */
public class ValidateException extends ServiceException {
    @Serial
    private static final long serialVersionUID = -7811946305596665945L;

    public ValidateException(SysLoginError errorCode) {
        super(errorCode);
    }
}
