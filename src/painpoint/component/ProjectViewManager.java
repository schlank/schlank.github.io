package painpoint.component;

import painpoint.decoration.DecorationToggleNotifier;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener;
import com.intellij.util.messages.MessageBusConnection;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.painpoint.model.PainPointDomain;

import java.sql.SQLException;
import java.util.List;

public class ProjectViewManager extends AbstractProjectComponent {

    private final Logger LOG = Logger.getInstance(getClass());
    private MessageBusConnection mConnection;
    private PainPointDomain mPainPointDomain;

    public ProjectViewManager(Project project) {
        super(project);
        mPainPointDomain = new PainPointDomain();
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
        ApplicationManager.getApplication().invokeLater(() -> {
            final ProjectView projectView = ProjectView.getInstance(project);
            PluginManager.getLogger().warn("[ WAT ] Refreshing Project View");
            try {
                mPainPointDomain.getPainPointMap(true);
            }
            catch (SQLException sqlEx) {
                PluginManager.getLogger().warn("[ WAT ] SQLException:" + sqlEx.getMessage());
            }
            projectView.refresh();
        });
    }

    private void initBusSubstription() {
        mConnection = myProject.getMessageBus().connect();
        mConnection.subscribe(DecorationToggleNotifier.TOGGLE_TOPIC, this::refreshProjectView);
        mConnection.subscribe(VcsConfigurationChangeListener.BRANCHES_CHANGED, (project, vcsRoot) -> refreshProjectView(project));
    }

    public PainPoint getPainPointForId(int painPointId) {
        return mPainPointDomain.getPainPointForId(true, painPointId);
    }

    public List<PainPoint> getPainPointsForClassId(int classId) {
        return mPainPointDomain.getPainPointsForClassId(true, classId);
    }
}
