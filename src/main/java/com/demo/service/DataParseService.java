package com.demo.service;

import com.demo.entity.ATeacherEntity;
import com.demo.entity.PeopleEntity;
import com.demo.entity.StudentEntity;
import com.demo.entity.TeacherEntity;

public interface DataParseService {

    PeopleEntity doHandle(String... params);

    TeacherEntity parseToTeacher(String... params);

    ATeacherEntity parseToATeacher(String... params);

    StudentEntity parseToStudent(String... params);

}
