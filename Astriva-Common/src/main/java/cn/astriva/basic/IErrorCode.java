package cn.astriva.basic;

/**
 * 基础错误码接口
 *
 * @author Mr. Tao
 */
public interface IErrorCode {
    /**
     * 获取错误码
     *
     * @return 业务状态码
     */
    Integer getCode();

    /**
     * 获取错误消息
     *
     * @return 错误提示消息
     */
    String getMsg();
}
