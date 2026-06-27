package cn.astriva.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
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
 * <p><b>为什么放在 WebMvc 模块而非 Admin？</b><br>
 * 全局异常处理是 Web 层的通用职责，所有业务模块都可能触发这些异常。放在 WebMvc
 * 模块后，任何依赖 WebMvc 的子模块（Admin、Service 等）都自动获得统一的异常
 * 处理能力，无需额外配置或传递依赖。</p>
 *
 * <p><b>当前处理的异常类型：</b></p>
 * <ul>
 *   <li>{@link NotLoginException} → 401 — Sa-Token 未登录校验失败</li>
 *   <li>{@link NotRoleException} → 403 — Sa-Token 角色校验失败</li>
 *   <li>{@link NotPermissionException} → 403 — Sa-Token 权限校验失败</li>
 *   <li>{@link MethodArgumentNotValidException} → 400 — 请求参数校验失败</li>
 *   <li>{@link Exception} → 500 — 其他未捕获的系统异常</li>
 * </ul>
 *
 * <p>{@code Sa-Token 的异常类在此处直接引用}，因此 WebMvc 模块的 pom.xml 中
 * 将 Sa-Token 依赖声明为 compile 作用域（非 optional），确保运行时类可加载。</p>
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 Sa-Token 未登录异常
     *
     * <p>当请求未携带有效 Token、Token 已过期、Token 无效时抛出此异常。</p>
     *
     * @param e NotLoginException 实例
     * @return 401 响应，msg 中附带登录失败原因
     */
    @ExceptionHandler(NotLoginException.class)
    public AjaxResult<Void> handleNotLogin(NotLoginException e) {
        return AjaxResult.error(401, "未登录：" + e.getMessage());
    }

    /**
     * 处理 Sa-Token 角色校验失败异常
     *
     * <p>当用户缺少访问目标接口所需的角色标识时抛出此异常。</p>
     *
     * @param e NotRoleException 实例
     * @return 403 响应
     */
    @ExceptionHandler(NotRoleException.class)
    public AjaxResult<Void> handleNotRole(NotRoleException e) {
        return AjaxResult.error(403, "无角色权限");
    }

    /**
     * 处理 Sa-Token 权限校验失败异常
     *
     * <p>当用户缺少访问目标接口所需的权限标识时抛出此异常。</p>
     *
     * @param e NotPermissionException 实例
     * @return 403 响应，msg 中附带缺失的权限标识
     */
    @ExceptionHandler(NotPermissionException.class)
    public AjaxResult<Void> handleNotPermission(NotPermissionException e) {
        return AjaxResult.error(403, "无权限：" + e.getPermission());
    }

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
