package cn.astriva.handler;

import cn.astriva.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * <p>通过 {@link RestControllerAdvice @RestControllerAdvice} 拦截所有 Controller
 * 层抛出的异常，统一返回 {@link AjaxResult} 格式，避免将原始异常栈暴露给前端。</p>
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验失败异常
     *
     * <p>当 {@code @Valid} / {@code @Validated} 校验失败时抛出。
     * 收集所有校验失败的字段错误信息，拼接为一条消息返回。</p>
     *
     * @param e MethodArgumentNotValidException 实例
     * @return 400 响应，msg 中附带具体字段校验失败原因
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult<Void> handleValidation(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("参数校验失败");
        return AjaxResult.error(400, msg);
    }

    /**
     * 处理其他所有未捕获的异常（兜底处理器）
     *
     * <p>作为最后一个防线，避免任何异常泄露到框架层导致返回非统一格式的错误。
     * 记录完整异常栈到日志，但只向客户端返回通用错误消息。</p>
     *
     * @param e Throwable 实例
     * @return 500 响应
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return AjaxResult.error("服务器内部错误");
    }
}
