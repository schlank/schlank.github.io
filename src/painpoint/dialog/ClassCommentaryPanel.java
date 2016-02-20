package painpoint.dialog;

import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

public class ClassCommentaryPanel extends JPanel {
    private JPanel mRootPanel;
    private JCheckBox mGoodExampleCB;
    private JCheckBox mPainPointCB;
    private JList mThumbsUpList;
    private JList mThumbsDownList;

    private PainPointPresentation mPainPointPresentation;


    public ClassCommentaryPanel(PainPointPresentation painPointPresentation) throws HeadlessException {
        super();
        setVisible(true);
        add(mRootPanel);
        mPainPointPresentation = painPointPresentation;
        List<PainPoint> thumbsUpList = mPainPointPresentation.getThumbsUpList();
        List<PainPoint> thumbsDownList = mPainPointPresentation.getThumbsDownList();

        for(PainPoint painPoint : thumbsUpList) {
            JTextComponent textComponent = new JTextField();
            textComponent.setText("Thumbs Up");
            mThumbsUpList.add(textComponent);
        }
        for(PainPoint painPoint : thumbsDownList) {
            JTextComponent textComponent = new JTextField();
            textComponent.setText("Thumbs Down");
            mThumbsDownList.add(textComponent);
        }
        //TODO based on painPointPresentation - Change the UI
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
