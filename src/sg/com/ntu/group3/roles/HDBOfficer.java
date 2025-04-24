package sg.com.ntu.group3.roles;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;

import java.util.ArrayList;
import java.util.List;

/** HDB Officer class is a subclass of Applicant and has the same capabilities, but with additional officer-specific methods and attributes.
 * <p>This includes an assigned project, and methods handling registration.</p>
 */
public class HDBOfficer extends Applicant{
    private Project assignedProject;
    private List<Registration> registrations = new ArrayList<>();


    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }


    /** Assigns a project to this officer after the registration is successful.
     * @param project The project to be assigned
     */
    public void assignProject(Project project){
        this.assignedProject = project;
    }

    /** Method to retrieve the list of active registrations. Used during the registration process.
     * @return the list of registrations made by this officer
     */
    public List<Registration> getRegistrations() {
        return registrations;
    }

    /** Method to retrieve the assigned project. Used for flat selection and enquiry reply.
     * @return the project assigned to this officer
     */
    public Project getAssignedProject() {
        return assignedProject;
    }

    /** Method to add a newly made registration to the list of registrations. Used during a successful registration.
     * @param registration The registration to be added.
     */
    public void register(Registration registration) {registrations.add(registration);}

    /** Specialised method to check if the officer is able to register for a given project.
     * @param project The project to register for.
     * @return true if the officer can register for the project, false if they cannot.
     */
    public boolean canRegisterForProject(Project project) {
        try {
            boolean hasRegisteredForProject = registrations.stream()
                    .anyMatch(reg -> reg.getProject().equals(project));
            if (super.getApplication() == null && !hasRegisteredForProject) {
                return true;
            } else {
                return !project.toString().equalsIgnoreCase(super.getApplication().getProject().toString());
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    /** Method to check if the officer can apply for a given project. Used during project application.
     * @param project The project to be applied to
     * @return true if the officer is able to apply to that project, false if they cannot.
     */
    public boolean canApplyForProject(Project project) {
        if(!super.canApplyForProject()) {return false;}

        boolean hasRegisteredForProject = registrations.stream()
        .anyMatch(reg -> reg.getProject().equals(project));

        /*check if officer has a registration for the project */
        if (assignedProject == project || hasRegisteredForProject) {
            System.out.println("officer cannot apply to his assigned project.");
            return false;
        }

        return true;
    }


}
