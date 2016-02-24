package painpoint.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import org.jetbrains.annotations.Nullable;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPointDomain;

import javax.swing.*;

public class PluginDialog extends DialogWrapper {

    private LabeledComponent<ClassCommentaryPanel> jPanelLabeledComponent;

    public PluginDialog(PainPointPresentation painPointPresentation, PainPointDomain pointDomain, Project project, boolean canBeParent, boolean applicationModalIfPossible) {
        super(project, canBeParent, applicationModalIfPossible);
        setTitle("Pain Point Reports");
        ClassCommentaryPanel classCommentaryPanel = new ClassCommentaryPanel(painPointPresentation, pointDomain, project);
        jPanelLabeledComponent = LabeledComponent.create(classCommentaryPanel, "Report a pain point.");
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
