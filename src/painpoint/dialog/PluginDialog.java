package painpoint.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPointDomain;

import javax.swing.*;
import java.awt.*;

public class PluginDialog extends DialogWrapper {

    private JFXPanel jfxPanel;

    public PluginDialog(PainPointPresentation painPointPresentation, PainPointDomain pointDomain, Project project, boolean canBeParent, boolean applicationModalIfPossible) {
        super(project, canBeParent, applicationModalIfPossible);
        setTitle("Pain Point Reports");
//        ClassCommentaryPanel classCommentaryPanel = new ClassCommentaryPanel(painPointPresentation, pointDomain, project);
//        jPanelLabeledComponent = LabeledComponent.create(classCommentaryPanel, "Report a pain point.");

//        setAutoAdjustable(true);
        jfxPanel = new PainPointFXPanel();
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return jfxPanel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return super.getPreferredFocusedComponent();
    }

    private class PainPointFXPanel extends JFXPanel {
        public PainPointFXPanel() {
            super();

            Platform.setImplicitExit(false);
            Platform.runLater(() -> {
                Group root  =  new Group();
                Scene scene  =  new  Scene(root, 200, 200);
//                FXMLLoader.load(getClass().getResource("application/sample.fxml"));
                scene.getStylesheets().add(getClass().getResource("custom.css").toExternalForm());
                Text text  =  new Text();


                setPreferredSize(new Dimension(800, 600));
                text.setX(100);
                text.setY(100);
                text.setFont(new Font(25));
                text.setText("FX Paint Point Panel");

                CheckBox checkBox = new CheckBox();
                checkBox.setPrefSize(200, 200);
                checkBox.setBackground(Background.EMPTY);
                checkBox.setSelected(true);
                checkBox.setText("Report Pain Point");

                root.getChildren().addAll(text, checkBox);

                setScene(scene);
            });
            setSize(200, 200);

        }
    }
}
