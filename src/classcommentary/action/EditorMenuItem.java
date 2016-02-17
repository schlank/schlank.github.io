package classcommentary.action;

import classcommentary.dialog.ClassCommentaryPanel;
import classcommentary.dialog.PluginDialog;
import classcommentary.model.Commentary;
import classcommentary.model.CommentaryFactory;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

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

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
//        logActionStatus(actionEvent);
//        createDialog(actionEvent);
        CommentaryFactory commentaryFactory = new CommentaryFactory();
        Commentary commentary = new Commentary(null,"classheredude11.java","/fsdfjjdfsaf/asdffd/classhderedude11.java");
        Commentary commentary2 = new Commentary(null,"classheredude12.java","/fsdfjjdfsaf/asdffd/classhderedude12.java");
        commentaryFactory.insertCommentary(commentary);
        commentaryFactory.insertCommentary(commentary2);
        createDialog(actionEvent);
    }

    private void createDialog(AnActionEvent actionEvent) {

        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);
//        final Editor editor = actionEvent.getRequiredData(CommonDataKeys.EDITOR);
//        final String className = getActiveFileName(editor);
//        final String classPath = getActiveFilePath(editor);

        PluginDialog pluginDialog = new PluginDialog(project, false, true);
        pluginDialog.show();
    }

    private void logActionStatus(AnActionEvent actionEvent) {

        //Get all the required data from data keys
        final Editor editor = actionEvent.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);
        final SelectionModel selectionModel = editor.getSelectionModel();
        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();

        PluginManager.getLogger().warn("Logging Starts Here ============================================>");

        String projectName = project.getName();
        PluginManager.getLogger().warn("Project: "+projectName);

        VirtualFile virtualFile = project.getProjectFile();
        if(virtualFile != null)
        PluginManager.getLogger().warn("ProjectFile: " + virtualFile.getName());

        String place = actionEvent.getPlace();
        PluginManager.getLogger().warn("place: " + place);
        PluginManager.getLogger().warn("selectedDocument: " + getActiveFilePath(editor));
        PluginManager.getLogger().warn("fileName: " + getActiveFileName(editor));
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
