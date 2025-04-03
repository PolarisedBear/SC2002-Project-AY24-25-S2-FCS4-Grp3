import enums.RegistrationStatus;

import java.util.Date;

public class Registration {
    private RegistrationStatus status;
    private Date requestDate;
    private Project project;

    public Registration(Project project) {
        this.project = project;
        this.requestDate = new Date();
        this.status = RegistrationStatus.Pending;
    }


}
