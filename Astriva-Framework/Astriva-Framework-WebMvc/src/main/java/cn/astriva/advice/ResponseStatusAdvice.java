package cn.astriva.advice;

import cn.astriva.result.AjaxResult;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应状态码同步器
 *
 * <p>通过 {@link RestControllerAdvice @RestControllerAdvice} 拦截所有返回
 * {@link AjaxResult} 的响应，自动将 HTTP 状态码设置为与 {@code AjaxResult.code} 一致，
 * 避免业务状态码与 HTTP 状态码不匹配的问题。</p>
 *
 * @author 棠野·Lyrical
 */
@RestControllerAdvice
public class ResponseStatusAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要拦截
     *
     * <p>仅当返回值类型为 {@link AjaxResult} 时才进行拦截处理。</p>
     *
     * @param returnType 返回值类型
     * @param converterType 消息转换器类型
     * @return true 表示需要拦截，false 表示跳过
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            @NonNull Class converterType) {
        return AjaxResult.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * 响应体写入前处理
     *
     * <p>读取 {@link AjaxResult} 中的 {@code code} 字段，将其设置为 HTTP 响应状态码。
     * 例如：{@code code=400} → HTTP 400 Bad Request。</p>
     *
     * @param body 响应体对象
     * @param returnType 返回值类型
     * @param selectedContentType 响应内容类型
     * @param selectedConverterType 选中的消息转换器类型
     * @param request HTTP 请求对象
     * @param response HTTP 响应对象
     * @return 原始响应体（不修改内容）
     */
    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {
        
        if (body instanceof AjaxResult<?> result) {
            Integer code = result.getCode();
            if (code != null) {
                response.setStatusCode(HttpStatus.valueOf(code));
            }
        }
        
        return body;
    }
}
