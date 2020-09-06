package com.demo.serviceImpl;

import com.demo.entity.ATeacherEntity;
import com.demo.entity.PeopleEntity;
import com.demo.entity.StudentEntity;
import com.demo.entity.TeacherEntity;
import com.demo.service.DataParseService;
import com.demo.service.MenuService;
import com.demo.util.PeopleDataFileUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 菜单功能实现类
 * 1: 手动输入数据
 * 2: 存储当前输入数据
 * 3: 读取指定文件数据
 * 4: 查询数据
 */
public class MenuServiceImpl implements MenuService {

    private static MenuService menuService = new MenuServiceImpl();//使用单例模式

    private List<StudentEntity> studentEntityList;//学生数据数组

    private List<ATeacherEntity> aTeacherEntityList;//辅教数据数组

    private List<TeacherEntity> teacherEntityList;//教师数据数组

    private int N = 0;//输入的N值

    private Scanner scanner;

    //初始化数据
    private MenuServiceImpl() {
        this.teacherEntityList = new ArrayList<>();
        this.aTeacherEntityList = new ArrayList<>();
        this.studentEntityList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * 打印出简单的菜单
     */
    @Override
    public void showMenu() {
        System.out.println("1: 手动输入数据");
        System.out.println("2: 存储当前输入数据");
        System.out.println("3: 读取指定文件数据");
        System.out.println("4: 查询数据");
//        System.out.println("q: 返回上一级菜单");
    }

    /**
     * 根据路径读取json数据
     */
    @Override
    public void readFileData() {
        String path;
        List<PeopleEntity> peopleEntityList;
        System.out.println("请输入待读取的文件路径(需要指定具体的文件):");
        while ((path = scanner.nextLine()).isEmpty()) {
        }//保证输入的路径不为空
        try {
            peopleEntityList = PeopleDataFileUtil.readFileData(path);//使用工具类读取数据
            if (peopleEntityList != null) {
                //对读取的数据分类存储
                peopleEntityList.forEach(e -> {
                    if (e instanceof StudentEntity) {
                        studentEntityList.add((StudentEntity) e);
                    }
                    if (e instanceof TeacherEntity) {
                        teacherEntityList.add((TeacherEntity) e);
                    }
                    if (e instanceof ATeacherEntity) {
                        aTeacherEntityList.add((ATeacherEntity) e);
                    }
                });
            }
            System.out.println("数据加载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 手动输入数据
     */
    @Override
    public void dataInput() {
        String temp;
        DataParseService dataParseService = new DataParseServiceImpl();
        while (N == 0) {
            System.out.println("请输入教师数N:");
            N = scanner.nextInt();
            scanner.nextLine();
            System.out.println("N: " + N);
        }
        System.out.println("请按顺序输入(英文,隔开)：姓名，年龄，性别(M/F)，住址，联系方式，教工号(如T20190210001)");
        System.out.println("输入q然后回车退出输入");
        while (!"q".equals(temp = scanner.nextLine())) {
            String []dataArray = temp.split(",");
            PeopleEntity peopleEntity = dataParseService.doHandle(dataArray);
            if (peopleEntity instanceof TeacherEntity) {
                teacherEntityList.add((TeacherEntity) peopleEntity);
                System.out.println("您输入的数据为: " + peopleEntity.toString());
            }
            if (peopleEntity instanceof ATeacherEntity) {
                aTeacherEntityList.add((ATeacherEntity) peopleEntity);
                System.out.println("您输入的数据为: " + peopleEntity.toString());
            }
            if (peopleEntity instanceof StudentEntity) {
                studentEntityList.add((StudentEntity) peopleEntity);
                System.out.println("您输入的数据为: " + peopleEntity.toString());
            }
        }
        System.out.println("退出输入");
    }

    /**
     * 把输入的数据存储至文件
     */
    @Override
    public void dataSave() {
        System.out.println("请输入有效的文件路径(目录即可，自动生成peopleData.txt文件)");
        String path;
        while ((path = scanner.nextLine()).isEmpty()) {
        }
        List<PeopleEntity> peopleEntityList = new ArrayList<>(teacherEntityList);
        peopleEntityList.addAll(aTeacherEntityList);
        peopleEntityList.addAll(studentEntityList);
        try {
            PeopleDataFileUtil.dataSave(path, "peopleData.txt", peopleEntityList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 数据查询(精确查询和模糊查询)
     */
    @Override
    public void dataQuery() {
        String temp;
        String condition;
        List<PeopleEntity> queryData = new ArrayList<>();

        System.out.println("查询模式 :1.模糊查询(包含查询信息即可) 2.精确查询-职工号查询(只返回完全匹配的结果)");
        while ((temp = scanner.nextLine()).isEmpty()) {
        }//获取模式选择数据

        if ("1".equals(temp)) {
            System.out.println("模糊查询条件:");
            while ((condition = scanner.nextLine()).isEmpty()) {
            }
            final String conditionFinal = condition;
            studentEntityList.forEach(e->{
                if (e.toString().contains(conditionFinal)) {
                    queryData.add(e);
                }
            });
            teacherEntityList.forEach(e->{
                if (e.toString().contains(conditionFinal)) {
                    queryData.add(e);
                }
            });
            aTeacherEntityList.forEach(e->{
                if (e.toString().contains(conditionFinal)) {
                    queryData.add(e);
                }
            });
        }
        if ("2".equals(temp)) {
            System.out.println("精确查询条件(职工号):");
            while ((condition = scanner.nextLine()).isEmpty()) {
            }
            final String conditionFinal = condition;
            if (conditionFinal.startsWith("S")) {
                studentEntityList.forEach(e->{
                    if(conditionFinal.equals(e.getSystemNumber())){
                        queryData.add(e);
                    }
                });
            }
            if (conditionFinal.startsWith("T")) {
                teacherEntityList.forEach(e->{
                    if(conditionFinal.equals(e.getSystemNumber())){
                        queryData.add(e);
                    }
                });
            }
            if (conditionFinal.startsWith("A")) {
                aTeacherEntityList.forEach(e->{
                    if(conditionFinal.equals(e.getSystemNumber())){
                        queryData.add(e);
                    }
                });
            }
        }
        queryData.forEach(e->{
            if (e instanceof StudentEntity) {
                StudentEntity studentEntity = (StudentEntity) e;
                System.out.println("序号:" + studentEntity.getId());
                System.out.println("姓名:" + studentEntity.getName());
                System.out.println("类型:" + studentEntity.getType());
                System.out.println("教工编号:" + studentEntity.getSystemNumber());
                System.out.println("年龄:" + studentEntity.getAge());
                System.out.println("性别:" + studentEntity.getSex());
                System.out.println("住址:" + studentEntity.getConnection());
                System.out.println("联系方式:" + studentEntity.getConnection());
                switch (studentEntity.getMajor()) {
                    case "01":
                        System.out.println("专业: 计算机信息与技术");
                        break;
                    case "02":
                        System.out.println("专业: 网络工程");
                        break;
                    case "03":
                        System.out.println("专业: 软件工程");
                        break;
                    case "04":
                        System.out.println("专业: 物联网工程");
                        break;
                    default:
                        System.out.println("专业编码错误，无此专业");
                }
                System.out.println("---------------------------------------------------");
            }
            if (e instanceof TeacherEntity) {
                TeacherEntity teacherEntity = (TeacherEntity) e;
                System.out.println("序号:" + teacherEntity.getId());
                System.out.println("姓名:" + teacherEntity.getName());
                System.out.println("类型:" + teacherEntity.getType());
                System.out.println("教工编号:" + teacherEntity.getSystemNumber());
                System.out.println("年龄:" + teacherEntity.getAge());
                System.out.println("性别:" + teacherEntity.getSex());
                System.out.println("住址:" + teacherEntity.getConnection());
                System.out.println("联系方式:" + teacherEntity.getConnection());
                System.out.println("---------------------------------------------------");
            }
            if (e instanceof ATeacherEntity) {
                ATeacherEntity aTeacherEntity = (ATeacherEntity) e;
                System.out.println("序号:" + aTeacherEntity.getId());
                System.out.println("姓名:" + aTeacherEntity.getName());
                System.out.println("类型:" + aTeacherEntity.getType());
                System.out.println("教工编号:" + aTeacherEntity.getSystemNumber());
                System.out.println("年龄:" + aTeacherEntity.getAge());
                System.out.println("性别:" + aTeacherEntity.getSex());
                System.out.println("住址:" + aTeacherEntity.getConnection());
                System.out.println("联系方式:" + aTeacherEntity.getConnection());
                System.out.println("---------------------------------------------------");
            }
        });

    }

    /**
     * 单例模式获取实例
     * @return
     */
    public static MenuService getSingletonInstance() {
        return menuService;
    }
}
