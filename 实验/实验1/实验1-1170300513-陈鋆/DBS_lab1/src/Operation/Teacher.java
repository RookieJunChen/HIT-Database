package Operation;
import java.sql.*;

import main.main;


public class Teacher {
  private final int id;
  private final String password;
 
  
  
  public Teacher(int id , String password) {
    this.id = id;
    this.password = password;
  }
  
  //连接查询教师信息
  public ResultSet getbase() throws SQLException
  {
    ResultSet rs = null;
    
    String sql = "select * from teacher,dept,domain,lab,project "
        + "where teacher_id = ? and password = ? and "
        + "teacher.dept_id = dept.dept_id and teacher.lab_id = lab.lab_id and teacher.domain_id = domain.domain_id "
        + "and teacher.project_id = project.project_id";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    pstmt.setString(2, password);
    rs=pstmt.executeQuery();
    
    return rs;
  }
  
  
  //连接查询
  public ResultSet getmycourse() throws SQLException
  {
    ResultSet rs = null;
    
    String sql = "select * from teacher,teaching,course"
        + " where teacher.teacher_id = ? and password = ? "
        + "and teacher.teacher_id = teaching.teacher_id and course.course_id = teaching.course_id";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    pstmt.setString(2, password);
    rs=pstmt.executeQuery();   
    
    return rs;
  }
  
  
  //插入
  public void addcourse(int course_id , int hour) throws SQLException
  {
    
    String sql = "insert into teaching(course_id,teacher_id,hour) values(?,?,?);";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setInt(1, course_id);
    pstmt.setInt(2, id);
    pstmt.setInt(3, hour);
    int judge = pstmt.executeUpdate();
    
    if(judge > 0)
      main.conn.commit();
    else
      main.conn.rollback();
    
  }
  
  //删除
  public void dropcourse(int course_id) throws SQLException
  {
    String sql = "delete from teaching where course_id=? and teacher_id=?";
    PreparedStatement pstmt = main.conn.prepareStatement(sql);
    pstmt.setInt(1, course_id);
    pstmt.setInt(2, id);
    int judge = pstmt.executeUpdate();
    
    if(judge > 0)
      main.conn.commit();
    else
      main.conn.rollback();
  }
  
  //更新
  public void updatepassword(String password) throws Exception
  {
    if(password.equals(""))
      password = null;
    String sql = "update teacher set password=? where teacher_id=?";
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
