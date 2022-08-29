package com.demo.mybatisTest.service;

import com.demo.mybatisTest.entity.StudentInfoEntity;

import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/1
 * @Version 1.0
 */
public interface StudentInfoService {

    StudentInfoEntity selectById(int id);

    List<StudentInfoEntity> selectAll(int index,int pageSize);

    void deleteById(int id);

    StudentInfoEntity insert(StudentInfoEntity studentInfoEntity);
}
