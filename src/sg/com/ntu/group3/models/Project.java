package sg.com.ntu.group3.models;

import enums.ApplicationStatus;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** Project class representing the data held by each project object.
 * <p>Includes methods to modify and return internal values.
 * Also includes lists to track other persistence models attached to the project, such as applications, enquiries, applicants and officers.</p>
 *
 */
public class Project {
    private String name;
    private List<FlatType> flatTypes;
    private String neighbourhood;
    private Date openingDate;
    private Date closeDate;
    private boolean isVisible;
    private int maxOfficers;
    private int officerSlots;
    private String createdBy = "";
    private HDBManager managerInCharge;
    private Map<FlatType, Integer> unitsAvailable; //units Available for that particular flat type in this project
    private List<Application> applications = new ArrayList<>();
    private List<Enquiry> enquiries = new ArrayList<>();
    private List<HDBOfficer> hdbOfficers = new ArrayList<>();
    private List<Applicant> applicants = new ArrayList<>(); //added
    private static List<Project> projectList = new ArrayList<>(); //master list of all created projects


    /** Project constructor to initialise all the relevant fields. Also adds this project to the static master list storing all created projects
     * @param name name of the project
     * @param flatTypes list of flat types available
     * @param neighbourhood the neighbourhood of this project
     * @param openDate the opening date of the project for application
     * @param closeDate the closing date for application
     * @param isVisible visibility to applicants
     * @param maxOfficers maximum number of officers assigned
     * @param unitsAvailable map of flat types to the units available for this project.
     */
    public Project(String name,
                   List<FlatType> flatTypes,
                   String neighbourhood,
                   Date openDate,
                   Date closeDate,
                   boolean isVisible,
                   int maxOfficers,
                   Map<FlatType, Integer> unitsAvailable) {
        this.name = name;
        this.flatTypes = flatTypes;
        this.neighbourhood = neighbourhood;
        this.openingDate = openDate;
        this.closeDate = closeDate;
        this.isVisible = isVisible;
        this.maxOfficers = maxOfficers;
        this.officerSlots = 0;
        this.unitsAvailable = unitsAvailable;
        projectList.add(this);
    }

    // Getters and Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Date getOpeningDate() {return openingDate;}
    public void setOpeningDate(Date openingDate) {this.openingDate = openingDate;}
    public boolean isVisible() {return isVisible;}
    public void setVisible(boolean visible) {isVisible = visible;}
    public String getNeighbourhood() {return neighbourhood;}
    public void setNeighbourhood(String neighbourhood) {this.neighbourhood = neighbourhood;}
    public Date getCloseDate() {return closeDate;}
    public void setCloseDate(Date closeDate) {this.closeDate = closeDate;}
    public int getMaxOfficers() {return maxOfficers;}
    public void setMaxOfficers(int maxOfficers) {this.maxOfficers = maxOfficers;}
    public String getCreatedBy() {return createdBy;}
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}
    public HDBManager getManagerInCharge() {return this.managerInCharge;}
    public void setManagerInCharge(HDBManager manager) {this.managerInCharge = manager;}
    public Map<FlatType, Integer> getUnitsAvailable() {return unitsAvailable;}
    public void setUnitsAvailable(Map<FlatType, Integer> unitsAvailable) {this.unitsAvailable = unitsAvailable;}
    public List<Application> getApplications() {return applications;}
    public void setApplications(List<Application> applications) {this.applications = applications;}
    public List<Enquiry> getEnquiries() {return enquiries;}
    public void setEnquiries(List<Enquiry> enquiries) {this.enquiries = enquiries;}
    public List<HDBOfficer> getHdbOfficers() {return hdbOfficers;}
    public void setHdbOfficers(List<HDBOfficer> hdbOfficers) {this.hdbOfficers = hdbOfficers;}
    public static List<Project> getProjectList() {return projectList;}
    public List<FlatType> getFlatTypes() {return flatTypes;}

    // Add application to list
    public void addApplication(Application application) {
        this.applications.add(application);
    }

    //
    public List<Applicant> getApplicants(){
        return applicants;
    }
    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }
    public void removeApplicant(Applicant applicant) {
        applicants.remove(applicant);
    }

    /** Override toString method for project
     * @return formatiing for projects whenever they are printed to the console
     */
    @Override
    public String toString() {
        return "Project Details{" +
                "\nname: " + name +
                "\nFlatTypes: " + flatTypes +
                "\nNeighbourhood: " + neighbourhood +
                "\nOpening Date: " + openingDate +
                "\nCloseDate: " + closeDate +
                "\nVisibility: " + isVisible +
                "\nmaxOfficers: " + maxOfficers +
                "\nOfficer Slots: " + officerSlots +
                "\nCreator: " + createdBy +
                "\nManager In Charge: " + managerInCharge +
                "\nAvailableUnits: " + unitsAvailable +
                "\n}";
    }


    /** Method to check if an applicant is eligible to apply for this project. Used during application
     * @param applicant The applicant to be checked
     * @return true if the applicant is eligible, false if otherwise
     */
    public boolean isEligibleForApplication(Applicant applicant) {
        boolean isEligible = false;
        for (FlatType flatType : this.flatTypes) {
            if (flatType.isEligibleForApplicant(applicant)) {isEligible = true; break;}
        }
        return isEligible;
    }


    /** Method to check if the project has available units for the applicant to book. Used during requests for booking and flat selection via officer.
     * Used in tandem with isEligibleForApplication
     * @param applicant The applicant to be checked
     * @return true if there are flats available for booking, false otherwise
     */
    public boolean hasAvailableUnitsForApplicant(Applicant applicant) {
        for (FlatType flatType : this.flatTypes) {
            if (flatType.isEligibleForApplicant(applicant)) {
                return this.unitsAvailable.get(flatType) != 0;
            }
        }
        return false;
    }

    /** Method to update the number of available units of a specific flat type in this project. Used after flat selection is successful.
     * @param flatType The flat type to modify
     * @param change the amount of flats to change
     * @param operator the type of change effected
     */
    public void updateAvailableUnits(FlatType flatType, int change, char operator) {
        if (operator == '+') {
            this.unitsAvailable.compute(flatType, (k, current) -> current + change);
        }
        else if (operator == '-') {
            this.unitsAvailable.compute(flatType, (k, current) -> current - change);
        }
        flatType.updateRemainingUnits(change, operator);
    }

    /** Method to filter applications to this project by a given status. Used during application approval or rejection.
     * @param applicationStatus The status to search for
     * @return a filtered list of applications that have the searched status
     */
    public List<Application> searchApplicationByStatus(ApplicationStatus applicationStatus) {
        return applications.stream().filter(application -> application.getStatus()==applicationStatus).toList();
    }

    /** Method to check if a given date falls between the opening and closing dates of this project. Used during application
     * @param date The date to be checked
     * @return true if the checked date falls within the opening and closing dates, false if it exists outside the range.
     */
    public boolean isWithinApplicationPeriod(Date date) {
        return !date.before(this.openingDate) && !date.after(this.closeDate);
    }

    /** Method to check if there are available slots for officers to register for this project. Used during officer registration.
     * @return true if the number of slots taken is less than the maximum number of officers allowed, false otherwise.
     */
    public boolean isAvailableForRegistration() {
        return officerSlots<maxOfficers;
    }

    /** Method to remove a certain flat type from this project. Used by managers when editing a project.
     * @param name The name of the flat type to be removed
     * @return true if the removal was successful, false if otherwise.
     */
    public boolean removeFlatType(String name) {
        boolean exists = this.flatTypes.stream()
                .anyMatch(type -> type.getType().equals(name));
        if (exists) {
            this.flatTypes.remove(this.flatTypes.stream()
                    .filter(type -> type.getType().equalsIgnoreCase(name))
                    .findFirst().orElse(null));
            this.unitsAvailable.remove(FlatType.getTypeList().get(name));
        }
        return exists;
    }

    /** Method to add an existing flat type to this project. Used by managers when editing a project
     * @param name The name of the flat type to be removed
     * @param number The number of available units to assign to this flat type
     * @return true if the addition was successful, false if otherwise
     */
    public boolean addFlatType(String name, Integer number) {
        boolean exists = FlatType.getTypeList().containsKey(name);
        if (exists) {
            this.flatTypes.add(FlatType.getTypeList().get(name));
            this.unitsAvailable.put(FlatType.getTypeList().get(name), number);
        }
        return exists;
    }

    /** Add a new enquiry to this project. Used during enquiry creation
     * @param enquiry The enquiry to be added
     */
    public void addEnquiry(Enquiry enquiry) {
        this.enquiries.add(enquiry);
    }

    /** Remove an enquiry from this project. Used during enquiry deletion.
     * @param enquiry The enquiry to be removed
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    /** Find an enquiry by its id among the list of enquiries belonging to this project. Used during enquiry viewing and response from officers and managers.
     * @param id The id to be searched
     * @return The enquiry that has that id, or null if it doesn't exist
     */
    public Enquiry findEnquiry(int id) {
        return enquiries.stream().filter(enquiry -> enquiry.getId()==id).findFirst().orElse(null);
    }

    /** Check if a given flat type exists in this project
     * @param name The name of the flat type to be searched
     * @return true if the flat type exists, false if otherwise
     */
    public boolean checkForFlatType(String name) {
        return this.flatTypes.stream().anyMatch(flatType -> flatType.getType().equalsIgnoreCase(name));
    }

    /** Static method to search for a project by its name from the master list
     * @param name The name of the project to search
     * @return the Project object matching the searched name, or null if it doesn't exist
     */
    public static Project findProject(String name) {
        return projectList.stream()
                .filter(proj -> proj.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    /** A static method that checks if a project exists within the master list. Similar to findProject, except that this returns a boolean instead.
     * @param name The name of the project to be searched
     * @return true if a matching project was found, false if otherwise
     */
    public static boolean projectExists(String name) {
        return projectList.stream()
                .anyMatch(proj -> proj.getName().equalsIgnoreCase(name));
    }

    /** Overloaded method for projectExists, this method instead searches a given list of Projects instead of the master list.
     * @param name The name of the project to be searched
     * @param projectList The list of projects to search from
     * @return true if a matching project is found, false if otherwise
     */
    public static boolean projectExists(String name, List<Project> projectList) {
        return projectList.stream()
                .anyMatch(proj -> proj.getName().equalsIgnoreCase(name));
    }

    /** Static method for project removal from the master list. Used during project deletion and withdrawal
     * @param project The project to be removed.
     */
    public static void removeProject(Project project) {
        projectList.remove(project);
    }

    /** Assign an officer to this project. Used during officer registration after approval.
     * @param officer The officer to be assigned to this project
     * @return true if slots are available, false if otherwise
     */
    public boolean assignOfficer(HDBOfficer officer) {
        if (officerSlots < maxOfficers) {
            this.hdbOfficers.add(officer);
            officerSlots++;
            return true;
        } else {
            System.out.println("no more slots for officers");
            return false;
        }
    }

}
