import Enumerations.ApplicationStatus;
import Enumerations.FlatType;

public class Application {
    private Applicant applicant;
    private ApplicationStatus status;
    private FlatType appliedFlatType;
    private Booking booking;
    private String projectName;

    public Application(Applicant applicant) {
        this.applicant = applicant;
    }

    public boolean isEligibleForFlat(Applicant applicant, FlatType flatType) {

    }

    public Booking bookFlat(FlatType flatType) {
        Booking newBooking = new Booking();


    }
}
