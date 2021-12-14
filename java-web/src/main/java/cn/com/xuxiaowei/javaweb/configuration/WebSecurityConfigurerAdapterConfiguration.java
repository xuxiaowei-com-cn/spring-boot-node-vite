package cn.com.xuxiaowei.javaweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Security 配置
 *
 * @author xuxiaowei
 * @see FormLoginConfigurer#loginPage(String) 设置自定义登录页面，禁用默认登录页面
 * @see DefaultLoginPageGeneratingFilter 默认登录页面
 * @see UsernamePasswordAuthenticationFilter 用户名密码认证过滤器
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private CsrfTokenRepository csrfTokenRepository;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setCsrfTokenRepository(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户密码编辑器
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 查询登录用户
        auth.userDetailsService(userDetailsService).passwordEncoder(delegatingPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/**/**.js");
        web.ignoring().antMatchers("/**/**.css");
        web.ignoring().antMatchers("/**/**.mjs");
        web.ignoring().antMatchers("/**/**.map");
        // vue 静态资源路径
        web.ignoring().antMatchers("/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 认证后访问
        http.authorizeRequests().antMatchers("/authenticated").authenticated();

        // 设置登录页面，禁用默认登录页面过滤器渲染
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureForwardUrl("/failure/forward")
                .failureUrl("/failure")
                .successForwardUrl("/success/forward")
                .permitAll();

        // CSRF 配置
        http.csrf().csrfTokenRepository(csrfTokenRepository);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        Map<String, CorsConfiguration> corsConfigurations = new HashMap<>(4);

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000", "http://192.168.5.3:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name()));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

        corsConfigurations.put("/**", corsConfiguration);

        urlBasedCorsConfigurationSource.setCorsConfigurations(corsConfigurations);
        http.cors().configurationSource(urlBasedCorsConfigurationSource);

    }

}
