package Operation;

import java.sql.PreparedStatement;

import GUI.FinanceQuery;

import java.sql.*;

import main.main;


public class FD {
  static final String USER = "10001"; //用户名
  static final String PASSWORD = "admin"; //密码
  
  public static void processing(String user , String password)
  {
    FD f = new FD();
    if(user.equals(USER) && password.equals(PASSWORD))
    {
      
      FinanceQuery.processing();
    }
  }
  
  //嵌套查询
  public static ResultSet getfinance(float budget , float salary) throws SQLException
  {
    ResultSet rs = null;
    
    String sql = "select * from (select * from dept where budget > ?) as ndept , teacher "
        + "where salary > ? and teacher.dept_id = ndept.dept_id";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setFloat(1, budget);
    pstmt.setFloat(2, salary);
    rs=pstmt.executeQuery();
    
    return rs;
  }
  
  //分组查询
  public static ResultSet group(float budget , float salary) throws SQLException
  {
    ResultSet rs = null;
    
    String sql = "select dept_name,count(*) from (select * from dept where budget > ?) as ndept , teacher "
        + "where salary > ? and teacher.dept_id = ndept.dept_id " + "group by dept_name having count(*)>5 "
        + "order by count(*) desc";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setFloat(1, budget);
    pstmt.setFloat(2, salary);
    rs=pstmt.executeQuery();
    
    return rs;
  }
  
}
