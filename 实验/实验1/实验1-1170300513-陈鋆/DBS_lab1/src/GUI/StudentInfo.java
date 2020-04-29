package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Operation.FD;
import Operation.Student;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JTextField;
import java.awt.Color;

public class StudentInfo extends JFrame {

  private static JFrame myFrame;
  private static Student stu;
  private JPanel contentPane;
  private JTextField textFieldid;
  private JTextField textFieldname;
  private JTextField textFieldgender;
  private JTextField textFieldage;
  private JTextField textFieldgrade;
  private JTextField textFielddept;
  private JTextField textFieldpassword;
  
  /**
   * Launch the application.
   */
  public static void processing(Student stuin) {
    stu = stuin;
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          StudentInfo frame = new StudentInfo(stu.getbase());
          frame.setVisible(true);
          myFrame = (JFrame) frame;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public StudentInfo(ResultSet rs) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 514, 447);
    setTitle("学生信息");
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JButton buttoncp = new JButton("\u4FEE\u6539\u5BC6\u7801");
    buttoncp.setBounds(86, 377, 97, 23);
    contentPane.add(buttoncp);
    
    JButton buttonlc = new JButton("\u67E5\u770B\u8BFE\u8868");
    buttonlc.setBounds(300, 377, 97, 23);
    contentPane.add(buttonlc);
    
    JLabel label = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
    label.setFont(new Font("楷体", Font.PLAIN, 22));
    label.setBounds(201, 10, 97, 35);
    contentPane.add(label);
    
    textFieldid = new JTextField();
    textFieldid.setBounds(201, 67, 163, 21);
    contentPane.add(textFieldid);
    textFieldid.setColumns(10);
    
    textFieldname = new JTextField();
    textFieldname.setBounds(201, 111, 66, 21);
    contentPane.add(textFieldname);
    textFieldname.setColumns(10);
    
    textFieldgender = new JTextField();
    textFieldgender.setBounds(201, 152, 54, 21);
    contentPane.add(textFieldgender);
    textFieldgender.setColumns(10);
    
    textFieldage = new JTextField();
    textFieldage.setColumns(10);
    textFieldage.setBounds(201, 196, 54, 21);
    contentPane.add(textFieldage);
    
    textFieldgrade = new JTextField();
    textFieldgrade.setBounds(201, 238, 54, 21);
    contentPane.add(textFieldgrade);
    textFieldgrade.setColumns(10);
    
    textFielddept = new JTextField();
    textFielddept.setColumns(10);
    textFielddept.setBounds(201, 281, 66, 21);
    contentPane.add(textFielddept);
    
    textFieldpassword = new JTextField();
    textFieldpassword.setBounds(201, 329, 113, 21);
    contentPane.add(textFieldpassword);
    textFieldpassword.setColumns(10);
    
    JLabel label_1 = new JLabel("\u5B66\u53F7\uFF1A");
    label_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1.setBounds(124, 66, 59, 21);
    contentPane.add(label_1);
    
    JLabel label_1_1 = new JLabel("\u59D3\u540D\uFF1A");
    label_1_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_1.setBounds(124, 108, 59, 21);
    contentPane.add(label_1_1);
    
    JLabel label_1_1_1 = new JLabel("\u6027\u522B\uFF1A");
    label_1_1_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_1_1.setBounds(124, 149, 59, 21);
    contentPane.add(label_1_1_1);
    
    JLabel label_1_1_1_1 = new JLabel("\u5E74\u9F84\uFF1A");
    label_1_1_1_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_1_1_1.setBounds(124, 193, 59, 21);
    contentPane.add(label_1_1_1_1);
    
    JLabel label_1_1_1_1_1 = new JLabel("\u5E74\u7EA7\uFF1A");
    label_1_1_1_1_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_1_1_1_1.setBounds(124, 235, 59, 21);
    contentPane.add(label_1_1_1_1_1);
    
    JLabel label_1_2 = new JLabel("\u6240\u5728\u7CFB\uFF1A");
    label_1_2.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_2.setBounds(109, 278, 82, 21);
    contentPane.add(label_1_2);
    
    JLabel label_1_1_1_1_1_1 = new JLabel("\u5BC6\u7801\uFF1A");
    label_1_1_1_1_1_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_1_1_1_1_1.setBounds(124, 326, 59, 21);
    contentPane.add(label_1_1_1_1_1_1);
    
    JLabel tips = new JLabel("");
    tips.setForeground(Color.BLUE);
    tips.setBounds(339, 332, 58, 15);
    contentPane.add(tips);
    
    try {
      while(rs.next())
      {
        textFieldid.setText(rs.getInt("student_id")+"");
        textFieldname.setText(rs.getString("student_name"));
        textFieldgender.setText(rs.getString("gender"));
        textFieldage.setText(rs.getInt("age")+"");
        textFieldgrade.setText(rs.getInt("grade")+"");
        textFielddept.setText(rs.getString("dept.dept_name"));
        textFieldpassword.setText(rs.getString("password"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
    
    
    buttoncp.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        String newpassword = textFieldpassword.getText();
        try {
          stu.updatepassword(newpassword);
          tips.setText("修改成功!");
          tips.setForeground(Color.BLUE);
        } catch (Exception e) {
          tips.setText("修改失败!");
          tips.setForeground(Color.RED);
        }

      }
    } );
    
    
    buttonlc.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        try {
          Answers2.processing(stu.getstucourse());
          myFrame.setVisible(false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } );
    
  }

}
