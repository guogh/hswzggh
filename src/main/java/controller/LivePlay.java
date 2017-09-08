package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.HswzMovie;
import upLoadFile.*;

@Controller
public class LivePlay {

    @RequestMapping("/liveVideo")
    public ModelAndView liveVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        System.out.println("parameter name = " + name);

        HswzMovie movie = new HswzMovie();
        movie.setName(name);
        System.out.println("movie id=" + movie.getId() + " name=" + movie.getName() + " path=" + movie.getPath() + " netUrl=" + movie.getNetUrl());

        String rtmpSrc = null;
        String medaiType = null;
        if (name.equals("香港卫视")){
            rtmpSrc = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
            medaiType = "rtmp/flv";
        }else if (name.equals("在宥直播hls")){
//            rtmpSrc = "http://10.0.66.11:8090/hls/ziayouzhibo.m3u8";
            rtmpSrc = "http://116.196.92.138:8090/hls/ziayouzhibo.m3u8";
            medaiType = "application/x-mpegURL";
        }else if (name.equals("在宥直播rtmp")){
//            rtmpSrc = "rtmp://10.0.66.11:1935/rtmplive/ziayouzhibo";
            rtmpSrc = "rtmp://116.196.92.138:1935/rtmplive/ziayouzhibo";
            medaiType = "rtmp/flv";
        }else {
            rtmpSrc = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
            medaiType = "rtmp/flv";
        }

        ModelAndView mav = new ModelAndView("playVideo");
        mav.addObject("movieSrc", rtmpSrc);
        mav.addObject("movieType", medaiType);

        return mav;
    }

    @RequestMapping("/livePlayList")
    public ModelAndView liveList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HswzMovie movie1 = new HswzMovie();
        movie1.setName("香港卫视");
        movie1.setId(1);
        movie1.setNetUrl("香港卫视在线直播");

        HswzMovie movie2 = new HswzMovie();
        movie2.setName("在宥直播hls");
        movie2.setId(2);
        movie2.setNetUrl("小华直播撸代码");

        HswzMovie movie3 = new HswzMovie();
        movie3.setName("在宥直播rtmp");
        movie3.setId(3);
        movie3.setNetUrl("小华直播撸代码rtmp");

        List<HswzMovie> movies = new ArrayList<HswzMovie>() ;
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        for (HswzMovie movie : movies) {
            System.out.println("id=" + movie.getId() + " name=" + movie.getName() + " path=" + movie.getPath() + " netUrl=" + movie.getNetUrl());
        }

        ModelAndView mav = new ModelAndView("livePlayList");
        mav.addObject("list", movies);
        return mav;
    }
}


