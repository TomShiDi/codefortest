package com.demo.mybatisTest.serviceImpl;

import com.demo.mybatisTest.entity.ViolationRecordEntity;
import com.demo.mybatisTest.mapper.ViolationRecordMapper;
import com.demo.mybatisTest.service.ViolationRecordService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/2
 * @Version 1.0
 */
public class ViolationRecordServiceImpl implements ViolationRecordService {

    private static SqlSession sqlSession;

    private static ViolationRecordMapper violationRecordMapper;

    public ViolationRecordServiceImpl() {
        if (violationRecordMapper == null) {
            try {
                Reader reader = Resources.getResourceAsReader("com/demo/mybatisTest/mybatisConf/mybatis_conf.xml");
                SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
                sqlSession = factory.openSession();
                violationRecordMapper = sqlSession.getMapper(ViolationRecordMapper.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ViolationRecordEntity findById(int recordId) {
        return violationRecordMapper.findById(recordId);
    }

    @Override
    public List<ViolationRecordEntity> findByStudentId(int studentId) {
        return violationRecordMapper.findByStudentId(studentId);
    }

    @Override
    public List<ViolationRecordEntity> findByIdList(List<Integer> idList) {
        return violationRecordMapper.findByIdList(idList);
    }

    @Override
    public List<ViolationRecordEntity> findByPunishment(String punishment) {
        return violationRecordMapper.findByPunishment(punishment);
    }

    @Override
    public List<ViolationRecordEntity> findAll(int index, int pageSize) {
        return violationRecordMapper.findAll(index, pageSize);
    }

    @Override
    public ViolationRecordEntity insert(ViolationRecordEntity violationRecordEntity) {
        violationRecordMapper.insert(violationRecordEntity);
        sqlSession.commit();
        return violationRecordMapper.findById(violationRecordEntity.getRecordId());
    }

    @Override
    public void deleteById(int recordId) {
        violationRecordMapper.deleteById(recordId);
        sqlSession.commit();
    }
}
