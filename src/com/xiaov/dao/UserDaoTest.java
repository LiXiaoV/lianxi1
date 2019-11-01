package com.xiaov.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xiaov Li
 * @date 2019-10-23 21:05
 */
public class UserDaoTest {
    public static void main(String[] args) {

//        //测试userDao.addUser(name,password)
//        String name = "lixiaov";
//        String password = "liwei666";
//        UserDao userDao = new UserDao();
//
//        try {
//            if (userDao.addUser(name,password)){
//                System.out.println("操作成功！");
//            }else {
//                System.out.println("操作失败！");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //测试userDao.allUsers()
        UserDao userDao = new UserDao();
        List users = null;
        try {
            users = userDao.allUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);

//        //测试userDao.editUser(id,name,password);
//        int id = 1;
//        String name = "liwei";
//        String password = "liwei666";
//        UserDao userDao = new UserDao();
//        try {
//            userDao.editUser(id,name,password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        //测试userDao.deleteById(id)
//        int id = 9;
//        UserDao userDao = new UserDao();
//        boolean res = false;
//        try {
//            res = userDao.deleteById(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(res);


    }


}
