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
public class SignFrame {
    String path="";

    public static void  createWindow(){
        JFrame frame = new JFrame("Signature");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 200);      
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame){  
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();  
        panel.setLayout(layout);       
        JButton button = new JButton("Signez");
        JButton button2 = new JButton("Verifiez");
        final JLabel label = new JLabel();
  
        button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              JFileChooser fileChooser = new JFileChooser();
              int option = fileChooser.showOpenDialog(frame);
              if(option == JFileChooser.APPROVE_OPTION){
                 File file = fileChooser.getSelectedFile();
                 // label.setText("File Selected: " + file.getAbsolutePath());
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

        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JFileChooser fileChooser = new JFileChooser();
               int option = fileChooser.showOpenDialog(frame);
               if(option == JFileChooser.APPROVE_OPTION){
                  File file = fileChooser.getSelectedFile();
                  // label.setText("File Selected: " + file.getAbsolutePath());
                   try {
                    openFileAndVerify(file.getAbsolutePath());
                   } catch (Exception ex) {
                      ex.printStackTrace();
                   }
                   
               }else{
                  label.setText("Desolé");
               }
            }
         });
  
        panel.add(button);
        panel.add(button2);
                panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);    
     }

     private static  void openFileAndVerify(String path) throws Exception{
        try {
            SignVerif sign = new SignVerif();
            ContenFrame contenFrame = new ContenFrame();
            byte[] signature = sign.signText(sign.getMessage("C:/Users/megaw/Desktop/Dev/JAVA/signature_numerique/signature/src/frame/test.txt").getBytes("utf-8"), sign.getPrivateKey());
           boolean bool = sign.verifSnature(sign.getMessage(path).getBytes("utf-8"), signature, sign.getPublicKey());
          //   Desktop.getDesktop().open(new java.io.File(path));
        //   sign.displayFrame(bool);
        if (bool) {
            contenFrame.createWindow();
        }else{
            System.out.println("Sorryyyyyyyyyyyyyyy");
        }
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    private static  void openFilew(String path) throws Exception{
        try {
            SignVerif sign = new SignVerif();
            sign.signText(sign.getMessage(path).getBytes("UTF-8"), sign.getPrivateKey());
          //   Desktop.getDesktop().open(new java.io.File(path));
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        createWindow();
    }
}
