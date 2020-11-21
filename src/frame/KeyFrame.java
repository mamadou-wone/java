package frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyFrame extends JFrame implements ActionListener {

    JFrame frame;
    JButton button;
    JButton button2;
    JPanel panel;

    public KeyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 200);
        panel = new JPanel();
        
        button = new JButton();
        button.setText("Click me :)");
        button.setBounds(266, 351, 119, 37);
        button.setFont(new Font("Time", Font.PLAIN, 15));
        button.setFocusable(false);
        
        button.addActionListener(this);
        panel.add(button);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            GenerateKey key = new GenerateKey();
            key.generateKey();
            this.dispose();
            NewFrame newFrame = new NewFrame();
            newFrame.createWindow();
        }
    }

    public static void main(String[] args) {
        new KeyFrame();
    }

}
