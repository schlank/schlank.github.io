package painpoint.component;

import painpoint.decoration.DecorationToggleNotifier;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.committed.VcsConfigurationChangeListener;
import com.intellij.util.messages.MessageBusConnection;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.painpoint.PainPointDomain;
import painpoint.git.GitRunner;
import painpoint.pairing.PairConfig;
import painpoint.pairing.PairController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProjectViewManager extends AbstractProjectComponent {

    private MessageBusConnection mConnection;
    private PainPointDomain mPainPointDomain;
    private PairController mPairController;

    public ProjectViewManager(Project project) {
        super(project);
        updateState(project);
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

    public List<PainPoint> getPainPointsForClassId(int classId) {
        return mPainPointDomain.getPainPointsForClassId(true, classId);
    }

    /**
     * Ask git who is the current user, and update our internal state.
     */
    public boolean updateState(Project project) {
        if (project == null) {
            return false;
        }

        String projectPath = project.getBasePath();
        if (projectPath == null) {
            return false;
        }

        String configFile = projectPath.concat("/.pairs");
        String configYaml = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(configFile));
            String line;
            while ((line = br.readLine()) != null) {
                configYaml += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Git Pair plugin couldn't open " + configFile + ": " + e.getMessage());
            return false;
        }
        PairConfig pairConfig = new PairConfig(configYaml);
        GitRunner gitRunner = new GitRunner(projectPath);
        mPairController = new PairController(pairConfig, gitRunner);
        mPairController.init();

        return true;
    }
}
