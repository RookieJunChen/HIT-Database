package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {
  static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver"; //JDBC driver name
  static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/edusystem?user=root&serverTimezone=Asia/Shanghai"; //数据库URL
  
  static final String USER = "root"; //用户名
  static final String PASS = "chen5211999"; //密码
  
  public static Connection getConn(Connection conn){
    // Register JDBC driver
    try {
      Class.forName(JDBC_DRIVER);
      System.out.println("成功加载Mysql Driver!");
    } catch (Exception e) {
      System.out.print("加载Mysql Driver失败!");
      e.printStackTrace();
    }
    //连接数据库
    try {
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      conn.setAutoCommit(false);
      System.out.println("成功连接到数据库!");
    } catch (Exception e) {
      System.out.println("连接到数据库失败!");
      e.printStackTrace();
    }
    return conn;
}
}
