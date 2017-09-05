package controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
    @RequestMapping("/")
    public ModelAndView indePage (HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("RequestMapping = /");
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/index")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("RequestMapping = /index");

        String userName = (String) request.getSession().getAttribute("userName");
        ModelAndView mav = null;
        if (null == userName || userName.length() == 0) {
            mav = new ModelAndView("index");
        }else {
            mav = new ModelAndView("redirect:/listMovie.html");
        }

        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("RequestMapping = /login");
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("RequestMapping = /register");
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @RequestMapping("/check")
    public ModelAndView check(HttpSession session) {
        Integer i = (Integer) session.getAttribute("count");
        if (i == null)
            i = 0;
        i++;
        session.setAttribute("count", i);
        ModelAndView mav = new ModelAndView("check");
        return mav;
    }

    @RequestMapping("/clean")
    public ModelAndView clean(HttpSession session) {
        session.setAttribute("count", 0);
        ModelAndView mav = new ModelAndView("redirect:/check");
        return mav;
    }

    @RequestMapping("/fail")
    public ModelAndView fail(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Integer i = (Integer) session.getAttribute("count");
        if (i == null)
            i = 0;
        i++;
        session.setAttribute("count", i);

        ModelAndView mav = new ModelAndView("fail");

        String reason=request.getParameter("reason");
        mav.addObject("reason", reason);
        return mav;
    }

    @RequestMapping("/success")
    public ModelAndView success(HttpSession session) {
        ModelAndView mav = new ModelAndView("success");
        return mav;
    }

    @RequestMapping("/upLoadMovie")
    public ModelAndView upLoadMovie(HttpSession session) {
        ModelAndView mav = new ModelAndView("upLoadMovie");
        return mav;
    }
}