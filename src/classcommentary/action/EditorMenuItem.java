package classcommentary.action;

import classcommentary.dialog.ClassCommentaryPanel;
import classcommentary.dialog.PluginDialog;
import classcommentary.model.Commentary;
import classcommentary.model.CommentaryFactory;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class EditorMenuItem extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
//        logActionStatus(actionEvent);
//        createDialog(actionEvent);
        CommentaryFactory commentaryFactory = new CommentaryFactory();
        Commentary commentary = new Commentary(null,"classheredude.java","filepath");
        commentaryFactory.insertCommentary(commentary);
    }

//    private void databaseConnection() throws ClassNotFoundException, SQLException {
//
////        JdbcConnectionPool cp = JdbcConnectionPool.create(
////                "jdbc:h2:~/test", "sa", "sa");
////        for (int i = 0; i < args.length; i++) {
////            Connection conn = cp.getConnection();
////            conn.createStatement().execute(args[i]);
////            conn.close();
//
//        // delete the database named 'test' in the user home directory
////
//        Class.forName("org.h2.Driver");
//
//            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
//            Statement stat = conn.createStatement();
//
//            // this line would initialize the database
//            // from the SQL script file 'init.sql'
//            // stat.execute("runscript from 'init.sql'");
//            PluginManager.getLogger().warn("FO");
//            stat.execute("create table test(id int primary key, name varchar(255))");
//            stat.execute("insert into test values(1, 'Hello')");
//            ResultSet rs;
//            rs = stat.executeQuery("select * from test");
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//                PluginManager.getLogger().warn(rs.getString("name"));
//            }
//            stat.close();
//            conn.close();
//    }

    private void createDialog(AnActionEvent actionEvent) {

        final Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);
        final Editor editor = actionEvent.getRequiredData(CommonDataKeys.EDITOR);
        final String className = getActiveFileName(editor);
        final String classPath = getActiveFilePath(editor);

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
        PluginManager.getLogger().warn("ProjectFile: " + virtualFile.getName());

        String place = actionEvent.getPlace();
        PluginManager.getLogger().warn("place: " + place);
        PluginManager.getLogger().warn("selectedDocument: " + getActiveFilePath(editor));
        PluginManager.getLogger().warn("fileName: " + getActiveFileName(editor));
    }

    private @Nullable VirtualFile getActiveVirtualFile(Editor editor) {
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
