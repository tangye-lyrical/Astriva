package cn.astriva.common.exceptions;

import cn.astriva.common.basic.IErrorCode;
import cn.astriva.common.basic.ServiceException;

import java.io.Serial;

/**
 * 验证异常类
 *
 * @author Mr. Tao
 */
public class ValidateException extends ServiceException {
    @Serial
    private static final long serialVersionUID = -7811946305596665945L;

    public ValidateException(IErrorCode errorCode) {
        super(errorCode);
    }
}
