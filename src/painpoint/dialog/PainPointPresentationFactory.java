package painpoint.dialog;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.ide.caches.FileContent;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;
import com.intellij.util.indexing.DataIndexer;
import gnu.trove.THashMap;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import painpoint.component.ProjectViewManager;
import painpoint.decoration.ClassFileIdCalulator;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;
import painpoint.domain.util.DataModelUtil;
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
        Integer classId = ClassFileIdCalulator.classIdForNode(classTreeNode);

        Integer painPointId = DataModelUtil.generatePainPointId(classId, gitUsername);
        String classFileName = ClassFileIdCalulator.classFileNameForNode(classTreeNode);
        List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);

        PsiClass psiClass = classTreeNode.getPsiClass();
        int todoCount = getTodoCount(psiClass);

        return new PainPointPresentation(classId, painPointId, gitUsername, painPoints, classFileName, todoCount);
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

    public static int getTodoCountSimple(PsiFile psiFile) {

        int todoCount = 0;
        String[] lines = psiFile.getText().split("\n").clone();
        for(String line : lines) {

            if(StringUtils.containsIgnoreCase(line, "todo")) {
                todoCount++;
            }
        }
        return todoCount;
    }

    public static int getTodoCount(PsiFile psiFile) {

        final List<String>todoList = new ArrayList<>();
        psiFile.accept(new PsiRecursiveElementWalkingVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                super.visitElement(element);

                if(element instanceof PsiCommentImpl) {

                    String commentText = element.getText();
                    if(StringUtils.containsIgnoreCase(commentText, "TODO")) {
                        todoList.add(element.getText());
                    }
                }

            }
            private boolean checkForTodo(PsiElement psiElement) {


                return false;
            }

                });
        return todoList.size();
    }

    public static PainPointPresentation creatPresentation(Project project, VirtualFile virtualFile, PsiJavaFile psiJavaFile) {

        //TODO I dont like this dependency
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);

        String gitPairUser = PainPointPresentationFactory.getGitUsername(project);
        Integer classId = ClassFileIdCalulator.classIdForVirtualFile(project, virtualFile);
        Integer painPointId = DataModelUtil.generatePainPointId(classId, gitPairUser);

        String classFileName = virtualFile.getName();
        List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);

        int todoCount = getTodoCount(psiJavaFile);
        return new PainPointPresentation(classId, painPointId, gitPairUser, painPoints, classFileName, todoCount);
    }
}
