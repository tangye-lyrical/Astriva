package cn.astriva.st.handler;

import cn.astriva.common.result.AjaxResult;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * SaToken异常处理器
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SaTokenExceptionHandler {

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

}
