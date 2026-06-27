package cn.astriva.config;

import cn.astriva.handler.JwtCurrentHandler;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
     * 白名单路径
     */
    private final List<String> patterns = List.of(
            // 登录相关接口
            "/auth/login",
            "/auth/register",
            // Swagger 相关接口
            "/swagger-ui/**",
            "/v3/api-docs/**",
            // 静态资源
            "/doc.html",
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
     * SaInterceptor 是 Sa-Token 的路由拦截器，它会根据 SaRouter 定义的规则
     * 对请求进行匹配和处理。每条规则包含三个要素：
     *   - match(pattern, handler)  ：匹配路径并执行处理
     *   - notMatch(pattern, handler)：排除路径并执行处理
     *   - check(checker)            ：无条件执行安全检查
     *
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 校验是否登录
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 放行白名单
            SaRouter.notMatch(patterns).back();

            // 登录校验
            SaRouter.match("/**", r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
        // 注册 JWT 上下文数据处理器
        registry.addInterceptor(jwtCurrentHandler).addPathPatterns("/**");
    }
}
