package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class helloController {
    @RequestMapping("/hello")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("hello world");

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "哈哈哈 Hello Spring MVC");
        return mav;
    }

    @RequestMapping("/jump")
    public ModelAndView handleRequest() {
        ModelAndView mav = new ModelAndView("redirect:/index");
        return mav;
    }
}