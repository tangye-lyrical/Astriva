package cn.astriva.st.config;

import cn.astriva.st.annotation.NotLogin;
import cn.astriva.st.handler.JwtCurrentHandler;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * SaToken 配置类
 *
 * @author 棠野·Lyrical
 */
@Configuration
@RequiredArgsConstructor
public class SaTokenConfig implements WebMvcConfigurer {
    /**
     * 基础设施白名单路径
     *
     * <p>这些路径不是 Controller 方法（如 Swagger、静态资源），无法标注
     * {@link NotLogin @Anonymous} 注解，因此在这里统一放行。
     * Controller 层的匿名接口请使用 {@code @Anonymous} 注解，无需修改本配置。
     */
    private final List<String> patterns = List.of(
            // Swagger / SpringDoc 相关
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/doc.html",
            // 静态资源
            "/favicon.ico",
            "/error",
            "/static/**"
    );
    /**
     * JWT上下文数据处理器
     */
    private final JwtCurrentHandler jwtCurrentHandler;

    /**
     * Sa-Token 整合 jwt (Simple 简单模式)
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

    /**
     * 注册 Sa-Token 路由拦截器
     * <p>
     * 白名单处理策略：
     * <ol>
     *   <li><b>基础设施路径</b>（Swagger、静态资源等）— 通过 {@code patterns} 列表静态声明放行</li>
     *   <li><b>标注了 {@link NotLogin @Anonymous} 的接口</b> — 在 SaInterceptor 的
     *       {@code preHandle} 中优先检查注解，命中则直接返回 {@code true}，跳过登录校验</li>
     *   <li><b>其余所有路径</b> — 必须登录</li>
     * </ol>
     *
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Sa-Token 路由拦截（子类化以支持 @Anonymous 注解）
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 登录校验（排除基础设施白名单）
            SaRouter.match("/**")
                    .notMatch(patterns.toArray(new String[0]))
                    .check(r -> StpUtil.checkLogin());
        }) {
            @Override
            public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
                // 检查 @Anonymous 注解（方法级 / 类级），命中则跳过 SaInterceptor 全部逻辑
                if (handler instanceof HandlerMethod hm) {
                    if (hm.hasMethodAnnotation(NotLogin.class) || hm.getBeanType().isAnnotationPresent(NotLogin.class)) {
                        return true;
                    }
                }
                return super.preHandle(request, response, handler);
            }
        }).addPathPatterns("/**");

        // 注册 JWT 上下文数据处理器
        registry.addInterceptor(jwtCurrentHandler).addPathPatterns("/**");
    }
}
