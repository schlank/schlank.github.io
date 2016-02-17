package classcommentary.component;

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

public class ProjectViewManager extends AbstractProjectComponent {
    private final Logger LOG = Logger.getInstance(getClass());

    private MessageBusConnection myConnection;

    public ProjectViewManager(Project project) {
        super(project);
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
                        projectView.refresh();
                    }
                });
    }

    private void initBusSubstription() {

        myConnection = myProject.getMessageBus().connect();
        myConnection.subscribe(DecorationToggleNotifier.TOGGLE_TOPIC, new DecorationToggleNotifier() {
            @Override
            public void decorationChanged(Project project) {
                refreshProjectView(project);
            }
        });
        myConnection.subscribe(VcsConfigurationChangeListener.BRANCHES_CHANGED, new VcsConfigurationChangeListener.Notification() {
            @Override
            public void execute(Project project, VirtualFile vcsRoot) {
                refreshProjectView(project);
            }
        });
        myConnection.subscribe(DecorationSettingsNotifier.TOGGLE_TOPIC, new DecorationSettingsNotifier() {
            @Override
            public void settingsChanged() {
                refreshProjectView(myProject);
            }
        });
    }
}
