package painpoint.dialog;

import com.intellij.openapi.project.Project;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.painpoint.model.PainPointDomain;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

public class ClassCommentaryPanel extends JPanel {
    private JPanel mRootPanel;
    private JList mThumbsDownList;
    private JScrollPane mScrollPane;
    private JButton mPainPointButton;

    private PainPointPresentation mPainPointPresentation;
    private PainPointDomain mPainPointDomain;

    public ClassCommentaryPanel(PainPointPresentation painPointPresentation, PainPointDomain painPointDomain, Project project) throws HeadlessException {
        super();

        setVisible(true);

        ImageIcon voodooIcon = new ImageIcon("voodoo-doll-happy-pinned.png");
        ImageIcon selectedIcon =  new ImageIcon("voodoo-doll-sad.png");

        mPainPointButton = new VoodooButton(
                "Pain Point reported.",
                "Report Pain Point",
                selectedIcon,
                voodooIcon
        );
        mPainPointButton.setSize(100, 200);


        add(mRootPanel);
        mPainPointPresentation = painPointPresentation;
        mPainPointDomain = painPointDomain;
//        mPainPointCB.setSelected(mPainPointPresentation.isPinned());
//        populateList(mPainPointPresentation.getPainPointPresentations());
//        ActionListener actionListener = actionEvent -> {
//            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
//            boolean selected = abstractButton.getModel().isSelected();
//            updateSelectionData(selected);
//        };
//        mPainPointCB.addActionListener(actionListener);
        // TODO: place custom component creation code here

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

//    /** Returns an ImageIcon, or null if the path was invalid. */
//    private ImageIcon createImageIcon(String path, String description) {
//        java.net.URL imgURL = getClass().getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL, description);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
}