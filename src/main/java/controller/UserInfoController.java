package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.UserInfo;

@Controller
public class UserInfoController {

    @RequestMapping("/loginDo")
    public ModelAndView webApiLoginDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        System.out.println("name is: "+name + " password is: "+password);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        UserInfo user = new UserInfo();
        user.setName(name);
        List<UserInfo> us = session.selectList("getUserInfo",user);
        session.commit();
        session.close();

        ModelAndView mav = null;
        if (us.size() == 1){
            UserInfo getUser = us.get(0);
            if (getUser.getPassword().equals(password)){
                mav = new ModelAndView("redirect:/success.html");
            }else {
                mav = new ModelAndView("redirect:/fail.html");
            }
        }else {
            mav = new ModelAndView("redirect:/fail.html");
        }
        return mav;
    }

    @RequestMapping("/registerDo")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        System.out.println("name is: "+name + " password is: "+password);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        UserInfo user = new UserInfo();
        user.setName(name);
        List<UserInfo> us = session.selectList("getUserInfo",user);

        ModelAndView mav = null;
        if (us.size() == 0){
            UserInfo user1 = new UserInfo();
            user1.setName(name);
            user1.setPassword(password);
            session.insert("addUserInfo",user1);
            mav = new ModelAndView("redirect:/login.html");
        }else {
            mav = new ModelAndView("redirect:/fail.html");
        }

        session.commit();
        session.close();
        return mav;
    }
}
