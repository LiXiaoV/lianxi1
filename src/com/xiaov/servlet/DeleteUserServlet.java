package com.xiaov.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaov.bean.ResultInfo;
import com.xiaov.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaov Li
 * @date 2019-10-29 18:52
 */
@WebServlet(name = "DeleteUserServlet",urlPatterns = {"/deleteUser"})
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = false;
        UserDao userDao = new UserDao();
        try {
            if(userDao.deleteById(id)){
//                System.out.println("删除用户"+id+"成功");
                flag = true;
            }else {
//                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除id为"+id+"的用户失败");
        }
        String json = JSON.toJSONString(info);
//        System.out.println(json);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
