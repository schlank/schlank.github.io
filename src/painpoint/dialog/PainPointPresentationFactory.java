package painpoint.dialog;

import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import painpoint.component.ProjectViewManager;
import painpoint.decoration.ClassFileIdCalulator;
import painpoint.decoration.PainPointPresentation;
import painpoint.domain.painpoint.model.PainPoint;
import java.util.List;

public class PainPointPresentationFactory {

    public static PainPointPresentation creatPresentation(Project project, ClassTreeNode classTreeNode) {

        //TODO I dont like this dependency
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);

        String gitPairUser = projectViewManager.getPairString();
        Integer classId = ClassFileIdCalulator.classIdForNode(classTreeNode, gitPairUser);
        String classFileName = ClassFileIdCalulator.classFileNameForNode(classTreeNode);
        List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);

        return new PainPointPresentation(classId, gitPairUser, painPoints, classFileName);
    }

    public static PainPointPresentation creatPresentation(Project project, VirtualFile virtualFile) {

        //TODO I dont like this dependency
        ProjectViewManager projectViewManager = ProjectViewManager.getInstance(project);

        String gitPairUser = projectViewManager.getPairString();
        Integer classId = ClassFileIdCalulator.classIdForVirtualFile(project, virtualFile, gitPairUser);
        String classFileName = virtualFile.getName();
        List<PainPoint> painPoints = projectViewManager.getPainPointsForClassId(classId);

        return new PainPointPresentation(classId, gitPairUser, painPoints, classFileName);
    }
}
