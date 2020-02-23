package bubnov.max.logger.back.util;

import bubnov.max.logger.ui.MainPanel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;

import static bubnov.max.logger.back.util.Utils.readFile;

public class HighlighterFindWord implements TreeSelectionListener {

    private String log;

    public HighlighterFindWord(String log){
        this.log = log;
    }

    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeSelectionEvent
                .getPath().getLastPathComponent();

        File exFile = new File(node.toString());
        if(!exFile.isDirectory()) {
            StringBuilder builder = readFile(exFile);
            MainPanel.fileContent.setText(builder.toString());

            final int count = searchWorld(builder.toString(), log);

            if(count >= 1) {
                Highlighter highlighter = MainPanel.fileContent.getHighlighter();
                Highlighter.HighlightPainter painter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.pink);

                int pos = 0;

                while ((pos=builder.toString().toLowerCase().indexOf(log.toLowerCase(), pos)) >= 0 ) {
                    try {
                        highlighter.addHighlight(pos, pos+log.length(), painter);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }

                    pos += log.length();
                }
            }
        }
    }

    private int searchWorld(String text, String word) {
        text = text.toLowerCase();
        word = word.toLowerCase();
        int i = text.indexOf(word);
        int count = 0;
        while (i >= 0) {
            count++;
            i = text.indexOf(word, i + 1);
        }

        return count;
    }
}
