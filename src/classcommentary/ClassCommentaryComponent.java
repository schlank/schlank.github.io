package classcommentary;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ClassCommentaryComponent implements ProjectComponent {


    private Project mProject;

    public ClassCommentaryComponent(Project project) {
        mProject = project;
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "classcommentary.ClassCommentaryComponent";
    }

    @Override
    public void projectOpened() {
        // called when project is opened
    }

    @Override
    public void projectClosed() {
        // called when project is being closed
    }
}
