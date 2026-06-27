package cn.astriva.result;

import lombok.Data;

/**
 * 请求响应结果
 *
 * @author 棠野·Lyrical
 */
@Data
public class AjaxResult<T> {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T object;

    /**
     * 成功响应 => 需要返回成功数据
     */
    public static <T> AjaxResult<T> success(String msg, T object) {
        AjaxResult<T> result = new AjaxResult<>();
        result.code = 200;
        result.msg = msg;
        result.object = object;
        return result;
    }

    /**
     * 成功响应 => 不需要返回成功数据
     */
    public static <T> AjaxResult<T> success(String msg) {
        AjaxResult<T> result = new AjaxResult<>();
        result.code = 200;
        result.msg = msg;
        return result;
    }

    /**
     * 错误响应
     *
     * @param msg 错误提示
     */
    public static <T> AjaxResult<T> error(String msg) {
        AjaxResult<T> result = new AjaxResult<>();
        result.code = 500;
        result.msg = msg;
        return result;
    }

    /**
     * 错误响应
     *
     * @param code 错误码
     * @param msg  错误提示
     */
    public static <T> AjaxResult<T> error(Integer code, String msg) {
        AjaxResult<T> result = new AjaxResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }
}
