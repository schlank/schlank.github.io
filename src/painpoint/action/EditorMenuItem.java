package painpoint.action;

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

import java.sql.SQLException;
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

        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(actionEvent.getDataContext());
        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);

        List<PainPoint> painPoints = getPainPoints(currentFile, project.getName());

        createDialog(project, new PainPointPresentation(painPoints));
        PluginManager.getLogger().warn("project");
    }

    private void createDialog(Project project, PainPointPresentation painPointPresentation) {


        PluginDialog pluginDialog = new PluginDialog(painPointPresentation, mPainPointDomain, project, false, true);
        pluginDialog.show();
    }

    public List<PainPoint> getPainPoints(VirtualFile virtualFile, String projectName) {
        String className = virtualFile.getName();
        String filePath = virtualFile.getPath();
        Integer classId = DataModelUtil.classFileId(className, filePath, projectName);
        return mPainPointDomain.getPainPointsForClassId(true, classId);
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
