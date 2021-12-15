package cn.com.xuxiaowei.javaweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * CSRF
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
@RequestMapping("/csrf")
public class CsrfRestController {

    /**
     * CSRF
     *
     * @param request  请求
     * @param response 响应
     * @return 返回 Cookie CSRF
     */
    @RequestMapping
    private Map<String, Object> index(HttpServletRequest request, HttpServletResponse response) {

        return new HashMap<>(4);
    }

}
