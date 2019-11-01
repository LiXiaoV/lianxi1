package com.xiaov.servlet;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaov.bean.ResultInfo;
import com.xiaov.dao.UserDao;
import jsonTest.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaov Li
 * @date 2019-10-23 21:18
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/addUser"})
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
//        System.out.println(name+" "+password);
//        System.out.println("AddUserServlet is running...");
        UserDao userDao = new UserDao();
        boolean flag = false;
        try {
            if (userDao.addUser(name,password)){
                flag = true;
//                System.out.println("操作成功");
            }else {
//                System.out.println("操作失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultInfo info = new ResultInfo();
        if (flag) {
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("添加用户"+name+"失败");
        }
        //将info对象序列化为json
        String json = JSON.toJSONString(info);
//        System.out.println(json);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
