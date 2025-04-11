package sg.com.ntu.group3.controllers;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.com.ntu.group3.controllers.services.IProjectService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.views.ProjectView;

public class ProjectController implements IProjectService{


    public ProjectController() {}

    public void createProject(HDBManager manager) throws ParseException {
        Project newProject = ProjectView.showCreateProjectForm();
        manager.createProject(newProject);
        System.out.println("Project created successfully");
    }

    public void addProject() {
        // Prob a function to add unclaimed or blank project
        Project newProject = new Project();
        newProject.setVisible(true);
        newProject.setName("Blank Project");
    }

    public void editProject() {

    }

    public void deleteProject() {

    }

    public void toggleVisibility(Project project, boolean isVisible) {
        project.setVisible(isVisible);
    }

    public void getProjectList() {

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
