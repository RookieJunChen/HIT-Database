package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Operation.FD;
import Operation.Student;
import Operation.Teacher;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Login extends JFrame {

  private static JFrame myFrame;
  private JPanel contentPane;
  private JTextField textFieldid;
  private JTextField textFieldpassword;

  /**
   * Launch the application.
   */
  public static void processing() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Login frame = new Login();
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
  public Login() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 406, 408);
    setTitle("登录界面");
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JButton LoginButton = new JButton("登录");
    LoginButton.setFont(new Font("宋体", Font.PLAIN, 14));
    LoginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    LoginButton.setBounds(131, 314, 113, 31);
    contentPane.add(LoginButton);
    
    JLabel lblNewLabel = new JLabel("id: ");
    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    lblNewLabel.setBounds(89, 97, 40, 15);
    contentPane.add(lblNewLabel);
    
    textFieldid = new JTextField();
    textFieldid.setBounds(117, 95, 180, 21);
    contentPane.add(textFieldid);
    textFieldid.setColumns(10);
    
    JLabel label = new JLabel("\u8BF7\u767B\u5F55");
    label.setFont(new Font("楷体", Font.PLAIN, 20));
    label.setBounds(155, 27, 65, 31);
    contentPane.add(label);
    
    JLabel lblPassword = new JLabel("password:");
    lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    lblPassword.setBounds(43, 141, 77, 15);
    contentPane.add(lblPassword);
    
    textFieldpassword = new JTextField();
    textFieldpassword.setBounds(117, 139, 180, 21);
    contentPane.add(textFieldpassword);
    textFieldpassword.setColumns(10);
    
    JRadioButton rdbtnFinancialDepartment = new JRadioButton("Financial Department");
    rdbtnFinancialDepartment.setBounds(117, 195, 180, 23);
    contentPane.add(rdbtnFinancialDepartment);
    
    JRadioButton rdbtnTeacher = new JRadioButton("Teacher");
    rdbtnTeacher.setBounds(117, 230, 127, 23);
    contentPane.add(rdbtnTeacher);
    
    JRadioButton rdbtnStudent = new JRadioButton("Student");
    rdbtnStudent.setBounds(117, 268, 127, 23);
    contentPane.add(rdbtnStudent);
    
    LoginButton.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        String id = textFieldid.getText();
        String password = textFieldpassword.getText();
        if(rdbtnTeacher.isSelected())
        {
          myFrame.setVisible(false); //点击按钮后自动关闭原窗口
          Teacher t = new Teacher(Integer.valueOf(id), password);
          TeacherInfo.processing(t);
        }
        else if(rdbtnStudent.isSelected())
        {
          myFrame.setVisible(false);
          Student s = new Student(Integer.valueOf(id), password);
          StudentInfo.processing(s);
        }
        else if(rdbtnFinancialDepartment.isSelected())
        {
          myFrame.setVisible(false);
          FD.processing(id, password);
        }
//        manu.processing();
      }
    } );
  }
}
