package classcommentary.action;

import classcommentary.dialog.PluginDialog;
import classcommentary.domain.commentary.CommentaryDomain;
import classcommentary.domain.commentary.model.Commentary;
import classcommentary.domain.commentary.util.CommentaryUtil;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;

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

    private CommentaryDomain mCommentaryDomain;

    public EditorMenuItem() {
        super();
        mCommentaryDomain = new CommentaryDomain();
    }

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {

        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(actionEvent.getDataContext());
        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);
        Commentary commentary = getCommentary(currentFile, project.getName());

        createDialog(actionEvent);
        PluginManager.getLogger().warn("project");
    }

    private void createDialog(AnActionEvent actionEvent) {
        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);
        PluginDialog pluginDialog = new PluginDialog(project, false, true);
        pluginDialog.show();
    }

    public Commentary getCommentary(VirtualFile virtualFile, String projectName) {

        String className = virtualFile.getName();
        String filePath = virtualFile.getPath();
        int commentaryId = CommentaryUtil.commentaryId(className, filePath, projectName);

        Commentary commentary = mCommentaryDomain.getCommentaryForId(commentaryId);
        if(commentary == null) {
            commentary = new Commentary(commentaryId, className, filePath);
            try {
                mCommentaryDomain.insertCommentary(commentary);
                mCommentaryDomain.getCommentaryMap(true);
            }
            catch (SQLException sqlEx) {
                PluginManager.getLogger().warn("SQLClientInfoException sqlEx:" + sqlEx.getMessage());
            }
        }
        return commentary;

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
