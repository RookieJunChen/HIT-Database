package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Operation.Teacher;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class Teachcourse extends JFrame {
  
  private static Teacher tea;
  private JPanel contentPane;
  private JTextField textFieldid;
  private JTextField textFieldhours;
  
  /**
   * Launch the application.
   */
  public static void processing(Teacher teacher) {
    tea = teacher;
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Teachcourse frame = new Teachcourse();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Teachcourse() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 613, 451);
    setTitle("课程情况");
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    
    JList list = new JList();
    DefaultListModel dlm = new DefaultListModel();
    try {
      ResultSet rs = tea.getmycourse();
      while(rs.next())
      {
        dlm.addElement( rs.getString("course.course_id")  +"  " +rs.getString("course_name") + ":   " + rs.getInt("hour")
        + "   in classroom " + rs.getString("classroom_id"));
      }
      list.setModel(dlm);
    } catch (Exception e) {
      e.printStackTrace();
    }
    list.setBounds(24, 64, 252, 321);
    contentPane.add(list);
    
    
    JLabel label = new JLabel("\u5E94\u6559\u8BFE\u7A0B");
    label.setFont(new Font("楷体", Font.PLAIN, 22));
    label.setBounds(96, 19, 97, 35);
    contentPane.add(label);
    
    JLabel label_1 = new JLabel("\u8BFE\u7A0B\u4FEE\u6539");
    label_1.setFont(new Font("楷体", Font.PLAIN, 20));
    label_1.setBounds(407, 53, 97, 35);
    contentPane.add(label_1);
    
    textFieldid = new JTextField();
    textFieldid.setBounds(483, 141, 66, 21);
    contentPane.add(textFieldid);
    textFieldid.setColumns(10);
    
    textFieldhours = new JTextField();
    textFieldhours.setBounds(483, 193, 66, 21);
    contentPane.add(textFieldhours);
    textFieldhours.setColumns(10);
    
    JLabel label_2 = new JLabel("\u8BFE\u7A0B\u7F16\u53F7\uFF1A");
    label_2.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_2.setBounds(367, 141, 83, 21);
    contentPane.add(label_2);
    
    JLabel label_2_1 = new JLabel("\u8BB2\u6388\u5B66\u65F6\uFF1A");
    label_2_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
    label_2_1.setBounds(367, 193, 83, 21);
    contentPane.add(label_2_1);
    
    JButton buttonadd = new JButton("\u6DFB\u52A0\r\n");
    buttonadd.setFont(new Font("宋体", Font.PLAIN, 14));
    buttonadd.setBounds(324, 282, 97, 23);
    contentPane.add(buttonadd);
    
    JButton buttondelete = new JButton("\u5220\u9664");
    buttondelete.setFont(new Font("宋体", Font.PLAIN, 14));
    buttondelete.setBounds(483, 282, 97, 23);
    contentPane.add(buttondelete);
    
    JLabel tips = new JLabel("");
    tips.setFont(new Font("宋体", Font.PLAIN, 14));
    tips.setForeground(Color.BLUE);
    tips.setBounds(418, 224, 82, 23);
    contentPane.add(tips);
    
    JLabel tips2 = new JLabel("");
    tips2.setFont(new Font("宋体", Font.PLAIN, 14));
    tips2.setForeground(Color.RED);
    tips2.setBounds(407, 251, 108, 21);
    contentPane.add(tips2);
    
    
    buttonadd.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        int course_id = Integer.valueOf(textFieldid.getText());
        int hour = Integer.valueOf(textFieldhours.getText());
        try {
          tea.addcourse(course_id, hour);
          tips.setText("添加成功!");
          tips.setForeground(Color.BLUE);
          tips2.setText("");
          dlm.removeAllElements();
          try {
            ResultSet rs = tea.getmycourse();
            while(rs.next())
            {
              dlm.addElement( rs.getString("course.course_id")  +"  " +rs.getString("course_name") + ":   " + rs.getInt("hour")
              + "   in classroom " + rs.getString("classroom_id"));
            }
            list.setModel(dlm);
          } catch (Exception e) {
            e.printStackTrace();
          }
        } catch (Exception e) {
          tips.setText("添加失败!");
          tips2.setText("存在重复或空值");
          tips.setForeground(Color.RED);
        }

      }
    } );
    
    
    buttondelete.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        int course_id = Integer.valueOf(textFieldid.getText());
        int hour = Integer.valueOf(textFieldhours.getText());
        try {
          tea.dropcourse(course_id);
          tips.setText("删除成功!");
          tips.setForeground(Color.BLUE);
          tips2.setText("");
          dlm.removeAllElements();
          try {
            ResultSet rs = tea.getmycourse();
            while(rs.next())
            {
              dlm.addElement( rs.getString("course.course_id")  +"  " +rs.getString("course_name") + ":   " + rs.getInt("hour")
              + "   in classroom " + rs.getString("classroom_id"));
            }
            list.setModel(dlm);
          } catch (Exception e) {
            e.printStackTrace();
          }
        } catch (Exception e) {
          tips.setText("删除失败!");
          tips2.setText("并未讲该课程");
          tips.setForeground(Color.RED);
        }
      }
    } );
    
  }

}
