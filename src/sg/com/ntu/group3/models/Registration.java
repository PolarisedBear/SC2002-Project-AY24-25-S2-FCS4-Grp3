package sg.com.ntu.group3.models;

import enums.RegistrationStatus;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Registration class represents the data held by each registration object.
 * <p>New registrations are created whenever an officer decides to register for a project.
 * Includes methods for internal modification and retrieval of attributes.</p>
 *
 */
public class Registration {
    private RegistrationStatus status;
    private Date requestDate;
    private Project project;
    private HDBOfficer officer;
    private static List<Registration> registrationList = new ArrayList<>();

    /** Registration Constructor. Automatically logs the current date as the date of registration and adds itself to the master list storing all registrations.
     * @param project Project to register for
     * @param officer Officer making the registration
     */
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

    /** Method to approve the registration. Used by managers
     *
     */
    public void approve() {
        this.status = RegistrationStatus.Approved;
    }

    /** Method to reject the registration. Used by managers
     *
     */
    public void reject() {
        this.status = RegistrationStatus.Rejection;
    }
    public static List<Registration> getRegistrations() {
        return registrationList;
    }
    public HDBOfficer getOfficer(){
        return officer;
    }

    /** Static method to search the registration master list for all registrations made to a given project. Used by managers during officer registration.
     * @param project The project to be searched from
     * @return A list of registrations to the project
     */
    public static List<Registration> findRegistrationsByProject(Project project) {
        return registrationList.stream().filter(reg -> reg.getProject().toString().equals(project.toString())).toList();
    }

    /** Override toString method for registrations
     * @return formatting used when registrations are printed to the console.
     */
    @Override
    public String toString() {
        return "Registration{" +
                "\nStatus: " + status +
                "\nRequest Date: " + requestDate +
                "\nProject: " + project +
                "\nOfficer: " + officer +
                '}';
    }
}
