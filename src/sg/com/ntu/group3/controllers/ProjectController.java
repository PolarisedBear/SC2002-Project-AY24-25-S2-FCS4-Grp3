package sg.com.ntu.group3.controllers;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.controllers.services.IProjectService;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.views.ProjectView;
import sg.com.ntu.group3.views.View;

/** Project Controller Class handling operations on projects
 * <p>Operations include: Filtering, Creating, Editing, Deleting</p>
 */
public class ProjectController implements IProjectService{

    private AuthenticationService authenticationService;

    public ProjectController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /** Main method for HDB Managers to create, edit or delete projects.
     * Redirects to different methods responsible for the tasks as indicated by the manager user.
     * @param manager The HDB Manager making the request.
     */
    public void createEditOrDeleteProject(HDBManager manager) {
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

    /** Method for creating a new project.
     * Includes querying the user for necessary input fields and automatic IC assignment of the newly created project.
     * @param manager HDB Manager responsible for creating the project. Also used for assignment as the project's in charge.
     */
    public void createProject(HDBManager manager) {
        List<Object> proj;
        try {proj = ProjectView.showCreateProjectForm();}
        catch (ParseException e) {
            View.showOperationOutcome("Project Creation", false);
            System.out.println("Invalid input!");
            return;
        }

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

    /** Method used to update the manager in charge of a given project and the project's manager in charge field.
     * @param manager The new Manager to be assigned as the manager in charge
     * @param project The project to be updated.
     * @return
     */
    public boolean setInChargeOf(HDBManager manager, Project project) {
        if(manager.getCurrentProject()!=null) {
            if (manager.getCurrentProject().isWithinApplicationPeriod(new Date())) {
                return false;
            } else {
                // update previous manager in charge
                HDBManager prevManager = project.getManagerInCharge();
                Project prevProject = manager.getCurrentProject();
                if (prevProject!=null) {prevProject.setManagerInCharge(null);}
                if (prevManager!=null) {prevManager.setCurrentProject(null);}
                // update current manager in charge
                manager.setCurrentProject(project);
                project.setManagerInCharge(manager);
                return true;
            }
        } else {
            // update previous manager in charge
            HDBManager prevManager = project.getManagerInCharge();
            Project prevProject = manager.getCurrentProject();
            if (prevProject!=null) {prevProject.setManagerInCharge(null);}
            if (prevManager!=null) {prevManager.setCurrentProject(null);}
            // update current manager in charge
            manager.setCurrentProject(project);
            project.setManagerInCharge(manager);
            return true;
        }
    }

    /** Method for editing a project's attributes. Includes querying the user for the field to be updated and the new value.
     * @param project Project to be edited.
     */
    public void editProject(Project project) {
        String attributeToEdit = ProjectView.showEditProjectForm().toLowerCase(Locale.ROOT);
        try {
            if (isValidAttribute(attributeToEdit)) {
                switch (attributeToEdit) {
                    case "name":
                        String newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                        project.setName(newAttribute);
                        break;
                    case "flattypes":
                        int addOrRemove = ProjectView.showEditProjectFlatTypes(project);
                        String name;
                        boolean successful;
                        switch (addOrRemove) {
                            case 0:
                                name = ProjectView.showRemoveProjectFlatTypes();
                                successful = project.removeFlatType(name);
                                ProjectView.showOperationOutcome(successful);
                                break;
                            case 1:
                                name = ProjectView.showAddProjectFlatTypes();
                                Integer number = ProjectView.AddFlatNumberForm();
                                successful = project.addFlatType(name, number);
                                ProjectView.showOperationOutcome(successful);
                                break;
                            default:
                                System.out.println("Operation cancelled");
                        }
                    case "neighbourhood":
                        newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                        project.setNeighbourhood(newAttribute);
                        break;
                    case "closedate":
                        Date newCloseDate = ProjectView.showEditProjectCloseDate();
                        project.setCloseDate(newCloseDate);
                        break;
                    case "visibility":
                        project.setVisible(!project.isVisible());
                        ProjectView.showOperationOutcome(true);
                        break;
                    case "maxofficers":
                        newAttribute = ProjectView.showEditProjectForm(attributeToEdit);
                        project.setMaxOfficers(Integer.parseInt(newAttribute));
                        break;
                    case "incharge":
                        String newManagerNric = ProjectView.showEditManagerInCharge();
                        if (authenticationService.findUserByNric(newManagerNric) instanceof HDBManager) {
                            boolean isInCharge = setInChargeOf((HDBManager) authenticationService.findUserByNric(newManagerNric), project);
                            ProjectView.showOperationOutcome(isInCharge);
                        } else {
                            ProjectView.showOperationOutcome(false);
                        }
                        break;
                    default:
                        ProjectView.showOperationOutcome(false);
                        break;
                }

            } else {
                ProjectView.showOperationOutcome(false);
            }
        } catch (ParseException e) {
            ProjectView.showOperationOutcome(false);
            System.out.println("Invalid input!");
        } catch (IOException e) {
            ProjectView.showOperationOutcome(false);
            System.out.println("User doesn't exist");
        }
        View.lineSeparator();
    }

    /** Method to delete a project. Includes querying the user for the project they wish to delete.
     *
     */
    public void deleteProject() {
        Project deletedProject = ProjectView.showRemoveProjectForm();
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

    /** Method to check if the user enters a valid attribute of the project. Used in tandem with editProject.
     * @param input The String input to be checked
     * @return true if the input is a valid attribute, false if otherwise.
     */
    private boolean isValidAttribute(String input) {
        List<String> projectAttributes = Arrays
                .asList("name","flatTypes","neighbourhood","openDate",
                        "closeDate","visibility","maxOfficers","incharge");
        return projectAttributes.stream().anyMatch(item -> item.equalsIgnoreCase(input));

    }


    /** Implemented from IProjectService, this method searches a HDB Manager user and returns all the projects created by them.
     * @param manager Manager to filter by
     * @return A list of all projects created by the specified manager.
     */
    @Override
    public List<Project> findProjectsByManager(HDBManager manager) {
        return manager.getCreatedProjects();
    }

    /** Implemented from IProjectService, this method checks if a given HDB Manager has an active project within a given date.
     * @param manager Manager whose projects are to be checked
     * @param date The date to be checked
     * @return True if the date falls within the opening and closing date of at least one of the manager's created projects. False if no such projects were found.
     */
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


    /** Method for retrieving all projects.
     * @return A list of all projects that are not deleted.
     */
    public List<Project> getAllProjects() {
        return Project.getProjectList();
    }


    /** Method for HDB Managers to choose to view all projects, or to view only those created by themselves.
     * @param manager Manager user to query
     */
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
