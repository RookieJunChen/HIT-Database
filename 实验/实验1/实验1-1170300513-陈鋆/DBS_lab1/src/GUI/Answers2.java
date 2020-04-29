package GUI;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Answers2 extends JFrame{

  public Answers2(ResultSet rs)throws SQLException
  {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane sp = new JScrollPane();
    JList list = new JList();
    DefaultListModel dlm = new DefaultListModel();
    dlm.addElement("选修课程情况：");
    while(rs.next())
    {
      dlm.addElement(rs.getString("course_name") + ":   " + rs.getInt("score"));
    }
    list.setModel(dlm);
    list.setBounds(101, 56, 288, 106);
    sp.getViewport().add(list);
    this.getContentPane().add(sp);
//      dlm.removeAllElements();
  }
  
  
  public static void processing(ResultSet rs) throws SQLException{

    Answers2 mainFrame = new Answers2(rs);
    mainFrame.setSize(400, 300);
    mainFrame.setBounds(400, 200, 339, 319);
    mainFrame.setTitle("查询结果");
    mainFrame.setVisible(true);
  }

}
