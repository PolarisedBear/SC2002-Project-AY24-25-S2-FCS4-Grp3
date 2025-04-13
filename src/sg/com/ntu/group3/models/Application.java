package sg.com.ntu.group3.models;

import enums.ApplicationStatus;
import sg.com.ntu.group3.roles.Applicant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private Applicant applicant;
    private ApplicationStatus status;
    private Project project;
    private static Map<Applicant, Application> applicationMap;

    public Application(Applicant applicant) {
        this.applicant = applicant;
        this.status = ApplicationStatus.Pending;
        applicationMap.put(this.applicant, this);
    }

    public Application(Applicant applicant, Project project) {
        this.applicant = applicant;
        this.status = ApplicationStatus.Pending;
        this.project = project;
        project.addApplication(this);
        applicationMap.put(this.applicant, this);
    }


    public Applicant getApplicant() {return applicant;}
    public void setStatus(ApplicationStatus status) {this.status = status;}
    public Project getProject(){return project;}
    public ApplicationStatus getStatus(){return status;}
    public Application getApplication(){return this;}
    public static Map<Applicant, Application> getAllApplications() {return applicationMap;}

    public Map<FlatType, Integer> getAvailableUnitsForApplicant() {
        Map<FlatType, Integer> availableUnitsByFlatType = new HashMap<>();
        for (FlatType flatType : this.project.getFlatTypes()) {
            if (flatType.isEligibleForApplicant(this.applicant)) {
                Integer availableUnits = this.project.getUnitsAvailable().get(flatType);
                availableUnitsByFlatType.put(flatType, availableUnits);
            }
        }
        return availableUnitsByFlatType;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicant=" + applicant +
                ", status=" + status +
                ", project=" + project +
                '}';
    }
}
