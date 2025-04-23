package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Enquiry class representing the data held in an enquiry object made by officers and applicants
 * <p>Contains methods that retrieve or update the information within the class.
 * Includes a static enquiryMap that stores all enquiries made, and a static latestId that assigns a unique number to each enquiry.</p>
 */
public class Enquiry {
    private String content;
    private String response;
    private Project proj;
    private int enquiryId;
    private User user;
    private static Map<Project, List<Enquiry>> enquiryMap = new HashMap<>();
    private static int latestId = 1;

    /** Enquiry constructor that automatically updates the relevant project and applicant
     * @param proj The project that is being enquired
     * @param content The String content of this enquiry
     * @param applicant The applicant making the enquiry
     */
    public Enquiry(Project proj, String content, Applicant applicant) {
        this.content = content;
        this.user = applicant;
        this.proj = proj;
        this.enquiryId = latestId;
        latestId+=1;
        proj.addEnquiry(this);
        applicant.addEnquiry(this);
    }

    /** Method to update the content of the enquiry
     * @param content The new String content
     */
    public void editEnquiry(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public Project getProj() {
        return proj;
    }
    public User getUser() {
        return user;
    }

    /** Method to retrieve the specific ID of this enquiry, not the static latestId variable.
     * @return an integer ID of just this one enquiry
     */
    public int getId() {return enquiryId;}

    /** Method to update the reply to this enquiry. Used by officers and managers
     * @param response The String response to reply with.
     */
    public void reply(String response) {
        this.response = response;
    }

    /** Method to delete the current enquiry.
     * This method detaches the current enquiry from the relevant applicant and project, and erases its data.
     *
     */
    public void deleteEnquiry() {
        //remove from project and applicant
        this.proj.removeEnquiry(this);
        Applicant applicant = (Applicant) this.user;
        applicant.removeEnquiry(this);
        this.content = null;
        this.response = null;
        this.proj = null;
    }


    /** Retrieve the static map of all current enquiries
     * @return The map of projects to the list of enquiries made to that project
     */
    public static Map<Project, List<Enquiry>> getEnquiryMap() {
        return enquiryMap;
    }

    /** Override toString method for enquiry
     * @return formatting for enquiries whenever they are printed to the console.
     */
    @Override
    public String toString() {
        return "Enquiry{" +
                "\nContent: " + content +
                "\nResponse: " + response +
                "\nProject: " + proj.getName() +
                "\nEnquiryId: " + enquiryId +
                '}';
    }
}
