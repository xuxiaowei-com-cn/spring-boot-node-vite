package cn.com.xuxiaowei.javaweb.controller;

import cn.com.xuxiaowei.javaweb.authentication.SmsAbstractAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信登录
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsRestController {

    /**
     * 短信登录手机号
     */
    public final String SMS_LOGIN_PHONE = "SMS_LOGIN_PHONE";

    /**
     * 短信登录验证码
     */
    public final String SMS_LOGIN_CODE = "SMS_LOGIN_CODE";

    /**
     * 根据手机号获取验证码
     *
     * @param request  请求
     * @param response 响应
     * @param phone    手机号
     * @return 返回 验证码
     */
    @PreAuthorize("permitAll")
    @RequestMapping("/code")
    public Map<String, Object> code(HttpServletRequest request, HttpServletResponse response, String phone) {
        Map<String, Object> map = new HashMap<>(4);

        HttpSession session = request.getSession();

        int max = 1000000, min = 100000;
        int code = (int) (Math.random() * (max - min) + min);

        // 1、请将不同业务的短信验证码区分开
        // 2、请设置有效时间
        // 3、请设置失败次数
        // 4、用完清空

        session.setAttribute(SMS_LOGIN_CODE, code);
        session.setAttribute(SMS_LOGIN_PHONE, phone);

        log.info("手机号：{} 短信验证码：{}", phone, code);

        map.put("msg", "短信验证码已发送成功。");
        map.put("phone", phone);

        return map;
    }

    /**
     * 短信登录
     *
     * @param request  请求
     * @param response 响应
     * @param code     短信验证码
     * @return 返回 用户信息
     */
    @PreAuthorize("permitAll")
    @RequestMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, String code) {
        Map<String, Object> map = new HashMap<>(4);

        HttpSession session = request.getSession();
        String sessionCode = session.getAttribute(SMS_LOGIN_CODE) + "";
        String sessionPhone = session.getAttribute(SMS_LOGIN_PHONE) + "";

        // 1、请将不同业务的短信验证码区分开
        // 2、请设置有效时间
        // 3、请设置失败次数
        // 4、用完清空

        if (StringUtils.hasText(code) && code.equals(sessionCode)) {

            SecurityContext context = SecurityContextHolder.getContext();

            // 用户IP等
            WebAuthenticationDetails credentials = new WebAuthenticationDetails(request);

            // 短信登录权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            // 给一个权限
            authorities.add(new SimpleGrantedAuthority("ROLE_SMS"));

            // 根据手机号查询用户信息
            String username = String.format("手机号：%s 对应的用户名", sessionPhone);
            String password = "";
            User user = new User(username, password, authorities);

            // 创建短信验证码登录权限
            SmsAbstractAuthenticationToken smsAbstractAuthenticationToken = new SmsAbstractAuthenticationToken(
                    sessionPhone, user, null, credentials, authorities);

            // 用于测试，不推荐使用
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user, credentials, authorities);
            // 设置IP
            usernamePasswordAuthenticationToken.setDetails(credentials);

            // 设置权限
            context.setAuthentication(smsAbstractAuthenticationToken);

            String msg = String.format("手机号：%s 登录成功", sessionPhone);

            log.info(msg);

            map.put("msg", msg);

        } else {

            String msg = String.format("手机号：%s 登录失败", sessionPhone);

            log.warn(msg);

            map.put("msg", msg);

        }

        return map;
    }

}
