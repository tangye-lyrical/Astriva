package cn.astriva.basic;

import lombok.Getter;

import java.io.Serial;

/**
 * 业务异常 => 所有异常的基类
 *
 * @author Mr. Tao
 */
@Getter
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1009235776781961541L;

    /**
     * 异常状态码
     */
    private final Integer code;

    /**
     * 构造业务异常
     *
     * @param code    业务状态码
     * @param message 异常消息
     */
    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造业务异常（默认状态码 500）
     *
     * @param message 异常消息
     */
    public ServiceException(String message) {
        this(400, message);
    }

    /**
     * 构造业务异常（直接使用错误码枚举）
     *
     * @param errorCode 错误码枚举
     */
    public ServiceException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    /**
     * 构造业务异常（使用错误码枚举 + 自定义消息）
     *
     * @param errorCode 错误码枚举
     * @param message 自定义异常消息（覆盖枚举中的默认消息）
     */
    public ServiceException(IErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    /**
     * 构造业务异常（带原始异常）
     *
     * @param code 业务状态码
     * @param message 异常消息
     * @param cause 原始异常
     */
    public ServiceException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
