package painpoint.dialog;

import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

public class ClassCommentaryPanel extends JPanel {
    private JPanel mRootPanel;
    private JCheckBox mPainPointCB;
    private JList mThumbsDownList;

    private PainPointPresentation mPainPointPresentation;


    public ClassCommentaryPanel(PainPointPresentation painPointPresentation) throws HeadlessException {
        super();
        setVisible(true);
        add(mRootPanel);
        mPainPointPresentation = painPointPresentation;
        mPainPointCB.setSelected(mPainPointPresentation.isPinned());
        populateList(mPainPointPresentation.getPainPointPresentations());
    }

    private void populateList(List<PainPoint> painPoints) {
        for(PainPoint painPoint : painPoints) {
            JTextComponent textComponent = new JTextField();
            textComponent.setText("Reported Pain Point:" + painPoint.getClassId());
            mThumbsDownList.add(textComponent);
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        mPainPointCB.setIcon(new ImageIcon("voodoo-doll-happy.png"));
        mPainPointCB.setSelectedIcon(new ImageIcon("voodoo-doll-sad.png"));
    }
}
