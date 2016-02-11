package classcommentary.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class PluginDialog extends DialogWrapper {

    private LabeledComponent<ClassCommentaryPanel> jPanelLabeledComponent;

    public PluginDialog(Project project, boolean canBeParent, boolean applicationModalIfPossible) {
        super(project, canBeParent, applicationModalIfPossible);

        setTitle("DialogTitle");

        ClassCommentaryPanel classCommentaryPanel = new ClassCommentaryPanel();
        jPanelLabeledComponent = LabeledComponent.create(classCommentaryPanel, "wtf mate");

        init();
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return jPanelLabeledComponent;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return super.getPreferredFocusedComponent();
    }
}
