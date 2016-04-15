package com.damon.mybatis.session;

import com.damon.mybatis.dao.UserMapper;
import com.damon.mybatis.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.InputStream;

/**
 * Created by dongjun.wei on 16/4/13.
 */
public class SqlSessionFactoryTest {

    @Test
    public void testSqlSessionFactoryApi() {
        String mybatisConfigFile = "mybatis-config.xml";
        InputStream resourceAsStream = SqlSessionFactoryTest.class.getClassLoader().getResourceAsStream(mybatisConfigFile);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        Integer uid = 123457;

        User user = new User();
        user.setUid(uid);
        user.setEmail("dj1@163.com");
        user.setPassword("123");
        user.setSalt("asdjfi");
        user.setAvatar("fajlsdjf");
        user.setPhone("1235433131");
        user.setUsername("alice1");
        user.setRealname("bo");
        user.setTid(123);
        user.setCid(123);
        user.setOnline(1);
        user.setStatus(1);
        user.setType(1);
        user.setRole(1);

        //int insert = sqlSession.insert("com.damon.mybatis.dao.UserMapper.insert", user);

        //默认情况下,是不会自动提交的
        //sqlSession.commit();
        //System.out.println(insert);

        User userGot = sqlSession.selectOne("com.damon.mybatis.dao.UserMapper.selectByPrimaryKey",uid);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.selectByPrimaryKey(uid);
        System.out.println(user1.getUsername());

        sqlSession.close();


        assertTrue(user.getEmail().equals(userGot.getEmail()));
    }
}
