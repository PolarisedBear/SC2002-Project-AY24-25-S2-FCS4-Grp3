package sg.com.ntu.group3.roles;

import java.util.ArrayList;
import java.util.List;

import enums.ApplicationStatus;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.views.ApplicationView;
import sg.com.ntu.group3.views.EnquiryView;

/** Applicant is a subclass of User, but with added attributes specific to it to help facilitate the applicant role capabilities.
 * <p>The applicant class represents the data stored for each applicant type user.
 * The methods included check and modify the internal attributes of the applicant's profile</p>
 *
 */
public class Applicant extends User {
    private Application application;
    private List<Enquiry> enquiries = new ArrayList<>();
    private FlatType FlatTypeBooked;
    private Project ProjectBooked;



    /** Creating a new applicant user object calls the same constructor as the user superclass
     * @param name name of the user
     * @param nric user's NRIC
     * @param age user's age
     * @param maritalStatus user's marital status
     * @param password user's password
     */
    public Applicant(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }


    /** Method to create a new withdrawal request attached to the current application
     *
     */
    public void RequestWithdrawal(){
        application.createWithdrawalRequest();
    }

    /** Add a new enquiry attached to this applicant. Used during the enquiry submission process.
     * @param enquiry The new enquiry to be added
     */
    public void addEnquiry(Enquiry enquiry){
        enquiries.add(enquiry);
    }

    /** Remove an enquiry from the list of enquiries attached to this applicant. Used during enquiry editing and deletion process
     * @param enquiry The enquiry to be removed
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    /** Method to retrieve an application from this applicant. Used by higher controllers for data retrieval.
     * @return the current application attached to this applicant
     */
    public Application getApplication(){
        return this.application;
    }

    /** Method to set the flat type booked for this applicant.
     * Used during successful flat selection
     * @param flatTypeBooked The flat type booked for this applicant
     */
    public void setFlatTypeBooked(FlatType flatTypeBooked) {
        FlatTypeBooked = flatTypeBooked;
    }

    /** Method to set the project booked for this applicant.
     * Used during successful flat selection
     * @param projectBooked The project to with a successful booking
     */
    public void setProjectBooked(Project projectBooked) {
        ProjectBooked = projectBooked;
    }

    /** Method to set the applicant's current application. Used during application
     * @param application The application to be attached to this applicant
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /** Method to return the current flat type booked for this applicant. Used when displaying applicant's profile
     * @return The flat type booked
     */
    public FlatType getFlatTypeBooked() {
        return FlatTypeBooked;
    }

    /** Method to return the current project with a successful booking for this applicant. Used when displaying applicant's profile.
     * @return The project booked
     */
    public Project getProjectBooked() {
        return ProjectBooked;
    }

    /** Method to retrieve the list of enquiries attached to this applicant. Used during viewing of applicant's enquiries
     * @return a list of enquiries created by this applicant
     */
    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    /** Method to find an enquiry belonging to this applicant by its id.
     * @param id the id to search for
     * @return the matching enquiry, or null if no such enquiry was found.
     */
    public Enquiry findEnquiry(int id) {
        return enquiries.stream().filter(enquiry -> enquiry.getId()==id).findFirst().orElse(null);
    }

    /** Method to check if this applicant has made any enquiries
     * @return true if they have made an enquiry, false if otherwise
     */
    public boolean hasEnquiries() {
        return !enquiries.isEmpty();
    }

    /** Method to check if the applicant has cleared the conditions to request a flat booking. Used in flat booking requests
     * @return true if the applicant is able to request a booking, and false if they cannot.
     */
    public boolean canBookFlat() {
        boolean canBook = false;
        if (application==null) {return false;}
        canBook = FlatTypeBooked==null
                && application.getProject().hasAvailableUnitsForApplicant(this)
                && !(application.getStatus()==ApplicationStatus.Booked || application.getStatus()==ApplicationStatus.Booking)
                && application.getStatus()==ApplicationStatus.Successful;
        return canBook;
    }

    /** Method to check if the applicant is able to apply for a new project. Used during project application.
     * @return true if the applicant is able to apply, and false if they cannot.
     */
    public boolean canApplyForProject() {
        if (application==null) {return true;}
        if (application.getStatus()==ApplicationStatus.Unsuccessful
                || getApplication().getStatus()==ApplicationStatus.Withdrawn) {return true;}
        return false;
    }

}