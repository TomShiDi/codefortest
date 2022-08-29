package com.demo.mybatisTest;

import com.demo.mybatisTest.entity.StudentInfoEntity;
import com.demo.mybatisTest.mapper.StudentInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/1
 * @Version 1.0
 */
public class LaunchMain {

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        Reader reader = null;
        SqlSessionFactory sqlSessionFactory = null;
        try {
            reader = Resources.getResourceAsReader("com/demo/mybatisTest/mybatisConf/mybatis_conf.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession();
            StudentInfoMapper studentInfoMapper = sqlSession.getMapper(StudentInfoMapper.class);
            StudentInfoEntity studentInfoEntity = studentInfoMapper.selectById(2);
            List<StudentInfoEntity> studentInfoEntityList = studentInfoMapper.selectAll(0, 10);
            System.out.println(studentInfoEntity.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
