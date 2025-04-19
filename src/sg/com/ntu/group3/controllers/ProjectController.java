package sg.com.ntu.group3.controllers;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.controllers.services.IProjectService;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.views.ProjectView;
import sg.com.ntu.group3.views.View;

public class ProjectController extends ProjectView implements IProjectService{

    private AuthenticationService authenticationService;

    public ProjectController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void createEditOrDeleteProject(HDBManager manager) throws ParseException, IOException {
        String choice = ProjectView.showCreateEditOrDeleteForm();
        switch (choice) {
            case "1":
                createProject(manager);
                break;
            case "2":
                ProjectView.displayProjectList();
                String name = ProjectView.queryProjectName();
                Project project = Project.findProject(name);
                editProject(project);
                break;
            case "3":
                deleteProject();
                break;
            default:
                System.out.println("Cancelled");
                break;

        }
    }

    public void createProject(HDBManager manager) throws ParseException {
        List<Object> proj = ProjectView.showCreateProjectForm();
        Project newProject = new Project((String) proj.get(0),
                (List<FlatType>) proj.get(1),
                (String) proj.get(2),
                (Date) proj.get(3),
                (Date) proj.get(4),
                (Boolean) proj.get(5),
                (Integer) proj.get(6),
                (Map<FlatType, Integer>) proj.get(7));
        manager.createProject(newProject);
        View.showOperationOutcome("Project Creation", true);
        View.lineSeparator();
        boolean isInCharge = setInChargeOf(manager, newProject);
        if (!isInCharge) {
            View.showOperationOutcome("Automatic IC assignment", false);
            System.out.println("You cannot be in charge of another project right now.");
        } else {
            View.showOperationOutcome("Automatic IC assignment", true);
            System.out.println("You are now in charge of this project.");
        }
    }

    public boolean setInChargeOf(HDBManager manager, Project project) {
        if(manager.getCurrentProject()!=null) {
            if (manager.getCurrentProject().isWithinApplicationPeriod(new Date())) {
                return false;
            } else {
                // update previous manager in charge
                HDBManager prevManager = project.getManagerInCharge();
                Project prevProject = manager.getCurrentProject();
                if (prevProject!=null) {prevProject.setManagerInCharge(new HDBManager());}
                if (prevManager!=null) {prevManager.setCurrentProject(new Project());}
                // update current manager in charge
                manager.setCurrentProject(project);
                project.setManagerInCharge(manager);
                return true;
            }
        } else {
            // update previous manager in charge
            HDBManager prevManager = project.getManagerInCharge();
            Project prevProject = manager.getCurrentProject();
            if (prevProject!=null) {prevProject.setManagerInCharge(new HDBManager());}
            if (prevManager!=null) {prevManager.setCurrentProject(new Project());}
            // update current manager in charge
            manager.setCurrentProject(project);
            project.setManagerInCharge(manager);
            return true;
        }
    }

    public void editProject(Project project) throws ParseException, IOException {
        String attributeToEdit = ProjectView.showEditProjectForm().toLowerCase(Locale.ROOT);
        if (isValidAttribute(attributeToEdit)) {
            switch (attributeToEdit) {
                case "name":
                    String newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                    project.setName(newAttribute); break;
                case "flattypes":
                    int addOrRemove = ProjectView.showEditProjectFlatTypes(project);
                    String name;
                    boolean successful;
                    switch (addOrRemove) {
                        case 0:
                            name = ProjectView.showRemoveProjectFlatTypes(project);
                            successful = project.removeFlatType(name);
                            ProjectView.showOperationOutcome(successful); break;
                        case 1:
                            name = ProjectView.showAddProjectFlatTypes(project);
                            Integer number = ProjectView.AddFlatNumberForm();
                            successful = project.addFlatType(name, number);
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
                case "incharge":
                    String newManagerNric = ProjectView.showEditManagerInCharge();
                    if (authenticationService.findUserByNric(newManagerNric) instanceof HDBManager) {
                        boolean isInCharge = setInChargeOf((HDBManager) authenticationService.findUserByNric(newManagerNric), project);
                        ProjectView.showOperationOutcome(isInCharge);
                    } else {ProjectView.showOperationOutcome(false);} break;
                default:
                    ProjectView.showOperationOutcome(false); break;
            }

        } else {
            ProjectView.showOperationOutcome(false);
        }
        View.lineSeparator();
    }

    public void deleteProject() {
        Project deletedProject = (Project) ProjectView.showRemoveProjectForm();
        if (deletedProject != null) {
            deletedProject.setName(null);
            deletedProject.setVisible(false);
            Project.removeProject(deletedProject);
            View.showOperationOutcome("Deletion", true);
        } else {
            View.showOperationOutcome("Deletion", false);
            System.out.println("Project does not exist");
        }

    }

    public void toggleVisibility(Project project, boolean isVisible) {
        project.setVisible(!project.isVisible());
    }

    public List<Project> getProjectList() {
        ProjectView.displayProjectList();
        return null;
    }

    private boolean isValidAttribute(String input) {
        List<String> projectAttributes = Arrays
                .asList("name","flatTypes","neighbourhood","openDate",
                        "closeDate","visibility","maxOfficers","incharge");
        return projectAttributes.stream().anyMatch(item -> item.equalsIgnoreCase(input));

    }


    @Override
    public List<Project> findProjectsByManager(HDBManager manager) {
        return manager.getCreatedProjects();
    }

    @Override
    public boolean hasActiveProject(HDBManager manager, Date date) {
        boolean hasActive = false;
        for (Project proj : manager.getCreatedProjects()) {
            if ((date.compareTo(proj.getOpeningDate())>=0) && (date.compareTo(proj.getCloseDate())<=0)) {
                hasActive = true;
                break;
            }
        }
        return hasActive;
    }


    public boolean hasActiveProject(HDBManager manager, Date date, ApplicationController controller) {
        return false;
    }
    public void displayEligibleProjects(Applicant applicant, ApplicationController controller) {
        List<Project> eligibleProjects = controller.findEligibleProjects(applicant);
        System.out.println("Eligible projects:\n");
        for (Project project : eligibleProjects) {
            System.out.println(project.getName());
        }
    }
    public List<Project> getAllProjects() {
        return Project.getProjectList();
    }


    public void viewProjects(HDBManager manager) {
        int choice = ProjectView.chooseProjectViewScope();
        if (choice == 1) {
            List<Project> managerProjects = findProjectsByManager(manager);
            ProjectView.displayProjectList(managerProjects);
        } else if (choice == 2) {
            List<Project> allProjects = getAllProjects();
            ProjectView.displayProjectList(allProjects);
        } else {
            System.out.println("Cancelled");
        }
    }
}
