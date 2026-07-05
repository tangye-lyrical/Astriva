package cn.astriva.st.annotation;

import java.lang.annotation.*;

/**
 * 匿名访问标记注解
 *
 * <p>标注在 Controller 的方法或类上，表示该接口无需登录即可访问。</p>
 * @author 棠野·Lyrical
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin { }
