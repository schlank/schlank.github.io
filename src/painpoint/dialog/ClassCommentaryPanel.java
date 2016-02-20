package painpoint.dialog;

import painpoint.decoration.PainPointPresentation;

import javax.swing.*;
import java.awt.*;

public class ClassCommentaryPanel extends JPanel {
    private JTextArea mCommentField;
    private JPanel mRootPanel;
    private JCheckBox mHighRiskCB;
    private JRadioButton mTestCoverageYesRadio;
    private JRadioButton mTestCoverageNoRadio;
    private JRadioButton mTestableYesRadio;
    private JRadioButton mTestableNoRadio;
    private JRadioButton mJavaDocsYesRadio;
    private JRadioButton mJavaDocsNoRadio;
    private JCheckBox mPainPointCB;

    public ClassCommentaryPanel(PainPointPresentation painPointPresentation) throws HeadlessException {
        super();
        setVisible(true);
        add(mRootPanel);

        //TODO based on painPointPresentation - Change the UI
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
