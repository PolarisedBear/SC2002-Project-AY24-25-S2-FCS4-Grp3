import java.util.List;

public class ProjectController implements ProjectView{
    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    public void createProject() {

    }

    public void editProject() {

    }

    public void deleteProject() {

    }

    public void toggleVisibility() {

    }

    public void getProjectList() {

    }

    public List<Project> getProjectsByManager() {

    }

    @Override
    public void displayProjectInfo() {

    }

    @Override
    public void displayProjectList() {

    }

    @Override
    public void showEditProjectForm() {

    }

    @Override
    public void showCreateProjectForm() {

    }
}
