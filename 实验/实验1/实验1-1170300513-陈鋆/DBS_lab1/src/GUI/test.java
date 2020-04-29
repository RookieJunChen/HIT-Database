package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.crypto.ExemptionMechanismException;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class test extends JFrame {

  private JPanel contentPane;
  private static JFrame cFrame;
  private JPasswordField pwdAsda;
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          test frame = new test();
          frame.setVisible(true);
          cFrame = (JFrame) frame;
        } catch (Exception e) {

        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public test(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 540, 348);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    
    JButton btnNewButton = new JButton("New button");
    btnNewButton.setBounds(226, 254, 97, 23);
    contentPane.add(btnNewButton);
    
    JTextPane text = new JTextPane();
    text.setBounds(186, 204, 164, 23);
    text.setText("emmmmm");
    contentPane.add(text);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(40, 53, 223, 120);
    contentPane.add(scrollPane);
    
    JList list = new JList();
    scrollPane.setViewportView(list);
    
    pwdAsda = new JPasswordField();
    pwdAsda.setText("asda");
    pwdAsda.setBounds(309, 85, 97, 21);
    contentPane.add(pwdAsda);
    
    JFormattedTextField frmtdtxtfldDaa = new JFormattedTextField();
    frmtdtxtfldDaa.setText("daa");
    frmtdtxtfldDaa.setBounds(322, 158, 117, 23);
    contentPane.add(frmtdtxtfldDaa);
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 111, 23);
    contentPane.add(menuBar);
    
    JMenu mnNewMenu = new JMenu("New menu");
    menuBar.add(mnNewMenu);
    
    JMenuItem mntmDa = new JMenuItem("da");
    mnNewMenu.add(mntmDa);
    
    JMenuItem mntmNewMenuItem = new JMenuItem("czc");
    mnNewMenu.add(mntmNewMenuItem);
//    DefaultListModel dlm = new DefaultListModel();
//    for(int i = 0 ; i < 500 ; i++)
//    {
//      dlm.addElement(i);
//    }
//    list.setModel(dlm);

//    tableModel.addColumn("课程名");
//    tableModel.addColumn("学时");
//    for(int i = 0 ; i < 20 ; i++)
//    {
//      String s1 = i + "";
//      String s2 = i+1 + "";
//      Object o[] = {s1,s2};
//      tableModel.addRow(o);
//    }
    
    
    
    String str = text.getText();
    btnNewButton.addMouseListener(new  MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {

        text.setText("Fuck you!" + " " + str);
        DefaultListModel dlm = new DefaultListModel();
        for(int i = 0 ; i < 500 ; i++)
        {
          dlm.addElement(i);
        }
        list.setModel(dlm);
//        cFrame.setVisible(false);

        
//        manu.processing();
      }
    }
        
        );
    
  }
}
