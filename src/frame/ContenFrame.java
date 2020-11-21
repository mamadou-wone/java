package frame;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class ContenFrame {
    public static void main(String[] args) {
        createWindow();
     }
  
     public static void createWindow() {    
        JFrame frame = new JFrame("Sign Verify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);      
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
     }
     
     private static  void openFile(String path) throws Exception{
         try {
             HachFile hachFile = new HachFile();
             hachFile.hachFile(path);
           //   Desktop.getDesktop().open(new java.io.File(path));
           // SignFrame signFrame = new SignFrame();
           new SignFrame().createWindow();
        } catch (Exception e) {
              e.printStackTrace();
         }
     }
  
     private static void createUI(final JFrame frame){  
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();  
        panel.setLayout(layout);       
  
        JButton button = new JButton("Click Me :)");
        final JLabel label = new JLabel();
  
        button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              JFileChooser fileChooser = new JFileChooser();
              int option = fileChooser.showOpenDialog(frame);
              if(option == JFileChooser.APPROVE_OPTION){
                 File file = fileChooser.getSelectedFile();
                 // label.setText("File Selected: " + file.getAbsolutePath());
                 new SignFrame().createWindow();
                 try {
                      openFile(file.getAbsolutePath());
                  } catch (Exception ex) {
                     ex.printStackTrace();
                  }
                  
              }else{
                 label.setText("Desolé");
              }
           }
        });
  
        panel.add(button);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);    
     }
}
