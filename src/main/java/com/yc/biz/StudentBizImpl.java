package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 14:43
 */
//@Service
public class StudentBizImpl {
    private StudentDao studentDao;

    //@Inject
    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    public StudentBizImpl(){}

    @Inject   //javax中的依赖注入   如果有多个对象（如studentPaj，studentMybatis对象）
                //因为有多个对象可以注入，则必须要用@Named("studentMybatisImpl")约定
//    @Autowired  //org.springframework
//    @Qualifier("studengMybatisImpl")    //如果有多个对象（如studentPaj，studengMybatis对象）
                   //因为有多个对象可以注入，则必须要用@Qualifier("studentMybatisImpl")约定
    //@Resource(name = "studengMybatisImpl")
    public void setStudentDao( @Named("studengMybatisImpl") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add(String name){
        System.out.println("=======业务层==========");
        System.out.println("用户名是否重名");
        int result=studentDao.add(name);
        System.out.println("=========业务操作结束===========");
        return result;
    }

    public int update(String name,String pwd){
        System.out.println("=======业务层==========");
        System.out.println("用户名是否存在");
        int result=studentDao.update(name,pwd);
        System.out.println("=========业务操作结束===========");
        return result;
    }
}
