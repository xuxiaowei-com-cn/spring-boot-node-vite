package cn.com.xuxiaowei.javaweb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Login
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping
public class LoginRestController {

    /**
     * Login
     * <p>
     * 允许任何人访问
     *
     * @param request        请求
     * @param response       响应
     * @param session        用于创建 {@link HttpSession}
     * @param authentication 权限
     * @return 返回 数据
     */
    @PreAuthorize("permitAll()")
    @RequestMapping("/failure")
    public Map<String, Object> failure(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       Authentication authentication) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("uri", request.getRequestURI());

        return map;
    }

    /**
     * Login
     * <p>
     * 允许任何人访问
     *
     * @param request        请求
     * @param response       响应
     * @param session        用于创建 {@link HttpSession}
     * @param authentication 权限
     * @return 返回 数据
     */
    @PreAuthorize("permitAll()")
    @RequestMapping("/failure/forward")
    public Map<String, Object> failureForward(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                              Authentication authentication) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("uri", request.getRequestURI());

        return map;
    }

    /**
     * Login
     * <p>
     * 允许任何人访问
     *
     * @param request        请求
     * @param response       响应
     * @param session        用于创建 {@link HttpSession}
     * @param authentication 权限
     * @return 返回 数据
     */
    @PreAuthorize("permitAll()")
    @RequestMapping("/success/forward")
    public Map<String, Object> successForward(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                              Authentication authentication) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("uri", request.getRequestURI());

        return map;
    }

}
