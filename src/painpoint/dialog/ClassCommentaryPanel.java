package painpoint.dialog;

import com.intellij.openapi.project.Project;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.painpoint.model.PainPointDomain;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ClassCommentaryPanel extends JPanel {
    private JPanel mRootPanel;
    private JList mThumbsDownList;
    private VoodooCheckbox mPainPointCB;
    private VoodooCheckbox checkBox1;

    private PainPointPresentation mPainPointPresentation;
    private PainPointDomain mPainPointDomain;


    public ClassCommentaryPanel(PainPointPresentation painPointPresentation, PainPointDomain painPointDomain, Project project) throws HeadlessException {
        super();
        setVisible(true);
        add(mRootPanel);
        mPainPointPresentation = painPointPresentation;
        mPainPointDomain = painPointDomain;
        mPainPointCB.setSelected(mPainPointPresentation.isPinned());
        populateList(mPainPointPresentation.getPainPointPresentations());
        ActionListener actionListener = actionEvent -> {
            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            updateSelectionData(selected);
        };
        mPainPointCB.addActionListener(actionListener);
    }

    private void updateSelectionData(boolean isCheckboxSelected) {
        if (mPainPointPresentation.isPinned() != isCheckboxSelected) {
            Integer painPointId = mPainPointPresentation.getmClassId();
            String gitPairUser = mPainPointPresentation.getGitPairString();
            mPainPointDomain.addOrUpdateForClass(painPointId, gitPairUser, isCheckboxSelected);
        }
    }

    private void populateList(List<PainPoint> painPoints) {
        for (PainPoint painPoint : painPoints) {
            JTextComponent textComponent = new JTextField();
            textComponent.setText("Reported Pain Point:" + painPoint.getClassId());
            mThumbsDownList.add(textComponent);
        }
    }
}