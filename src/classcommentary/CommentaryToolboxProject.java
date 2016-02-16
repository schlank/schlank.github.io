package classcommentary;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CommentaryToolboxProject extends AbstractProjectComponent {
    private final Logger LOG = Logger.getInstance(getClass());

    private final Supplier<Integer> PV_SEQ = new Supplier<Integer>() {
        @Override
        public Integer get() {
            return null;
            //TODO this does not work.
        }
    };

//    private SvnBranchWidget myBranchWidget;

    public CommentaryToolboxProject(@NotNull Project project) {
        super(project);
    }

    public static CommentaryToolboxProject getInstance(@NotNull Project project) {
        return project.getComponent(CommentaryToolboxProject.class);
    }

    public Supplier<Integer> sequence() {
        return PV_SEQ;
    }

    @Override
    public void projectOpened() {
        if (!ApplicationManager.getApplication().isHeadlessEnvironment()) {
//            myBranchWidget = new SvnBranchWidget(myProject);
            StatusBar statusBar = WindowManager.getInstance().getStatusBar(myProject);
            if (statusBar != null) {
//                statusBar.addWidget(myBranchWidget, myProject);
            }
        }
        PluginManager.getLogger().warn("Project opened");
    }

    @Override
    public void projectClosed() {
//        if (myBranchWidget != null && !ApplicationManager.getApplication().isHeadlessEnvironment()) {
//            StatusBar statusBar = WindowManager.getInstance().getStatusBar(myProject);
//            if (statusBar != null) {
//                statusBar.removeWidget(myBranchWidget.ID());
//            }
//        }
        PluginManager.getLogger().warn("Project closed");
    }
}
