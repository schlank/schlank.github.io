package painpoint.dialog;

import com.intellij.openapi.project.Project;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.painpoint.model.PainPointDomain;
import painpoint.pairing.TeamMember;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClassCommentaryPanel extends JPanel {
    private JPanel mRootPanel;
    private JCheckBox mPainPointCB;
    private JList mThumbsDownList;

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
        if(mPainPointPresentation.isPinned() !=isCheckboxSelected) {
            // TODO dont use a reference to the domain here, have something else listen or publish it somehow.
            Integer painPointId = mPainPointPresentation.getmClassId();
            String gitPairUser = mPainPointPresentation.getGitPairString();
            mPainPointDomain.addOrUpdateForClass(painPointId, gitPairUser, isCheckboxSelected);
        }
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
        mPainPointCB.setSelectedIcon(new ImageIcon("voodoo-doll-sad-pinned.png"));
        mPainPointCB.setRolloverIcon(new ImageIcon("voodoo-doll-sad.png"));
        mPainPointCB.setRolloverSelectedIcon(new ImageIcon("voodoo-doll-happy-pinned.png"));
    }
}
