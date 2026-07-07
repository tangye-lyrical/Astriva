package cn.astriva.common.exceptions;

import cn.astriva.common.basic.IErrorCode;
import cn.astriva.common.basic.ServiceException;

import java.io.Serial;

/**
 * 更新时异常类
 *
 * @author Mr. Tao
 */
public class UpdateException extends ServiceException {
    @Serial
    private static final long serialVersionUID = -3577765393451130377L;

    public UpdateException(IErrorCode errorCode) {
        super(errorCode);
    }
}
