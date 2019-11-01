package com.xiaov.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaov.bean.ResultInfo;
import com.xiaov.bean.User;
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
 * @date 2019-10-30 21:16
 */
@WebServlet(name = "EditUserServlet",urlPatterns = {"/editUser"})
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        boolean flag = false;
        try {
            if(userDao.editUser(id,name,password)){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultInfo info = new ResultInfo();
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("编辑用户"+name+"信息失败！");
        }

        //将info对象序列化为json
        String json = JSON.toJSONString(info);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
//        System.out.println("id:"+id);
        UserDao userDao = new UserDao();
        User user = null;
        try {
            user = userDao.searchUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            String page = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>编辑用户界面</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <form id=\"editForm\" action=\"editUser\">\n" +
                    "        <input id=\"id\" type=\"hidden\" value=\""+user.getId()+"\">\n" +
                    "        <table width=\"50%\">\n" +
                    "            <tbody>\n" +
                    "            <tr>\n" +
                    "                <th width=\"20%\">\n" +
                    "                    姓名：\n" +
                    "                </th>\n" +
                    "                <td>\n" +
                    "                    <input type=\"text\" id=\"name\" value=\""+user.getUsername()+"\" />\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th width=\"20%\">\n" +
                    "                    密码：\n" +
                    "                </th>\n" +
                    "                <td>\n" +
                    "                    <input type=\"password\" id=\"password\" value=\""+user.getPassword()+"\"/>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <th></th>\n" +
                    "                <td width=\"20%\">\n" +
                    "                    <button type=\"button\" id=\"submitEdit\">确认</button>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n" +
                    "    </form>\n" +
                    "\n" +
                    "    <script type=\"text/javascript\" src= \"assets/js/jquery-3.4.1.min.js\"></script>\n" +
                    "    <script type=\"text/javascript\">\n" +
                    "        \n" +
                    "        function delayer(){\n" +
                    "            window.location = \"allUsers.html\";\n" +
                    "        }\n" +
                    "        \n" +
                    "        $(\"#submitEdit\").click(function(){\n" +
                    "            var id = $(\"#id\").val();\n" +
                    "            var name = $(\"#name\").val();\n" +
                    "            // alert(name)\n" +
                    "            var password = $(\"#password\").val()\n" +
                    "            // alert(password)\n" +
                    "            var targetUrl = $(\"#editForm\").attr(\"action\");\n" +
                    "            // alert(targetUrl)\n" +
                    "            $.ajax({\n" +
                    "                type:\"post\",\n" +
                    "                async:false,    //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）\n" +
                    "                url:targetUrl,\n" +
                    "                cache: false,\n" +
                    "                data:{\"id\":id,\"name\":name,\"password\":password},  //重点必须为一个变量如：data\n" +
                    "                dataType:\"json\",\n" +
                    "                success:function(data){\n" +
                    "                    //请求成功时执行该函数内容，data即为服务器返回的json对象\n" +
                    "                    if(data.flag == true){\n" +
                    "                        alert(\"编辑用户信息成功\");\n" +
                    "                        setTimeout('delayer()', 1000);\n" +
                    "                        // window.location.reload();\n" +
                    "                    }else {\n" +
                    "                        alert(data.errorMsg);\n" +
                    "                        setTimeout('delayer()', 1000);\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                error:function(){\n" +
                    "                    alert(\"请求失败\")\n" +
                    "                }\n" +
                    "            })\n" +
                    "\n" +
                    "        })\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>";
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(page);
        }else {
            request.getRequestDispatcher("failure.html").forward(request,response);
        }
    }
}
