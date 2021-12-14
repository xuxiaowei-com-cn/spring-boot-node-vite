package cn.com.xuxiaowei.javaweb.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

/**
 * 默认 CSRF 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class DefaultCsrfTokenRepository {

    /**
     * 加密 Token {@link Bean}
     * <p>
     * 在 {@link CsrfTokenRepository} 对应的 {@link Bean} 不存在时，才会创建此 {@link Bean}
     *
     * @return 在 {@link CsrfTokenRepository} 对应的 {@link Bean} 不存在时，才会返回此 {@link Bean}
     * @see CookieCsrfTokenRepository#withHttpOnlyFalse()
     */
    @Bean
    @ConditionalOnMissingBean
    public CsrfTokenRepository csrfTokenRepository() {
        return CookieCsrfTokenRepository.withHttpOnlyFalse();
    }

}
