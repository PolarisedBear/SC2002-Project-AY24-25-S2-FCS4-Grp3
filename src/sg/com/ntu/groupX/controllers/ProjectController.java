package sg.com.ntu.groupX.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.com.ntu.groupX.controllers.services.IProjectService;
import sg.com.ntu.groupX.views.ProjectView;

public class ProjectController implements ProjectView, IProjectService{
    private IProjectService projectService;
    private static List<Project> projectList;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
        projectList = new ArrayList<>();
    }

    public void createProject() {
        Project newProject = new Project();
        projectList.add(newProject);
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

    @Override
    public List<Project> findProjectsByManager(HDBManager manager) {
        return List.of();
    }

    @Override
    public boolean hasActiveProject(HDBManager manager, Date date) {
        return false;
    }
}
