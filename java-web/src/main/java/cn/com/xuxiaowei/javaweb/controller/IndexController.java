package cn.com.xuxiaowei.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主页
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Controller
public class IndexController {

    /**
     * 主页
     *
     * @param request  请求
     * @param response 响应
     */
    @RequestMapping("")
    public String path(HttpServletRequest request, HttpServletResponse response) {

        return "forward:index.html";
    }

}
