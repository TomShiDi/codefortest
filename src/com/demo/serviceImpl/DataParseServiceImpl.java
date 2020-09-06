package com.demo.serviceImpl;

import com.demo.entity.ATeacherEntity;
import com.demo.entity.PeopleEntity;
import com.demo.entity.StudentEntity;
import com.demo.entity.TeacherEntity;
import com.demo.service.DataParseService;

public class DataParseServiceImpl implements DataParseService {

    @Override
    public PeopleEntity doHandle(String... params) {
        String temp;
        if (!(temp = params[params.length - 1]).isEmpty() && temp.startsWith("S")) {
            return parseToStudent(params);
        } else if (!temp.isEmpty() && temp.startsWith("T")) {
            return parseToTeacher(params);
        } else if (!temp.isEmpty() && temp.startsWith("A")) {
            return parseToATeacher(params);
        }else {
            return null;
        }
    }

    @Override
    public TeacherEntity parseToTeacher(String... params) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName(params[0]);
        teacherEntity.setAge(Integer.parseInt(params[1]));
        teacherEntity.setSex(params[2]);
        teacherEntity.setAddress(params[3]);
        teacherEntity.setConnection(params[4]);
        teacherEntity.setId(params[5].substring(9, params[5].length()));
        teacherEntity.setEnterDate(params[5].substring(1, 7));
        teacherEntity.setInstitution(params[5].substring(7, 9));
        teacherEntity.setSystemNumber(params[5]);
        teacherEntity.setType("教师");
        return teacherEntity;
    }

    @Override
    public ATeacherEntity parseToATeacher(String... params) {
        ATeacherEntity aTeacherEntity = new ATeacherEntity();
        aTeacherEntity.setName(params[0]);
        aTeacherEntity.setAge(Integer.parseInt(params[1]));
        aTeacherEntity.setSex(params[2]);
        aTeacherEntity.setAddress(params[3]);
        aTeacherEntity.setConnection(params[4]);
        aTeacherEntity.setId(params[5].substring(9, params[5].length()));
        aTeacherEntity.setEnterDate(params[5].substring(1, 7));
        aTeacherEntity.setInstitution(params[5].substring(7, 9));
        aTeacherEntity.setSystemNumber(params[5]);
        aTeacherEntity.setType("辅教");
        return aTeacherEntity;
    }

    @Override
    public StudentEntity parseToStudent(String... params) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(params[0]);
        studentEntity.setAge(Integer.parseInt(params[1]));
        studentEntity.setSex(params[2]);
        studentEntity.setAddress(params[3]);
        studentEntity.setConnection(params[4]);
        studentEntity.setId(params[5].substring(params[5].length() - 2, params[5].length()));
        studentEntity.setEnterDate(params[5].substring(1, 7));
        studentEntity.setInstitution(params[5].substring(7, 9));
        studentEntity.setMajor(params[5].substring(9, 11));
        studentEntity.setSystemNumber(params[5]);
        studentEntity.setType("学生");
        return studentEntity;
    }
}
