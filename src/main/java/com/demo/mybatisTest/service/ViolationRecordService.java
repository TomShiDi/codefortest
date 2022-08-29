package com.demo.mybatisTest.service;

import com.demo.mybatisTest.entity.ViolationRecordEntity;

import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/2
 * @Version 1.0
 */
public interface ViolationRecordService {

    ViolationRecordEntity findById(int recordId);

    List<ViolationRecordEntity> findByStudentId(int studentId);

    List<ViolationRecordEntity> findByIdList(List<Integer> idList);

    List<ViolationRecordEntity> findByPunishment(String punishment);

    List<ViolationRecordEntity> findAll(int index, int pageSize);

    ViolationRecordEntity insert(ViolationRecordEntity violationRecordEntity);

    void deleteById(int recordId);
}
