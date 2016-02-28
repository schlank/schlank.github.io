package painpoint.action;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vcs.QuantitySelection;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBCheckBox;
import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import painpoint.component.ProjectViewManager;
import painpoint.component.ViewNodeUtil;
import painpoint.decoration.PainPointPresentation;
import painpoint.dialog.PluginDialog;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.painpoint.model.PainPointDomain;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import painpoint.domain.commentary.util.DataModelUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Notes for phil:
 * PSIViewer could get me a away to hover an icon over a specific element in a file.  Like annotations.
 * Check RoboHexar for examples.  Maybe it uses it.c
 *
 JBPopupFactory.getInstance()
 .createHtmlTextBalloonBuilder(htmlText, messageType, null)
 .setFadeoutTime(7500)
 .createBalloon()
 .show(RelativePoint.getCenterOf(statusBar.getComponent()),
 Balloon.Position.atRight);
 */
public class EditorMenuItem extends AnAction {
    private final Logger LOG = Logger.getInstance(getClass());

    private PainPointDomain mPainPointDomain;

    public EditorMenuItem() {
        super();
        mPainPointDomain = new PainPointDomain();
    }

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {

        // get the project instance.
        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);

        // with the project instance get our project view manager.
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);

        // get the current pair user(s)
        String gitPairString = projectViewManager.getPairString();
        String projectName = project.getName();

        // get the currently chosen file.
        VirtualFile virtualFile = DataKeys.VIRTUAL_FILE.getData(actionEvent.getDataContext());

        // With the file and project name get the classID
        Integer classId = projectViewManager.getClassId(virtualFile, projectName);

        //get out list of pain points.
        List<PainPoint> painPoints = projectViewManager.getPainPoints(classId, projectName);

        //Create the show the dialog.
        createDialog(project, new PainPointPresentation(classId, gitPairString, painPoints));

    }

    private void createDialog(Project project, PainPointPresentation painPointPresentation) {

        PluginDialog pluginDialog = new PluginDialog(project, painPointPresentation, mPainPointDomain);
        pluginDialog.setSize(300, 150);
//        Platform.setImplicitExit(false);
//        Platform.runLater(() -> {
//            Group root  =  new Group();
//            Scene scene  =  new  Scene(root, Color.ALICEBLUE);
//            Text text  =  new Text();
//
//            text.setX(40);
//            text.setY(100);
//            text.setFont(new Font(25));
//            text.setText("Welcome JavaFX!");
//
//            root.getChildren().add(text);
//
//            fxPanel.setScene(scene);
//        });
//
//        component.getParent().add(fxPanel);


//        Platform.setImplicitExit(false);
//        Platform.runLater(() -> {
//            Stage dialog = new Stage();
//            dialog.initStyle(StageStyle.UTILITY);
//            Scene scene = new Scene(new Group(new Text(25, 25, "Hello World!")));
//            dialog.setScene(scene);
//            dialog.show();
//        });

//        JFXPanel jfxPanel = new JFXPanel();
//        jfxPanel.setSize(120, 200);
//        Platform.setImplicitExit(false);
//
//        Platform.setImplicitExit(false);
//        Platform.runLater(() -> {
//            Group root  =  new Group();
//            Scene scene  =  new  Scene(root);
//            CheckBox checkBox = new CheckBox();
//            checkBox.setLayoutX(13);
//            checkBox.setLayoutY(19);
//            checkBox.setPrefHeight(101);
//            checkBox.setPrefWidth(181);
//            checkBox.setText("Report Pain Point");
//
//            TitledPane titledPane = new TitledPane();
//            titledPane.setPrefSize(202.0,218.0);
//            titledPane.setLayoutX(5);
//            titledPane.setLayoutY(5);
//            titledPane.setExpanded(true);
//            titledPane.setMaxHeight(-1);
//            titledPane.setMaxWidth(-1);
//            titledPane.setMinWidth(-1);
//            titledPane.setMinHeight(-1);
//            titledPane.setText("Pain Points");
//            titledPane.setContent(checkBox);
//            root.getChildren().add(titledPane);
//            jfxPanel.setScene(scene);
//        });
    }

    private @Nullable
    VirtualFile getActiveVirtualFile(Editor editor) {
        Document currentDoc = editor.getDocument();
        return FileDocumentManager.getInstance().getFile(currentDoc);
    }

    private @Nullable
    String getActiveFileName(Editor editor) {
        VirtualFile currentFile = getActiveVirtualFile(editor);
        String fileName = null;
        if(currentFile != null) {
            fileName = currentFile.getName();
            PluginManager.getLogger().warn("fileName: " + fileName);
        }
        else {
            PluginManager.getLogger().warn("No file name found!");
        }
        return fileName;
    }

    private @Nullable
    String getActiveFilePath(Editor editor) {
        VirtualFile currentFile = getActiveVirtualFile(editor);
        String fileNamePath = null;
        if(currentFile != null) {
            fileNamePath = currentFile.getPath();
            PluginManager.getLogger().warn("fileNamePath: " + fileNamePath);
        }
        else {
            PluginManager.getLogger().warn("No file path found!");
        }
        return fileNamePath;
    }
}
