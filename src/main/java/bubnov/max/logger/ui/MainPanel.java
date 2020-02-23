package bubnov.max.logger.ui;

import bubnov.max.logger.back.SimpleTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainPanel extends JPanel {

    private JLabel extensionType;
    private JTextField extension;
    private JLabel folder;
    private JButton chooseDir;
    private JLabel dirInfo;
    private JLabel logInfo;
    private JTextArea logText;
    private JButton search;
    private JPanel filesTree;
    public static JTextArea fileContent;

    public MainPanel() {
        setLayout(null);

        extensionType = new JLabel("1.   Расширение файлов");
        extensionType.setBounds(20, 20, 190, 30);
        add(extensionType);

        extension = new JTextField(".log");
        extension.setBounds(250, 20, 50, 30);
        add(extension);

        folder = new JLabel("2.   Выберите папку для поиска");
        folder.setBounds(20, 70, 250, 30);
        add(folder);

        chooseDir = new JButton("Открыть");
        chooseDir.setBounds(300, 70, 100, 30);
        chooseDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
                int status = fileChooser.showOpenDialog(null);
                if(status == JFileChooser.APPROVE_OPTION) {
                    File dir = fileChooser.getSelectedFile();
                    if(dir != null) {
                        dirInfo.setText(dir.getAbsolutePath());
                    }
                }
            }
        });
        add(chooseDir);

        dirInfo = new JLabel("");
        dirInfo.setBounds(20, 120, 200, 30);
        add(dirInfo);

        logInfo = new JLabel("Что ищем?");
        logInfo.setBounds(270, 170, 200, 30);
        add(logInfo);

        logText = new JTextArea();
        logText.setBounds(40, 220, 400, 60);
        add(logText);

        search = new JButton("Search");
        search.setBounds(450, 220, 100, 30);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(filesTree != null) {
                    remove(filesTree);
                }
                filesTree = new SimpleTree(new File(dirInfo.getText()), extension.getText(), logText.getText());
                add(filesTree);
                revalidate();
            }
        });
        add(search);

        filesTree = new JPanel();
        add(filesTree);


        JPanel panelForFileContent = new JPanel();
        panelForFileContent.setBounds(310, 300, 500, 355);
        fileContent = new JTextArea(22, 40);
        fileContent.setLineWrap(true);
        fileContent.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(fileContent,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panelForFileContent.add(scroll);
        add(panelForFileContent);

    }
}
