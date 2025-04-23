package sg.com.ntu.group3.roles;


import sg.com.ntu.group3.models.*;

import java.util.ArrayList;
import java.util.List;

/** HDBManager class is a subclass of user, with added attributes to facilitate its unique capabilities.
 * <p>These include the list of created projects, and the current project.
 * The HDB Manager class represents the data stored for each manager type user.
 * Methods included handle internal modification and retrieval of data for use by higher level controllers.</p>
 */
public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private Project currentProject;


    /** Constructor, same as the superclass user. Manager-specific attributes are added later during manager operations.
     * @param name User's name
     * @param nric User's NRIC
     * @param age User's age
     * @param maritalStatus User's marital status
     * @param password User's password
     */
    public HDBManager(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }


    /** Method to retrieve all projects created by this manager. Used in report generation and filtering
     * @return a list of projects whose createdBy attribute point to this manager
     */
    public List<Project> getCreatedProjects() {return createdProjects;}

    /** Method to retrieve the project that this manager is currently handling. Used for most manager operations, and project creation and editing.
     * @return the project currently being handled by this manager
     */
    public Project getCurrentProject() {return currentProject;}

    /** Method to set the current project handled by this manager. Used in project creation and editing.
     * @param project The project to be assigned
     */
    public void setCurrentProject(Project project) {this.currentProject = project;}

    /** Method to check if this manager has an active project.
     * @return true if the currentProject field is not empty, false if no project was found.
     */
    public boolean hasActiveProject() {return currentProject!=null;}


    /** Method to add a newly created project to the list of projects created by this manager.
     * At the same time, updates the project to reflect who it was created by.
     * @param project The newly created project
     */
    public void createProject(Project project) {
        project.setCreatedBy(this.getName());
        this.createdProjects.add(project);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
