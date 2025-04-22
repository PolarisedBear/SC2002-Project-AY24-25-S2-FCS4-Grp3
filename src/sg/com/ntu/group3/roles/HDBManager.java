package sg.com.ntu.group3.roles;


import sg.com.ntu.group3.models.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private List<HDBOfficer> Officers = new ArrayList<>();
    private Project currentProject;

    //constructors
    public HDBManager() {
        super();
    }
    public HDBManager(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    //getters and setters

    public List<Project> getCreatedProjects() {return createdProjects;}

    public void setCreatedProjects(List<Project> createdProjects) {this.createdProjects = createdProjects;}

    public List<HDBOfficer> getOfficers() {return Officers;}

    public void setOfficers(List<HDBOfficer> officers) {Officers = officers;}

    public Project getCurrentProject() {return currentProject;}
    public void setCurrentProject(Project project) {this.currentProject = project;}
    public boolean hasActiveProject() {return currentProject!=null;}


    public void createProject(Project project) throws ParseException {
        project.setCreatedBy(this.getName());
        this.createdProjects.add(project);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
