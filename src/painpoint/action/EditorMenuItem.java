package painpoint.action;

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

        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                PluginDialog pluginDialog = new PluginDialog(painPointPresentation, mPainPointDomain, project, false, true);
                pluginDialog.show();
            }
        });

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
