package sg.com.ntu.group3.models;

import enums.RegistrationStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Registration {
    private RegistrationStatus status;
    private Date requestDate;
    private Project project;
    private static List<Registration> registrationList = new ArrayList<>();

    public Registration(Project project) {
        this.project = project;
        this.requestDate = new Date();
        this.status = RegistrationStatus.Pending;
        registrationList.add(this);
    }

    public void approve() {
        this.status = RegistrationStatus.Approved;
    }

    public void reject() {
        this.status = RegistrationStatus.Rejection;
    }

}
