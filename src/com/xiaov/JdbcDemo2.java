package com.xiaov;

import com.xiaov.util.JdbcUtil;

import java.sql.*;

/**
 * @author xiaov Li
 * @date 2019-10-23 11:21
 */
public class JdbcDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "liwei666");
////        String sql = "update users set psd='liwei6' where id = 1";
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into users values(null,?,?)";
//        PreparedStatement pStmt = conn.prepareStatement(sql);
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1,"小威");
        pStmt.setString(2,"66666");
        int count = pStmt.executeUpdate();
        System.out.println(count);
        pStmt.close();

        conn.close();

    }
}
