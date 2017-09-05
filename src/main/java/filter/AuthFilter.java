package filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("登陆验证 过滤器 初始化");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        System.out.println("AuthFilter uri = "+ uri);

        if (uri.endsWith("login.html") || uri.endsWith("login") || uri.endsWith("/") || uri.endsWith("/webApi/loginDo")) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.endsWith("register.html") || uri.endsWith("/webApi/registerDo") || uri.endsWith("index.html")) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.endsWith("fail.html")) {
            chain.doFilter(request, response);
            return;
        }

        String userName = (String) request.getSession().getAttribute("userName");
        if (null == userName || userName.length() == 0) {
            System.out.println(userName + "没有登陆，不允许访问资源" + uri);
            response.sendRedirect("login.html");
            return;
        }

        System.out.println("userName = " + userName + "已经登陆过了" + "允许访问资源" + uri);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}