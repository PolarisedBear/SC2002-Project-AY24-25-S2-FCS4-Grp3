package sg.com.ntu.group3.models;

import enums.ApplicationStatus;
import enums.WithdrawalStatus;
import sg.com.ntu.group3.roles.Applicant;

import java.util.*;

public class Application {
    private Applicant applicant;
    private ApplicationStatus status;
    private WithdrawalRequest withdrawalRequest;
    private Project project;
    private FlatType bookedFlat;
    private static Map<Applicant, Application> applicationMap = new HashMap<>();

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
        for (FlatType flatType : this.project.getUnitsAvailable().keySet()) {
            if (flatType.isEligibleForApplicant(this.applicant)) {
                Integer availableUnits = this.project.getUnitsAvailable().get(flatType);
                availableUnitsByFlatType.put(flatType, availableUnits);
            }
        }
        return availableUnitsByFlatType;
    }

    public void setBookedFlat(FlatType flat) {
        this.bookedFlat = flat;
    }
    public FlatType getBookedFlat() {
        return this.bookedFlat;
    }

    public void approveBooking() {
        setStatus(ApplicationStatus.Booked);
        this.project.updateAvailableUnits(bookedFlat, 1, '-');
    }

    public void createWithdrawalRequest() {
        this.withdrawalRequest = new WithdrawalRequest(this, new Date(), WithdrawalStatus.PENDING);
        setStatus(ApplicationStatus.RequestWithdrawal);
    }

    public WithdrawalRequest getWithdrawalRequest() {
        return this.withdrawalRequest;
    }

    @Override
    public String toString() {
        return "Application{" +
                "\napplicant=" + applicant +
                "\nstatus=" + status +
                "\nproject=" + project +
                '}';
    }
}
