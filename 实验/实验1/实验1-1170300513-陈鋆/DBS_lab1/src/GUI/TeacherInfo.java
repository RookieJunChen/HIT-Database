package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Operation.Teacher;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;

public class TeacherInfo extends JFrame {

  private static JFrame myFrame;
  private static Teacher tea;
  private JPanel contentPane;
  private JTextField textFieldid;
  private JTextField textFieldname;
  private JTextField textFieldgender;
  private JTextField textFieldage;
  private JTextField textFielddomain;
  private JTextField textFieldproject;
  private JTextField textFielddept;
  private JTextField textFieldlab;
  private JTextField textFieldsalary;
  private JTextField textFieldpassword;
  private JLabel label_1_2;
  private JLabel label_1_3;
  private JLabel label_2;
  private JLabel label_3;
  private JLabel label_4;
  private JLabel label_5;
  private JLabel label_1_4;
  private JLabel label_1_5;
  private JLabel tips;

  /**
   * Launch the application.
   */
  public static void processing(Teacher teacher) {
    tea = teacher;
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TeacherInfo frame = new TeacherInfo(tea.getbase());
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
  public TeacherInfo(ResultSet rs) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 604, 463);
    setTitle("教师信息");
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel label = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
    label.setFont(new Font("楷体", Font.PLAIN, 22));
    label.setBounds(230, 10, 97, 35);
    contentPane.add(label);
    
    textFieldid = new JTextField();
    textFieldid.setBounds(103, 59, 108, 21);
    contentPane.add(textFieldid);
    textFieldid.setColumns(10);
    
    textFieldname = new JTextField();
    textFieldname.setBounds(428, 59, 77, 21);
    contentPane.add(textFieldname);
    textFieldname.setColumns(10);
    
    textFieldgender = new JTextField();
    textFieldgender.setBounds(103, 114, 54, 21);
    contentPane.add(textFieldgender);
    textFieldgender.setColumns(10);
    
    textFieldage = new JTextField();
    textFieldage.setColumns(10);
    textFieldage.setBounds(428, 114, 54, 21);
    contentPane.add(textFieldage);
    
    textFielddomain = new JTextField();
    textFielddomain.setColumns(10);
    textFielddomain.setBounds(103, 167, 108, 21);
    contentPane.add(textFielddomain);
    
    textFieldproject = new JTextField();
    textFieldproject.setColumns(10);
    textFieldproject.setBounds(428, 167, 66, 21);
    contentPane.add(textFieldproject);
    
    textFielddept = new JTextField();
    textFielddept.setColumns(10);
    textFielddept.setBounds(103, 224, 77, 21);
    contentPane.add(textFielddept);
    
    textFieldlab = new JTextField();
    textFieldlab.setColumns(10);
    textFieldlab.setBounds(428, 224, 77, 21);
    contentPane.add(textFieldlab);
    
    textFieldsalary = new JTextField();
    textFieldsalary.setColumns(10);
    textFieldsalary.setBounds(103, 285, 88, 21);
    contentPane.add(textFieldsalary);
    
    textFieldpassword = new JTextField();
    textFieldpassword.setColumns(10);
    textFieldpassword.setBounds(428, 285, 108, 21);
    contentPane.add(textFieldpassword);
    
    JButton buttoncp = new JButton("\u4FEE\u6539\u5BC6\u7801");
    buttoncp.setBounds(140, 365, 97, 23);
    contentPane.add(buttoncp);
    
    JButton buttonlc = new JButton("\u8BFE\u7A0B\u60C5\u51B5");
    buttonlc.setBounds(343, 365, 97, 23);
    contentPane.add(buttonlc);
    
    JLabel label_1 = new JLabel("\u6559\u5E08\u53F7\uFF1A");
    label_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1.setBounds(22, 59, 71, 21);
    contentPane.add(label_1);
    
    JLabel label_1_1 = new JLabel("\u59D3\u540D\uFF1A");
    label_1_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_1.setBounds(347, 59, 71, 21);
    contentPane.add(label_1_1);
    
    label_1_2 = new JLabel("\u6027\u522B\uFF1A");
    label_1_2.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_2.setBounds(38, 114, 71, 21);
    contentPane.add(label_1_2);
    
    label_1_3 = new JLabel("\u5E74\u9F84\uFF1A");
    label_1_3.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_3.setBounds(347, 114, 71, 21);
    contentPane.add(label_1_3);
    
    label_2 = new JLabel("\u7814\u7A76\u65B9\u5411\uFF1A");
    label_2.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_2.setBounds(10, 167, 83, 21);
    contentPane.add(label_2);
    
    label_3 = new JLabel("\u79D1\u7814\u9879\u76EE\uFF1A");
    label_3.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_3.setBounds(316, 167, 83, 21);
    contentPane.add(label_3);
    
    label_4 = new JLabel("\u6240\u5728\u7CFB\uFF1A");
    label_4.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_4.setBounds(22, 224, 71, 21);
    contentPane.add(label_4);
    
    label_5 = new JLabel("\u5B9E\u9A8C\u5BA4\u540D\uFF1A");
    label_5.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_5.setBounds(316, 224, 83, 21);
    contentPane.add(label_5);
    
    label_1_4 = new JLabel("\u85AA\u6C34\uFF1A");
    label_1_4.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_4.setBounds(38, 285, 71, 21);
    contentPane.add(label_1_4);
    
    label_1_5 = new JLabel("\u5BC6\u7801\uFF1A");
    label_1_5.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_1_5.setBounds(347, 285, 71, 21);
    contentPane.add(label_1_5);
    
    tips = new JLabel("");
    tips.setForeground(Color.BLUE);
    tips.setBounds(450, 316, 77, 15);
    contentPane.add(tips);
    
    try {
      while(rs.next())
      {
        textFieldid.setText(rs.getInt("teacher_id")+"");
        textFieldname.setText(rs.getString("teacher_name"));
        textFieldgender.setText(rs.getString("gender"));
        textFieldage.setText(rs.getInt("age")+"");
        textFielddept.setText(rs.getString("dept.dept_name"));
        textFieldpassword.setText(rs.getString("password"));
        textFielddomain.setText(rs.getString("domain.domain_name"));
        textFieldsalary.setText(rs.getFloat("salary")+"");
        textFieldproject.setText(rs.getString("project.project_name"));
        textFieldlab.setText(rs.getString("lab.lab_name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
    buttoncp.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        String newpassword = textFieldpassword.getText();
        try {
          tea.updatepassword(newpassword);
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
          Teachcourse.processing(tea);
          myFrame.setVisible(false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } );
    
    
  }

  
}
