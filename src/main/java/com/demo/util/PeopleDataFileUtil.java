package com.demo.util;

import com.demo.entity.ATeacherEntity;
import com.demo.entity.PeopleEntity;
import com.demo.entity.StudentEntity;
import com.demo.entity.TeacherEntity;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件数据存储和读取工具类
 */
public class PeopleDataFileUtil {

    /**
     * 数据存储函数
     * @param path 路径
     * @param fileName 文件名
     * @param peopleEntityList 待保存的数据
     * @throws IOException
     */
    public static void dataSave(String path, String fileName, List<PeopleEntity> peopleEntityList)throws IOException {
        Gson gson = new Gson();//gson数据解析实例类
        List<PeopleEntity> fileData =null;
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        File file = new File(path + fileName);
        if (file.exists() && file.isFile()) {
            fileData = readFileData(file.getPath());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);//以新建的方式添加数据
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        if (peopleEntityList != null) {
            if (fileData != null) {
                peopleEntityList.addAll(fileData);
            }
            bufferedOutputStream.write((gson.toJson(peopleEntityList)).getBytes());//数据输出到文本
        }
        bufferedOutputStream.close();
        fileOutputStream.close();
        System.out.println("数据存储完毕,文件路径为" + file.getPath());
    }

    /**
     * 读取指定路径文件中的数据
     * @param path 路径
     * @return
     * @throws IOException
     */
    public static List<PeopleEntity> readFileData(String path) throws IOException{
        File file = new File(path);
        Gson gson = new Gson();
        List<PeopleEntity> readData = new ArrayList<>();
        if (!file.exists()) {
            System.out.println("失败---文件路径不存在");
            return null;
        }
        if (file.isDirectory()) {
            System.out.println("失败---当前路径为目录");
            return null;
        }
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        JsonReader jsonReader = gson.newJsonReader(inputStreamReader);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            Map<String, Object> map = gson.fromJson(jsonReader, Object.class);//先将gson数据转化为Map数据
            if ("学生".equals((String) map.get("type"))) {
                StudentEntity studentEntity = new StudentEntity(
                        (String) map.get("id"),
                        (String) map.get("enterDate"),
                        (String) map.get("institution"),
                        (String) map.get("systemNumber"),
                        (String) map.get("major"),
                        (String) map.get("type"),
                        (String) map.get("name"),
                        map.get("age").toString().substring(0, map.get("age").toString().length() - 2),
                        (String) map.get("sex"),
                        (String) map.get("address"),
                        (String) map.get("connection")
                );//转化成学生数据实体
                readData.add(studentEntity);
            }
            if ("教师".equals(map.get("type"))) {
                TeacherEntity teacherEntity = new TeacherEntity(
                        (String) map.get("name"),
                        map.get("age").toString().substring(0, map.get("age").toString().length() - 2),
                        (String) map.get("sex"),
                        (String) map.get("address"),
                        (String) map.get("connection"),
                        (String) map.get("id"),
                        (String) map.get("type"),
                        (String) map.get("systemNumber"),
                        (String) map.get("enterDate"),
                        (String) map.get("institution")
                );//转化成教师数据实体
                readData.add(teacherEntity);
            }
            if ("辅教".equals(map.get("type"))) {
                ATeacherEntity aTeacherEntity = new ATeacherEntity(
                        (String) map.get("name"),
                        map.get("age").toString().substring(0, map.get("age").toString().length() - 2),
                        (String) map.get("sex"),
                        (String) map.get("address"),
                        (String) map.get("connection"),
                        (String) map.get("id"),
                        (String) map.get("type"),
                        (String) map.get("systemNumber"),
                        (String) map.get("enterDate"),
                        (String) map.get("institution")
                );//转化为辅教数据实体
                readData.add(aTeacherEntity);
            }
        }
        inputStream.close();
        inputStream.close();
        jsonReader.close();
        return readData;
    }
}
