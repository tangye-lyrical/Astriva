package cn.astriva.handler;

import cn.astriva.basic.CurrentEntity;
import cn.astriva.utils.CurrentHolder;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT上下文数据处理器
 *
 * @author 棠野·Lyrical
 */
@Component
public class JwtCurrentHandler implements HandlerInterceptor {
    /**
     * 请求前置处理：从 JWT 中提取当前登录用户信息并存入线程上下文
     *
     * @param request  当前 HTTP 请求
     * @param response 当前 HTTP 响应
     * @param handler  当前请求的处理器对象
     * @return 是否放行请求
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 检查是否登录，排除白名单路径
        if (!StpUtil.isLogin()) {
            return true;
        }

        // 鉴权交给SaToken处理，这里只获取登录用户信息，不进行权限校验
        CurrentEntity current = new CurrentEntity();
        current.setUserId(StpUtil.getLoginIdAsLong());
        current.setUsername((String) StpUtil.getExtra("username"));
        current.setDeptId(Long.valueOf(StpUtil.getExtra("deptId").toString()));
        current.setNickname((String) StpUtil.getExtra("nickname"));

        // 将获取的数据存储至上下文持有者中
        CurrentHolder.setCurrentEntity(current);

        return true;
    }

    /**
     * 请求完成后清理线程上下文，防止内存泄漏
     *
     * @param request  当前 HTTP 请求
     * @param response 当前 HTTP 响应
     * @param handler  当前请求的处理器对象
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex) throws Exception {
        // 清除上下文数据
        CurrentHolder.clear();
    }
}
