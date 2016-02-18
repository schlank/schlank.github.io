package classcommentary.dialog;

import javax.swing.*;
import java.awt.*;

public class ClassCommentaryPanel extends JPanel {
    private JTextArea mCommentField;
    private JCheckBox mLoveItCheckbox;
    private JCheckBox mDownVoteCheckbox;
    private JPanel mTopPanel;

    public ClassCommentaryPanel() throws HeadlessException {
        super();
        setVisible(true);
        add(mTopPanel);
        mCommentField.setText("Comment here.");
    }


}
