package bubnov.max.logger;

import bubnov.max.logger.ui.MainFrame;

import javax.swing.*;

public class ApplicationStarter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
