package com.xiaov.dao;


import com.xiaov.bean.User;
import com.xiaov.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaov Li
 * @date 2019-10-22 19:25
 */
public class UserDao {
    private Connection conn;

    /**
     * 检查用户在数据库中是否存在
     * @param name
     * @param password
     * @return
     * @throws SQLException
     */
    public Boolean isRegistered(String name,String password) throws SQLException {
        Boolean is_reg = false;
        conn = JdbcUtil.getConnection();
        String sql = "SELECT * FROM users WHERE name=? AND psd=?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1,name);
        pStmt.setString(2,password);
        ResultSet res = pStmt.executeQuery();
        while (res.next()){
            is_reg = true;
        }
        JdbcUtil.close(res,pStmt,conn);
        return is_reg;
    }

    /**
     * 添加用户
     * @param name
     * @param password
     * @return
     * @throws SQLException
     */
    public Boolean addUser(String name,String password) throws SQLException {
        boolean result = false;
        conn = JdbcUtil.getConnection();
        String sql = "INSERT INTO users VALUES(null,?,?)";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1,name);
        pStmt.setString(2,password);
        int cnt = pStmt.executeUpdate();
        if (cnt >0) {
            result = true;
        }
        JdbcUtil.close(pStmt,conn);
        return result;
    }

    /**
     * 查询所有用户
     * @return
     * @throws SQLException
     */
    public List allUsers() throws SQLException {
        conn = JdbcUtil.getConnection();
        String sql = "SELECT * FROM users ORDER BY id DESC";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        List users = new ArrayList<User>();
        ResultSet res = pStmt.executeQuery();
        while (res.next()){
            User user = new User();
            user.setId(res.getInt(1));
            user.setUsername(res.getString(2));
            user.setPassword(res.getString(3));
            users.add(user);
        }
        JdbcUtil.close(res,pStmt,conn);
        return users;
    }

    /**
     * 编辑用户信息
     * @param id
     * @param name
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean editUser(int id,String name,String password) throws SQLException {
        boolean result = false;
        conn = JdbcUtil.getConnection();
        String sql = "UPDATE users SET name=?,psd=? WHERE id=?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1,name);
        pStmt.setString(2,password);
        pStmt.setInt(3,id);
        int count = pStmt.executeUpdate();
        if (count > 0){
            result = true;
        }
        JdbcUtil.close(pStmt,conn);
        return result;
    }

    /**
     * 根据id来删除用户
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean deleteById(int id) throws SQLException {
        boolean result = false;
        conn = JdbcUtil.getConnection();
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setInt(1,id);
        int count = pStmt.executeUpdate();
        if (count > 0){
            result = true;
        }
        JdbcUtil.close(pStmt,conn);
        return result;
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     * @throws SQLException
     */
    public User searchUserById(int id) throws SQLException {
        conn = JdbcUtil.getConnection();
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setInt(1,id);
        ResultSet res = pStmt.executeQuery();
        while (res.next()){
            User user = new User();
            user.setId(res.getInt(1));
            user.setUsername(res.getString(2));
            user.setPassword(res.getString(3));
            return user;
        }
        return null;
    }
}
