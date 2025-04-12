package sg.com.ntu.group3.controllers;
import java.text.ParseException;
import java.util.*;

import sg.com.ntu.group3.controllers.services.IProjectService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.views.ProjectView;

public class ProjectController extends ProjectView implements IProjectService{


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

    public void editProject(Project project) throws ParseException {
        String attributeToEdit = ProjectView.showEditProjectForm().toLowerCase(Locale.ROOT);
        if (isValidAttribute(attributeToEdit)) {
            switch (attributeToEdit) {
                case "name":
                    String newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                    project.setName(newAttribute); break;
                case "flattypes":
                    int addOrRemove = ProjectView.showEditProjectFlatTypes(project);
                    switch (addOrRemove) {
                        case 0:
                            String name = ProjectView.showRemoveProjectFlatTypes(project);
                            boolean successful = project.removeFlatType(name);
                            ProjectView.showOperationOutcome(successful); break;
                        case 1:
                            name = ProjectView.showAddProjectFlatTypes(project);
                            successful = project.addFlatType(name);
                            ProjectView.showOperationOutcome(successful); break;
                        default:
                            System.out.println("Operation cancelled");
                    }
                case "neighbourhood":
                    newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                    project.setNeighbourhood(newAttribute); break;
                case "closedate":
                    Date newCloseDate = ProjectView.showEditProjectCloseDate(project);
                    project.setCloseDate(newCloseDate); break;
                case "visibility":
                    project.setVisible(!project.isVisible());
                    ProjectView.showOperationOutcome(true); break;
                case "maxofficers":
                    newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                    project.setMaxOfficers(Integer.parseInt(newAttribute)); break;
                default:
                    ProjectView.showOperationOutcome(false); break;
            }

        } else {
            ProjectView.showOperationOutcome(false);
        }

    }

    public void deleteProject() {
        Project deletedProject = (Project) ProjectView.showRemoveProjectForm();
        if (deletedProject != null) {
            deletedProject.setName(null);
            deletedProject.setVisible(false);
            Project.removeProject(deletedProject);
        }

    }

    public void toggleVisibility(Project project, boolean isVisible) {
        project.setVisible(isVisible);
    }

    public void getProjectList() {
        ProjectView.displayProjectList();
    }

    private boolean isValidAttribute(String input) {
        List<String> projectAttributes = Arrays
                .asList("name","flatTypes","neighbourhood",
                        "closeDate","visibility","maxOfficers");
        return projectAttributes.stream().anyMatch(item -> item.equalsIgnoreCase(input));

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
