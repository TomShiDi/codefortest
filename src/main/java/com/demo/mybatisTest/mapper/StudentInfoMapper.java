package com.demo.mybatisTest.mapper;

import com.demo.mybatisTest.entity.StudentInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/1
 * @Version 1.0
 */
public interface StudentInfoMapper {

    StudentInfoEntity selectById(int id);

    List<StudentInfoEntity> selectAll(@Param("index") int index, @Param("pageSize") int pageSize);

    void deleteById(int id);

    int insert(StudentInfoEntity studentInfoEntity);

    int update(StudentInfoEntity studentInfoEntity);
}
