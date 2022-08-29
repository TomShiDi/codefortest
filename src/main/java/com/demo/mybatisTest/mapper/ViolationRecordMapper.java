package com.demo.mybatisTest.mapper;

import com.demo.mybatisTest.entity.ViolationRecordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/2
 * @Version 1.0
 */
public interface ViolationRecordMapper {

    ViolationRecordEntity findById(int recordId);

    List<ViolationRecordEntity> findByStudentId(int studentId);

    List<ViolationRecordEntity> findByIdList(List<Integer> idList);

    List<ViolationRecordEntity> findByPunishment(@Param("punishment") String punishment);

    List<ViolationRecordEntity> findAll(@Param("index") int index, @Param("pageSize") int pageSize);

    int insert(ViolationRecordEntity violationRecordEntity);

    void deleteById(int recordId);
}
