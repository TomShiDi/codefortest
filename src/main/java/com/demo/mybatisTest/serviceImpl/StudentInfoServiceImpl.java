package com.demo.mybatisTest.serviceImpl;

import com.demo.mybatisTest.entity.StudentInfoEntity;
import com.demo.mybatisTest.mapper.StudentInfoMapper;
import com.demo.mybatisTest.service.StudentInfoService;
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
public class StudentInfoServiceImpl implements StudentInfoService {

    private static volatile SqlSession sqlSession;

    private static volatile StudentInfoMapper studentInfoMapper;

    public StudentInfoServiceImpl() {
        if (studentInfoMapper == null) {
            try {
                Reader reader = Resources.getResourceAsReader("com/demo/mybatisTest/mybatisConf/mybatis_conf.xml");
                SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
                sqlSession = factory.openSession();
                studentInfoMapper = sqlSession.getMapper(StudentInfoMapper.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public StudentInfoEntity selectById(int id) {
        return studentInfoMapper.selectById(id);
    }

    @Override
    public List<StudentInfoEntity> selectAll(int index,int pageSize) {
        return studentInfoMapper.selectAll(index, pageSize);
    }

    @Override
    public void deleteById(int id) {
        studentInfoMapper.deleteById(id);
        sqlSession.commit();
    }

    @Override
    public StudentInfoEntity insert(StudentInfoEntity studentInfoEntity) {
        studentInfoMapper.insert(studentInfoEntity);
        sqlSession.commit();
        return studentInfoMapper.selectById(studentInfoEntity.getId());
    }
}
