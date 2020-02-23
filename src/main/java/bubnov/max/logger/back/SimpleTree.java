package bubnov.max.logger.back;

import bubnov.max.logger.back.util.HighlighterFindWord;
import bubnov.max.logger.back.util.Utils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class SimpleTree extends JPanel {
    JTree tree;
    DefaultMutableTreeNode root;
    private String log;

    public SimpleTree(File file, String extension, final String log) {
        this.log = log;
        setBounds(40, 300, 250, 355);
        root = new DefaultMutableTreeNode("root", true);
        getList(root, file, extension);
        setLayout(new BorderLayout());
        tree = new JTree(root);
        tree.addTreeSelectionListener(new HighlighterFindWord(log));
        tree.setRootVisible(false);
        add(new JScrollPane(tree),"Center");
        revalidate();
    }

    public void getList(DefaultMutableTreeNode node, File f, String extension){
        if(!f.isDirectory()) {
            if (f.getName().endsWith(extension) && hasSearchText(f)) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
                node.add(child);
            }
        }
        else {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
            node.add(child);
            File[] fileList = f.listFiles();
            for(int i = 0; i  < fileList.length; i++)
                getList(child, fileList[i], extension);
        }
    }

    private boolean hasSearchText(File file) {
        StringBuilder builder = readFile(file);
        return builder.toString().contains(Objects.requireNonNull(log));
    }

    private StringBuilder readFile(File file) {
        return Utils.readFile(file);
    }
}