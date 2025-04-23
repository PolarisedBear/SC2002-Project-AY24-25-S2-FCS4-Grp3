package sg.com.ntu.group3.models;

import enums.ApplicationStatus;
import enums.WithdrawalStatus;
import sg.com.ntu.group3.roles.Applicant;

import java.util.*;

/** Application class representing the data held by each Application object created by an applicant.
 * <p>Contains methods for data retrieval and modification, used by higher controller objects.
 * Includes a static applicationMap, that acts as a master list of all currently active applications.</p>
 *
 */
public class Application {
    private Applicant applicant;
    private ApplicationStatus status;
    private WithdrawalRequest withdrawalRequest;
    private Project project;
    private FlatType bookedFlat;
    private static Map<Applicant, Application> applicationMap = new HashMap<>();


    /** Application constructor that instantiates an application. Calling this also updates the static applicationMap.
     * @param applicant The applicant making the application
     * @param project The project being applied to. Also gets updated to include the newly made application
     */
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

    /** Static method to retrieve the map of all applications
     * @return The static attribute applicationMap
     */
    public static Map<Applicant, Application> getAllApplications() {return applicationMap;}

    /** Method to retrieve a map of eligible flats to the number of available units in the project that this application is made.
     * Used during flat selection when the presiding officer has to choose a flat to book.
     * @return A map of eligible flat types to the number of available units.
     */
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

    /** Method to update the flat booking of this application. Used during flat selection
     * @param flat The flat type to be booked.
     */
    public void setBookedFlat(FlatType flat) {
        this.bookedFlat = flat;
    }
    public FlatType getBookedFlat() {
        return this.bookedFlat;
    }

    /** Method to approve a successful flat booking. Used by officers during flat selection.
     * @param flat The flat type to be booked.
     */
    public void approveBooking(FlatType flat) {
        setStatus(ApplicationStatus.Booked);
        setBookedFlat(flat);
        applicant.setFlatTypeBooked(flat);
        applicant.setProjectBooked(project);
        this.project.updateAvailableUnits(bookedFlat, 1, '-');
    }

    /** Method for applicants to create a new withdrawal request attached to this application
     *
     */
    public void createWithdrawalRequest() {
        this.withdrawalRequest = new WithdrawalRequest(this, new Date(), WithdrawalStatus.PENDING);
        setStatus(ApplicationStatus.RequestWithdrawal);
    }

    public WithdrawalRequest getWithdrawalRequest() {
        return this.withdrawalRequest;
    }

    /** Override toString method for Application class.
     * @return formatting whenever an application is printed to the console.
     */
    @Override
    public String toString() {
        return "Application Details{" +
                "\nApplicant: " + applicant +
                "\nStatus: " + status +
                "\nProject: " + project +
                '}';
    }
}
