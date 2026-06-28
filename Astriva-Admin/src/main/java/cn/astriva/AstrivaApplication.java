package cn.astriva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 星驰快速开发模板启动类
 *
 * @author Mr. Tao
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class AstrivaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstrivaApplication.class, args);
		System.out.println("〘星驰〙启动成功！");
	}

}
