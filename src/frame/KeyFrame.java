package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class KeyFrame extends JFrame implements ActionListener {

    JFrame frame;
    JButton button;

    public KeyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 500);
        button = new JButton();
        button.setText("Click me :)");
        button.setBounds(200, 100, 100, 50);

        button.setFocusable(false);
        button.addActionListener(this);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            GenerateKey key = new GenerateKey();
            key.generateKey();
            this.dispose();
            NewFrame newFrame = new  NewFrame();
            newFrame.createWindow();
        }
    }

    public static void main(String[] args) {
        new KeyFrame();
    }

}
