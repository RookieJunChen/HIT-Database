package main;
import java.awt.EventQueue;

import java.sql.*;

import GUI.Login;
import Operation.FD;
import Operation.Student;
import Operation.Teacher;


public class main  {
  // 学号1709174
  // 教师号102001
  public static Connection conn = null;
  
  public static void main(String[] args){
    conn = Connect.getConn(conn);
    Login.processing();
    
    
//    FD f = new FD();
//    try {
//      ResultSet rs = FD.group(50000, 5000);
//    while(rs.next())
//    {
//      System.out.println(rs.getString("dept_name") +":  " + rs.getInt("count(*)"));
//    }
//    
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    
//    
//    Teacher t = new Teacher(id, password);
//    try {
//      ResultSet rs = t.getmycourse();
//      while(rs.next())
//      {
//        System.out.println(rs.getString("course_name") + ":  " + rs.getInt("hour"));
////        t.dropcourse(3);
////        System.out.println(rs.getInt("teacher_id"));
////        System.out.println(rs.getString("teacher_name"));
////        System.out.println(rs.getInt("age"));
////        System.out.println(rs.getFloat("salary"));
////        System.out.println(rs.getString("gender"));
////        System.out.println(rs.getString("dept.dept_name"));
////        System.out.println(rs.getString("lab.lab_name"));
////        System.out.println(rs.getString("project.project_name"));
////        System.out.println(rs.getString("domain.domain_name"));
//      }
//    }
//    catch (SQLIntegrityConstraintViolationException e) {
//      System.out.println("重复!"); //空值也是因为此，分开来catch
//    }  
//    catch (Exception e) {
//      e.printStackTrace();
//    }
   
    
//    Student s = new Student(id, password);
//    try {
//      ResultSet rs = s.getstucourse();
//      while(rs.next())
//      {
//          System.out.println(rs.getString("course_name") + ":  " + rs.getInt("score"));
//        System.out.println(rs.getInt("student_id"));
//        System.out.println(rs.getString("student_name"));
//        System.out.println(rs.getInt("age"));
//        System.out.println(rs.getInt("grade"));
//        System.out.println(rs.getString("gender"));
//        System.out.println(rs.getString("dept.dept_name"));
//      }
//      
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    
//    try {
//      conn.setAutoCommit(false);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    
//    Random r = new Random(1);
//
//
//
//    
//    
//    for(int i = 1 ; i <= 6 ; i++)
//    {
//
//        String sql = "select student_id from student";
//        try {
//          pstmt = conn.prepareStatement(sql);
//          rs = pstmt.executeQuery();
//          while(rs.next())
//          {
//            int stu_id = rs.getInt(1);
//            sql="insert into election(course_id,student_id,score) " + "values(?,?,?);";
//            int course_id = (((stu_id % 1000) + i) % 180) + 59 ;
//            int score = 60 + r.nextInt(40);
//            try {
//              pstmt = conn.prepareStatement(sql);
//              pstmt.setInt(1, course_id);
//              pstmt.setInt(2, stu_id);
//              pstmt.setInt(3, score);
//              pstmt.executeUpdate();
//            } catch (Exception e) {
//              e.printStackTrace();
//              System.exit(0);
//            }
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//        
//        
//        
//      }
//        
//       
//      
//  
//    try {
//      conn.commit();
//      System.out.println("完成任务");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    
  }
}
