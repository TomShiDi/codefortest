package com.demo.mybatisTest.test;

import com.demo.mybatisTest.serviceImpl.StudentInfoServiceImpl;
import com.demo.mybatisTest.entity.StudentInfoEntity;
import com.demo.mybatisTest.service.StudentInfoService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @Author TomShiDi
 * @Since 2019/6/1
 * @Version 1.0
 */
public class StudentInfoServiceImplTest {

    private static StudentInfoService studentInfoService;

    @BeforeClass
    public static void doBeforeClass() {
        if (studentInfoService == null) {
            studentInfoService = new StudentInfoServiceImpl();
        }
    }

    @Test
    public void selectById() {
        StudentInfoEntity result = studentInfoService.selectById(2);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectAll() {
        int index = 2;
        int size = 3;
        List<StudentInfoEntity> studentInfoEntityList = studentInfoService.selectAll(index, size);
        Assert.assertNotEquals(0, studentInfoEntityList.size());
    }

    @Test
    public void deleteById() {
        studentInfoService.deleteById(4);
    }

    @Test
    public void insert() {
        StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setAge(30);
        studentInfoEntity.setStudentName("管理员");
        studentInfoEntity.setSex("男");
        StudentInfoEntity result = studentInfoService.insert(studentInfoEntity);
        Assert.assertNotNull(result);
    }
}