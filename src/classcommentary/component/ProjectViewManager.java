package classcommentary.component;

import classcommentary.domain.commentary.CommentaryDomain;
import classcommentary.domain.commentary.model.Commentary;
import classcommentary.projectView.DecorationSettingsNotifier;
import classcommentary.projectView.DecorationToggleNotifier;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBusConnection;

import java.sql.SQLException;
import java.util.Map;

public class ProjectViewManager extends AbstractProjectComponent {

    private final Logger LOG = Logger.getInstance(getClass());
    private MessageBusConnection mConnection;
    private CommentaryDomain mCommentaryDomain;

    public ProjectViewManager(Project project) {
        super(project);
        mCommentaryDomain = new CommentaryDomain();
    }

    public static ProjectViewManager getInstance(Project project) {
        return project.getComponent(ProjectViewManager.class);
    }

    @Override
    public void initComponent() {
        super.initComponent();
        initBusSubstription();
        refreshProjectView(myProject);
    }

    public void refreshProjectView(final Project project) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                final ProjectView projectView = ProjectView.getInstance(project);
                PluginManager.getLogger().warn("[ WAT ] Refreshing Project View");
                try {
                    mCommentaryDomain.getCommentaryMap(true);
                }
                catch (SQLException sqlEx) {
                    PluginManager.getLogger().warn("[ WAT ] SQLException:" + sqlEx.getMessage());
                }
                projectView.refresh();
            }
        });
    }

    private void initBusSubstription() {

        mConnection = myProject.getMessageBus().connect();
        mConnection.subscribe(DecorationToggleNotifier.TOGGLE_TOPIC, new DecorationToggleNotifier() {
            @Override
            public void decorationChanged(Project project) {
                refreshProjectView(project);
            }
        });
        mConnection.subscribe(VcsConfigurationChangeListener.BRANCHES_CHANGED, new VcsConfigurationChangeListener.Notification() {
            @Override
            public void execute(Project project, VirtualFile vcsRoot) {
                refreshProjectView(project);
            }
        });
        mConnection.subscribe(DecorationSettingsNotifier.TOGGLE_TOPIC, new DecorationSettingsNotifier() {
            @Override
            public void settingsChanged() {
                refreshProjectView(myProject);
            }
        });
    }

    public Commentary getCommentaryForId(int commentaryId) {
        return null;
    }
}
