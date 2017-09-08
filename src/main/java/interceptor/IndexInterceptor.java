package interceptor;
import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.log4j.Logger;


public class IndexInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = Logger.getLogger(IndexInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.info("拦截器, 在访问Controller之前被调用 request.uri=" + request.getRequestURI());

        String strInfo = "\n\n";
        strInfo = strInfo + "浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有): " + request.getRequestURL() + "\n";
        strInfo = strInfo + "浏览器发出请求的资源名部分，去掉了协议和主机名: " + request.getRequestURI() + "\n";
        strInfo = strInfo + "请求行中的参数部分: " + request.getQueryString() + "\n";
        strInfo = strInfo + "浏览器所处于的客户机的IP地址: " + request.getRemoteAddr() + "\n";
        strInfo = strInfo + "浏览器所处于的客户机的主机名: " + request.getRemoteHost() + "\n";
        strInfo = strInfo + "浏览器所处于的客户机使用的网络端口: " + request.getRemotePort() + "\n";
        strInfo = strInfo + "服务器的IP地址: " + request.getLocalAddr() + "\n";
        strInfo = strInfo + "服务器的主机名: " + request.getLocalName() + "\n";
        strInfo = strInfo + "得到客户机请求方式: " + request.getMethod() + "\n";

        Enumeration<String> headerNames= request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            String value = request.getHeader(header);
            String outInfo = String.format("%s::%s%n",header,value);
            strInfo = strInfo + outInfo;
        }

        String userName = (String) request.getSession().getAttribute("userName");
        strInfo = strInfo + "用户名：userName=" +userName + "\n";

        logger.info(strInfo);

//        在本例，修改HelloServlet,使其获取头信息
//        访问HelloServlet获取如下头信息:
//        host: 主机地址
//        user-agent: 浏览器基本资料
//        accept: 表示浏览器接受的数据类型
//        accept-language: 表示浏览器接受的语言
//        accept-encoding: 表示浏览器接受的压缩方式，是压缩方式，并非编码
//        connection: 是否保持连接
//        cache-control: 缓存时限

        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle(), 在访问Controller之后，访问视图之前被调用,这里可以注入一个时间到modelAndView中，用于后续视图显示");
//        modelAndView.addObject("date","由拦截器生成的时间:" + new Date());
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion(), 在访问视图之后被调用");
    }

}