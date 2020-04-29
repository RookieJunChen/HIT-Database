package GUI;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Answers extends JFrame{

  public Answers(ResultSet rs1, ResultSet rs2)throws SQLException
  {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane sp = new JScrollPane();
    JList list = new JList();
    DefaultListModel dlm = new DefaultListModel();
    dlm.addElement("统计人数(>5人)：");
    while(rs1.next())
    {
      dlm.addElement(rs1.getString("dept_name") +":      " + rs1.getInt("count(*)"));
    }
    
    dlm.addElement("  ");
    dlm.addElement("  ");
    dlm.addElement("  ");
    
    dlm.addElement("具体情况：");
    while(rs2.next())
    {
      dlm.addElement(rs2.getString("teacher_name") + "     " + rs2.getString("dept_name") +":     " + rs2.getFloat("salary"));
    }
    list.setModel(dlm);
    list.setBounds(101, 56, 288, 106);
    sp.getViewport().add(list);
    this.getContentPane().add(sp);
//      dlm.removeAllElements();
  }
  
  
  public static void processing(ResultSet rs1, ResultSet rs2) throws SQLException{

    Answers mainFrame = new Answers(rs1,rs2);
    mainFrame.setSize(400, 300);
    mainFrame.setBounds(400, 200, 339, 319);
    mainFrame.setTitle("查询结果");
    mainFrame.setVisible(true);
  }

}
