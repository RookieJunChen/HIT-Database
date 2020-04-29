package Operation;

import java.sql.*;


import main.main;

public class Student {
  private final int id;
  private final String password;
  
  public Student(int id , String password) {
    this.id = id;
    this.password = password;
  }
  
  
  
  //连接查询学生信息
  public ResultSet getbase() throws SQLException
  {
    ResultSet rs = null;
    
    String sql = "select * from student,dept where student_id = ? and password = ? and student.dept_id = dept.dept_id";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    pstmt.setString(2, password);
    rs=pstmt.executeQuery();
    
    return rs;
  }
  
  
  public ResultSet getstucourse() throws SQLException
  {
    ResultSet rs = null;
    
    String sql = "select * from student,election,course"
        + " where student.student_id = ? and password = ? "
        + "and student.student_id = election.student_id and course.course_id = election.course_id";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    pstmt.setString(2, password);
    rs=pstmt.executeQuery();
    
    return rs;
  }
  
  
  public void updatepassword(String password) throws Exception
  {
    if(password.equals(""))
      password = null;
    String sql = "update student set password=? where student_id=?";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setString(1, password);
    pstmt.setInt(2, id);
    int judge = pstmt.executeUpdate();
    if(password.length() < 10)
      main.conn.commit();
    else
    {
      main.conn.rollback();
      throw new Exception("password too long");
    }
      
  }
  
  
}
