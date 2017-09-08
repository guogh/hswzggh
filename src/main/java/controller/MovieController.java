package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interceptor.IndexInterceptor;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
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
public class MovieController {
    private static Logger logger = Logger.getLogger(IndexInterceptor.class);
    private static String moviesPath = "../movies/";

    @RequestMapping("/listMovie")
    public ModelAndView listMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SqlSession session = getSqlSessionForMybase();
        List<HswzMovie> movies = session.selectList("listHswzMovie");
        closeSqlSessionForMybase(session);

        for (HswzMovie movie : movies) {
            System.out.println("id=" + movie.getId() + " name=" + movie.getName() + " path=" + movie.getPath() + " netUrl=" + movie.getNetUrl());
        }

        ModelAndView mav = new ModelAndView("listMovie");
        mav.addObject("list", movies);
        return mav;
    }

    @RequestMapping("/playVideo")
    public ModelAndView playVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        HswzMovie movie = new HswzMovie();
        movie.setName(name);

        SqlSession session = getSqlSessionForMybase();
        List<HswzMovie> movies = session.selectList("getHswzMovie", movie);
        movie = movies.get(0);
        closeSqlSessionForMybase(session);

        logger.info("movie id=" + movie.getId() + " name=" + movie.getName() + " path=" + movie.getPath() + " netUrl=" + movie.getNetUrl());

        //媒体文件的类型
        String type = null;
        if (movie.getName().endsWith(".mp4") || movie.getName().endsWith(".MP4")){
            type = "video/mp4";
        }else  if (movie.getName().endsWith(".m3u8") || movie.getName().endsWith(".m3u")){
            type = "application/x-mpegURL";
        }else if (movie.getName().startsWith("rtmp:")){
            type = "rtmp/flv";
        }else {
            type = "video/mp4";
        }

        ModelAndView mav = new ModelAndView("playVideo");
        mav.addObject("movieSrc", "movies/"+movie.getName());
        mav.addObject("movieType", type);
        return mav;
    }

    @RequestMapping("/upLoadMovieDo")
    public ModelAndView upLoadMovieDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name ");
        String netUrl = request.getParameter("netUrl");
        String pathRoot = System.getProperty("evan.webapp") + moviesPath;
        System.out.println("parameter name = " + name + " netUrl = " + netUrl +"pathRoot = "+ pathRoot);

        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        String fileName = null;
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                System.out.println("file  =  " + file.getOriginalFilename());

                if (file != null) {
                    fileName = file.getOriginalFilename();
                    String path = pathRoot + fileName;
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");

        //写 数据库记录
        HswzMovie movie = new HswzMovie();
        movie.setName(fileName);
        movie.setPath( moviesPath + fileName);
        movie.setNetUrl(netUrl);
        movie.setUpMaster((String) request.getSession().getAttribute("userName"));

        SqlSession session = getSqlSessionForMybase();
        session.insert("addHswzMovie", movie);
        closeSqlSessionForMybase(session);

        System.out.println("movie id=" + movie.getId() + " name=" + movie.getName() + " path=" + movie.getPath() + " netUrl=" + movie.getNetUrl());
        ModelAndView mav = new ModelAndView("redirect:/listMovie.html");
        return mav;
    }

    /*
        打开 数据库连接
     */
    private SqlSession getSqlSessionForMybase(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    /*
        关闭数据库连接
     */
    private void closeSqlSessionForMybase(SqlSession session){
        session.commit();
        session.close();
    }
}