package cn.com.xuxiaowei.javaweb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebMvcConfigurerConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        CorsRegistration corsRegistration = registry.addMapping("/**");

        corsRegistration.allowCredentials(true);
        corsRegistration.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000", "http://192.168.5.3:3000");
        corsRegistration.allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name());
        corsRegistration.allowedHeaders("*");

    }

}
