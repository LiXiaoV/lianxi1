package com.xiaov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author xiaov Li
 * @date 2019-10-22 20:09
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "liwei666");
        String sql = "update users set psd='liwei6' where id = 1";
        try {
            Statement stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();

    }
}
