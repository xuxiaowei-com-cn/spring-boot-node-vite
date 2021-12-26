package cn.com.xuxiaowei.javaweb.controller;

import cn.com.xuxiaowei.javaweb.authentication.SmsAbstractAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    /**
     * 用户信息
     *
     * @param request        请求
     * @param response       响应
     * @param authentication 权限
     * @return 返回 用户信息
     */
    @PreAuthorize("authenticated")
    @RequestMapping("/info")
    public Map<String, Object> index(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Map<String, Object> map = new HashMap<>(4);

//         SecurityContext context = SecurityContextHolder.getContext();
//         Authentication authentication = context.getAuthentication();

        String name = authentication.getName();
        Object details = authentication.getDetails();
        // 本身为空
        Object credentials = authentication.getCredentials();

        if (details instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) details;
            String remoteAddress = webAuthenticationDetails.getRemoteAddress();
            map.put("remoteAddress", remoteAddress);
        }

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            map.put("type", "用户名密码登录");
        } else if (authentication instanceof SmsAbstractAuthenticationToken) {

            String phone = ((SmsAbstractAuthenticationToken) authentication).getPhone();

            map.put("type", "短信验证码登录");
            map.put("phone", phone);
        }

        map.put("name", name);

        return map;
    }

}
