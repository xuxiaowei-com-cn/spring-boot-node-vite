package cn.com.xuxiaowei.javaweb.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 短信登录 权限
 *
 * @author xuxiaowei
 * @see UsernamePasswordAuthenticationToken 用户名、密码登录
 * @since 0.0.1
 */
public class SmsAbstractAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;

    private final Object credentials;

    private final WebAuthenticationDetails details;

    private final String phone;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param phone       手机号
     * @param principal   用户信息
     * @param credentials null
     * @param details     IP等，参见：{@link UsernamePasswordAuthenticationFilter#setDetails(HttpServletRequest, UsernamePasswordAuthenticationToken)}
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public SmsAbstractAuthenticationToken(String phone, UserDetails principal, Object credentials,
                                          WebAuthenticationDetails details,
                                          Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.details = details;
        this.phone = phone;

        // 必须设置
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public WebAuthenticationDetails getDetails() {
        return details;
    }

    @Override
    public UserDetails getPrincipal() {
        return principal;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
