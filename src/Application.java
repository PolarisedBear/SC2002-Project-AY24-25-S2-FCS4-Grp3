import enums.ApplicationStatus;
import enums.FlatType;

public class Application {
    private Applicant applicant;
    private ApplicationStatus status;
    private FlatType appliedFlatType;
    private Booking booking;
    private String projectName;

    public Application(Applicant applicant) {
        this.applicant = applicant;
        this.status = ApplicationStatus.Pending;
    }

    public Application(Project project, FlatType flatType, Applicant applicant) {
        this.applicant = applicant;
        this.appliedFlatType = flatType;
        this.status = ApplicationStatus.Pending;
        project.addApplication(this);
    }

    public boolean isEligibleForFlat(Applicant applicant, FlatType flatType) {

    }

    public Booking bookFlat(FlatType flatType) {
        Booking newBooking = new Booking();


    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
