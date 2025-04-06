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
        boolean eligibleFor2R = applicant.getAge()>=35 && applicant.getMaritalStatus().equals("Single");
        boolean eligibleFor2R3R = applicant.getAge()>=21 && applicant.getMaritalStatus().equals("Married");
        return switch (flatType) {
            case TWO_ROOM -> eligibleFor2R || eligibleFor2R3R;
            case THREE_ROOM -> eligibleFor2R3R;
            default -> false;
        };
    }

    public Booking bookFlat(FlatType flatType) {
        Booking newBooking = new Booking();


    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    public FlatType getFlatType(){
        return appliedFlatType;
    }
    public String getProject(){
        return projectName;
    }
    public ApplicationStatus getStatus(){
        return status;
    }

}
