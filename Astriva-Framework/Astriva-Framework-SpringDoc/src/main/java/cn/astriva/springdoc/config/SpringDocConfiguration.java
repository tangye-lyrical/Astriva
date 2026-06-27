package cn.astriva.springdoc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * SpringDoc OpenAPI 配置类
 *
 * @author 棠野·Lyrical
 */
@Configuration
public class SpringDocConfiguration {

    /**
     * 构建 OpenAPI 信息
     * <p>
     * 该 Bean 会替换 springdoc 默认的 OpenAPI 对象，提供自定义的 API 文档信息、
     * 服务地址以及全局 JWT 认证方案，使得 Swagger UI 中的每个接口都默认带有 Authorize 按钮。
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // API 基本信息
                .info(new Info().title("Astriva API").version("1.0.0").description("『星辰之远，驰速之快』星驰快速开发模板"))
                // 服务器地址
                .servers(List.of(new Server().url("/").description("默认服务器")))
                // 为所有接口添加 BearerAuth 安全要求，Swagger UI 会显示 "Authorize" 按钮
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                // 安全方案定义
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
