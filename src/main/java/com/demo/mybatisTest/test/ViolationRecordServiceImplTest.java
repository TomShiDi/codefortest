package com.demo.mybatisTest.test;

import com.demo.mybatisTest.entity.ViolationRecordEntity;
import com.demo.mybatisTest.service.ViolationRecordService;
import com.demo.mybatisTest.serviceImpl.ViolationRecordServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/2
 * @Version 1.0
 */
public class ViolationRecordServiceImplTest {

    private static ViolationRecordService violationRecordService;

    @BeforeClass
    public static void doBeforeClass() {
        if (violationRecordService == null) {
            violationRecordService = new ViolationRecordServiceImpl();
        }
    }

    @Test
    public void findById() {
        ViolationRecordEntity result = violationRecordService.findById(4);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByStudentId() {
        List<ViolationRecordEntity> result = violationRecordService.findByStudentId(2);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByIdList() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(4);
        idList.add(5);
        List<ViolationRecordEntity> result = violationRecordService.findByIdList(idList);
        Assert.assertNotEquals(1, result.size());
    }

    @Test
    public void findByPunishment() {
        List<ViolationRecordEntity> result = violationRecordService.findByPunishment("1 or 1=1");

        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findAll() {
        int index = 2;
        int pageSize = 3;
        List<ViolationRecordEntity> result = violationRecordService.findAll(index, pageSize);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void insert() {
        ViolationRecordEntity violationRecordEntity = new ViolationRecordEntity();
        violationRecordEntity.setStudentId(2);
        violationRecordEntity.setEventDescription("早签迟到");
        violationRecordEntity.setPunishment("警告");
        ViolationRecordEntity result = violationRecordService.insert(violationRecordEntity);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteById() {
        violationRecordService.deleteById(3);
    }
}