package painpoint.dialog;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.apache.commons.lang.StringUtils;
import painpoint.component.ProjectViewManager;
import painpoint.decoration.ClassFileIdCalulator;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.git.GitRunner;
import painpoint.pairing.PairConfig;
import painpoint.pairing.PairController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainPointPresentationFactory {

    public static PainPointPresentation creatPresentation(Project project, ClassTreeNode classTreeNode) {

        //TODO I dont like this dependency
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);
        String gitUsername = PainPointPresentationFactory.getGitUsername(project);

        //TODO these methods in ClassFileIdCalulator do too much.  Do more here or split the functions up.
        Integer classId = ClassFileIdCalulator.classIdForNode(classTreeNode, gitUsername);
        String classFileName = ClassFileIdCalulator.classFileNameForNode(classTreeNode);
        List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);

        PsiClass psiClass = classTreeNode.getPsiClass();
        int todoCount = getTodoCount(psiClass);

        return new PainPointPresentation(classId, gitUsername, painPoints, classFileName, todoCount);
    }

    public static String getGitUsername(Project project) {

        String gitUsername = "";
        if (project == null) {
            return gitUsername;
        }

        String projectPath = project.getBasePath();
        if (projectPath == null) {
            return gitUsername;
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
            return gitUsername;
        }

        PairConfig pairConfig = new PairConfig(configYaml);
        GitRunner gitRunner = new GitRunner(projectPath);
        PairController pairController = new PairController(pairConfig, gitRunner);
        pairController.init();

        gitUsername = pairController.getPairDisplayName();

        if(gitUsername == null || gitUsername.isEmpty()) {
            gitUsername = gitRunner.getUserName();
        }

        return gitUsername;
    }

    public static int getTodoCount(PsiClass psiClass) {
        return StringUtils.countMatches(psiClass.getText().toLowerCase(), "todo");
    }

    public static int getTodoCount(PsiFile psiFile) {
        return StringUtils.countMatches(psiFile.getText().toLowerCase(), "todo");
    }

    public static PainPointPresentation creatPresentation(Project project, VirtualFile virtualFile, PsiJavaFile psiJavaFile) {

        //TODO I dont like this dependency
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);

        String gitPairUser = PainPointPresentationFactory.getGitUsername(project);
        Integer classId = ClassFileIdCalulator.classIdForVirtualFile(project, virtualFile, gitPairUser);
        String classFileName = virtualFile.getName();
        List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);

        int todoCount = getTodoCount(psiJavaFile);
        return new PainPointPresentation(classId, gitPairUser, painPoints, classFileName, todoCount);
    }
}
