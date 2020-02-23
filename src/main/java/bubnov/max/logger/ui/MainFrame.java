package bubnov.max.logger.ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    MainPanel panel;

    public MainFrame(){
        panel = new MainPanel();
        add(panel);
        setLocationRelativeTo(null);
        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
