package sg.com.ntu.group3.roles;


import sg.com.ntu.group3.models.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** HDBManager class is a subclass of user, with added attributes to facilitate its unique capabilities.
 * <p>These include the list of created projects, and the current project.
 * The HDB Manager class represents the data stored in the </p>
 */
public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private Project currentProject;

    //constructors
    public HDBManager(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    //getters and setters

    public List<Project> getCreatedProjects() {return createdProjects;}

    public Project getCurrentProject() {return currentProject;}

    public void setCurrentProject(Project project) {this.currentProject = project;}

    public boolean hasActiveProject() {return currentProject!=null;}


    public void createProject(Project project) {
        project.setCreatedBy(this.getName());
        this.createdProjects.add(project);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
