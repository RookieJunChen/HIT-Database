package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Operation.FD;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FinanceQuery extends JFrame {

  private JPanel contentPane;
  private static JFrame myFrame;
  private JTextField textFieldbudget;
  private JTextField textFieldsalary;
  public String budget;
  public String salary;

  /**
   * Launch the application.
   */
  public static void processing() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          FinanceQuery frame = new FinanceQuery();
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
  public FinanceQuery() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 339, 319);
    setTitle("查询界面");
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel label = new JLabel("\u85AA\u91D1\u60C5\u51B5\u67E5\u8BE2");
    label.setFont(new Font("楷体", Font.PLAIN, 16));
    label.setBounds(110, 10, 104, 44);
    contentPane.add(label);
    
    textFieldbudget = new JTextField();
    textFieldbudget.setBounds(177, 98, 80, 21);
    contentPane.add(textFieldbudget);
    textFieldbudget.setColumns(10);
    
    textFieldsalary = new JTextField();
    textFieldsalary.setBounds(177, 145, 80, 21);
    contentPane.add(textFieldsalary);
    textFieldsalary.setColumns(10);
    
    JLabel label_1 = new JLabel("\u7CFB\u9884\u7B97\u5927\u4E8E\uFF1A");
    label_1.setFont(new Font("宋体", Font.PLAIN, 14));
    label_1.setBounds(76, 94, 92, 29);
    contentPane.add(label_1);
    
    JLabel label_2 = new JLabel("\u6559\u5E08\u85AA\u6C34\u5927\u4E8E\uFF1A");
    label_2.setFont(new Font("宋体", Font.PLAIN, 14));
    label_2.setBounds(62, 145, 105, 21);
    contentPane.add(label_2);
    
    JButton button = new JButton("\u67E5\u8BE2");
    button.setFont(new Font("宋体", Font.PLAIN, 14));
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    button.setBounds(110, 216, 116, 29);
    contentPane.add(button);
    
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setForeground(Color.RED);
    lblNewLabel.setBounds(150, 191, 76, 15);
    contentPane.add(lblNewLabel);
    
    button.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        
        budget = textFieldbudget.getText();
        salary = textFieldsalary.getText();
        try {
          Answers.processing(FD.group(Float.valueOf(budget), Float.valueOf(salary)),FD.getfinance(Float.valueOf(budget), Float.valueOf(salary)));
          myFrame.setVisible(false);
        } catch (Exception e) {
          lblNewLabel.setText("查询失败!");
          e.printStackTrace();
        }
        
      }
    });
    
  }

  
}
