package painpoint.action;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import painpoint.decoration.PainPointPresentation;
import painpoint.dialog.PainPointPresentationFactory;
import painpoint.dialog.PluginDialog;
import painpoint.domain.painpoint.PainPointDomain;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

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

    private PainPointDomain mPainPointDomain;

    public EditorMenuItem() {
        super();
        mPainPointDomain = new PainPointDomain();
    }

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {

        // get the project instance.
        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);

        // get the currently chosen file.
        VirtualFile virtualFile = DataKeys.VIRTUAL_FILE.getData(actionEvent.getDataContext());

        PsiFile psiFile = actionEvent.getData(LangDataKeys.PSI_FILE);
        if(psiFile instanceof PsiJavaFile) {
            PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
            PainPointPresentation painPointPresentation = PainPointPresentationFactory.creatPresentation(project, virtualFile, psiJavaFile);

            PluginDialog pluginDialog = new PluginDialog(painPointPresentation, mPainPointDomain, project);
            pluginDialog.setSize(300, 150);
        }
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
