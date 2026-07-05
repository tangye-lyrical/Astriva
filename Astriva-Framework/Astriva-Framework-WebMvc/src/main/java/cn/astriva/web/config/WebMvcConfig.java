package cn.astriva.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * WebMvc 全局配置类
 *
 * @author 棠野·Lyrical
 */
@Configuration
public class WebMvcConfig {

    /**
     * 跨域过滤器 —— 允许来自任意域名的跨域请求
     *
     * @return CorsFilter 实例，由 Spring Boot 自动注册
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许跨域请求携带身份凭证
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
