package sg.com.ntu.group3.models;

import enums.ApplicationStatus;
import sg.com.ntu.group3.roles.Applicant;

import java.util.Map;

public class Application {
    private Applicant applicant;
    private ApplicationStatus status;
    private Project project;
    private static Map<ApplicationStatus, Application> applicationMap;

    public Application(Applicant applicant) {
        this.applicant = applicant;
        this.status = ApplicationStatus.Pending;
        applicationMap.put(this.status, this);
    }

    public Application(Applicant applicant, Project project) {
        this.applicant = applicant;
        this.status = ApplicationStatus.Pending;
        this.project = project;
        project.addApplication(this);
        applicationMap.put(this.status, this);
    }


    public Applicant getApplicant() {
        return applicant;
    }
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Project getProject(){
        return project;
    }
    public ApplicationStatus getStatus(){
        return status;
    }
    public Application getApplication(){
        return this;
    }

}
