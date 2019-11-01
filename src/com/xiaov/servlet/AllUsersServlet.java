package com.xiaov.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaov.bean.User;
import com.xiaov.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiaov Li
 * @date 2019-10-24 14:40
 */
@WebServlet(name = "AllUsersServlet",urlPatterns = {"/allUsers"})
public class AllUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserDao userDao = new UserDao();
        List users = null;
        try {
            users = userDao.allUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = JSON.toJSONString(users);
//        System.out.println(json);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
