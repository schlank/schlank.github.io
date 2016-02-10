package classcommentary.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class PluginDialog extends DialogWrapper {
    public PluginDialog(Project project, boolean canBeParent, boolean applicationModalIfPossible) {
        super(project, canBeParent, applicationModalIfPossible);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        ContentComponent commentaryComponent = new ContentComponent();

        JPanel testPane = new JPanel();
        testPane.setBackground(Color.white);
        testPane.setLayout(new GridBagLayout());

        JFrame frame = new JFrame("Testing");
        frame.setLayout(new BorderLayout());
        frame.add(testPane);
        frame.pack();
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        commentaryComponent.add(testPane);
        return commentaryComponent;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return super.getPreferredFocusedComponent();
    }
}
