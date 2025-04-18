package sg.com.ntu.group3.models;

import enums.RegistrationStatus;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Registration {
    private RegistrationStatus status;
    private Date requestDate;
    private Project project;
    private HDBOfficer officer;
    private static List<Registration> registrationList = new ArrayList<>();

    public Registration(Project project, HDBOfficer officer) {
        this.project = project;
        this.officer = officer;
        this.requestDate = new Date();
        this.status = RegistrationStatus.Pending;
        registrationList.add(this);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void approve() {
        this.status = RegistrationStatus.Approved;
    }

    public void reject() {
        this.status = RegistrationStatus.Rejection;
    }
    public static List<Registration> getRegistrations() {
        return registrationList;
    }
    public HDBOfficer getOfficer(){
        return officer;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "Status: " + status +
                "\nRequest Date: " + requestDate +
                "\nProject: " + project +
                "\nOfficer: " + officer +
                '}';
    }
}
