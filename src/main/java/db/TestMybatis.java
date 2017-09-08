package db;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class TestMybatis {

    private static Logger logger = Logger.getLogger(TestMybatis.class);

    public static void main(String[] args) throws IOException {

                // 记录debug级别的信息
                logger.debug("This is debug message.");
                // 记录info级别的信息
                logger.info("This is info message.");
                // 记录error级别的信息
                logger.error("This is error message.");


//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();

//        Category c = new Category();
//        c.setName("我是 新增加的Category");
//        session.insert("addCategory",c);
//        listAll(session);


//        Category c1 = new Category();
//        c1.setId(3);
//        session.delete("deleteCategory",c1);
//        listAll(session);

//        Category c= session.selectOne("getCategory",5);
//        System.out.println(c.getName());

//        Category c= session.selectOne("getCategory",5);
//        c.setName("修改了的Category名稱");
//        session.update("updateCategory",c);
//        listAll(session);

//        UserInfo user = new UserInfo();
//        user.setName("sss");
//        user.setPassword("123456");
//        session.insert("addUserInfo",user);
//        listAllUserInfo(session);

//        UserInfo user = new UserInfo();
//        user.setName("sss");
//        session.delete("deleteUserInfo",user);
//        listAllUserInfo(session);

//        UserInfo user = new UserInfo();
//        user.setName("name1");
//        user.setPassword("");
//        session.delete("deleteUserInfo",user);
//        listAllUserInfo(session);

//        HswzMovie movie = new HswzMovie();
//        movie.setName("第一部片");
//        movie.setPath("hhh");
//        movie.setNetUrl("sdfsdf");
//        session.insert("addHswzMovie",movie);
//        listAllHswzMovie(session);

//        String i = "2";
//        HswzMovie movie1 = new HswzMovie();
//        movie1.setId(Integer.parseInt(i));
//        List<HswzMovie> movies = session.selectList("getHswzMovie",movie1);
//        for (HswzMovie movie : movies) {
//            System.out.println("id="+movie.getId() + " name="+movie.getName()+" path="+movie.getPath()+" netUrl="+movie.getNetUrl());
//        }

//
//        session.commit();
//        session.close();
    }

    private static void listAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    private static void listAllUserInfo(SqlSession session){
        List<UserInfo> us = session.selectList("listUserInfo");
        for (UserInfo user : us) {
            System.out.println("id="+user.getId() + " name="+user.getName()+" password="+user.getPassword());
        }
    }

    private static void listAllHswzMovie(SqlSession session){
        List<HswzMovie> movies = session.selectList("listHswzMovie");
        for (HswzMovie movie : movies) {
            System.out.println("id="+movie.getId() + " name="+movie.getName()+" path="+movie.getPath()+" netUrl="+movie.getNetUrl());
        }
    }


}