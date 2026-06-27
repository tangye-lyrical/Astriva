package cn.astriva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * WebMvc 全局配置类
 *
 * <p>集中管理 Web 层通用配置，当前包含：
 * <ul>
 *   <li><b>跨域（CORS）配置</b> — 前后端分离场景下，允许浏览器跨域请求后端 API</li>
 * </ul>
 *
 * @author 棠野·Lyrical
 */
@Configuration
public class WebMvcConfig {

    /**
     * 跨域过滤器 —— 允许来自任意域名的跨域请求
     *
     * <p><b>为什么需要 CORS？</b><br>
     * 浏览器同源策略会阻止前端页面（如 http://localhost:5173）向不同源的
     * 后端 API（如 http://localhost:8080）发送 AJAX 请求。CORS 是服务器
     * 端告知浏览器“允许该跨域请求”的标准机制。
     *
     * <p><b>配置策略说明：</b>
     * <ul>
     *   <li>{@code allowCredentials(true)} — 允许跨域携带 Cookie / Authorization 等凭据</li>
     *   <li>{@code addAllowedOriginPattern("*")} — 允许所有来源（生产环境建议收窄）
     *       使用 Pattern 而非 {@code addAllowedOrigin("*")} 是为了兼容
     *       {@code allowCredentials(true)} 时的浏览器限制</li>
     *   <li>{@code addAllowedHeader("*")} — 允许所有请求头（含自定义 header）</li>
     *   <li>{@code addAllowedMethod("*")} — 允许所有 HTTP 方法（GET/POST/PUT/DELETE 等）</li>
     *   <li>{@code registerCorsConfiguration("/**", config)} — 对所有路径生效</li>
     * </ul>
     *
     * <p><b>生产环境建议：</b><br>
     * 将 {@code addAllowedOriginPattern("*")} 替换为具体的前端域名列表：
     * <pre>{@code
     * config.addAllowedOrigin("https://admin.yourdomain.com");
     * config.addAllowedOrigin("https://app.yourdomain.com");
     * }</pre>
     *
     * @return CorsFilter 实例，由 Spring Boot 自动注册
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许跨域请求携带身份凭证（Cookie / Authorization header）
        config.setAllowCredentials(true);
        // 允许所有来源（开发环境）；生产环境应替换为具体域名
        config.addAllowedOriginPattern("*");
        // 允许所有请求头
        config.addAllowedHeader("*");
        // 允许所有 HTTP 方法
        config.addAllowedMethod("*");

        // 对全部路径注册此跨域配置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
