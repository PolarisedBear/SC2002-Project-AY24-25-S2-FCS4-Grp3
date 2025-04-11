package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project {
    private String name;
    private String projectId;
    private List<FlatType> flatTypes;
    private String neighbourhood;
    private Date openingDate;
    private Date closeDate;
    private boolean isVisible;
    private int maxOfficers;
    private int officerSlots;
    private String createdBy;
    private Map<FlatType, Integer> unitsAvailable;
    private List<Application> applications;
    private List<Enquiry> enquiries;
    private List<HDBOfficer> hdbOfficers;
    private List<Applicant> applicants; //added

    public Project() {
        ProjectRegistry.addProject(this);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
    public boolean isVisible() {
        return isVisible;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    public String getNeighbourhood() {
        return neighbourhood;
    }
    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }
    public Date getCloseDate() {
        return closeDate;
    }
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    public int getMaxOfficers() {
        return maxOfficers;
    }
    public void setMaxOfficers(int maxOfficers) {
        this.maxOfficers = maxOfficers;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Map<FlatType, Integer> getUnitsAvailable() {
        return unitsAvailable;
    }
    public void setUnitsAvailable(Map<FlatType, Integer> unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }
    public List<Application> getApplications() {
        return applications;
    }
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
    public List<Enquiry> getEnquiries() {
        return enquiries;
    }
    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }
    public List<HDBOfficer> getHdbOfficers() {
        return hdbOfficers;
    }
    public void setHdbOfficers(List<HDBOfficer> hdbOfficers) {
        this.hdbOfficers = hdbOfficers;
    }

    // Add application to list
    public void addApplication(Application application) {
        this.applications.add(application);
    }

    //
    public List<Applicant> getApplicants(){
        return applicants;
    }

    @Override
    public String toString() {
        return "sg.com.ntu.groupX.models.Project{" +
                "name='" + name + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", openingDate=" + openingDate +
                ", closeDate=" + closeDate +
                ", isVisible=" + isVisible +
                ", maxOfficers=" + maxOfficers +
                ", createdBy='" + createdBy + '\'' +
                ", unitsAvailable=" + unitsAvailable +
                ", applications=" + applications +
                ", enquiries=" + enquiries +
                ", hdbOfficers=" + hdbOfficers +
                '}';
    }

    public boolean isEligibleForApplication(Applicant applicant) {
        boolean isEligible = false;
        for (FlatType flatType : this.flatTypes) {
            if (flatType.isEligibleForApplicant(applicant)) {isEligible = true; break;}
        }
        return isEligible;
    }

    public boolean hasAvailableUnitsForApplicant(Applicant applicant) {
        for (FlatType flatType : this.flatTypes) {
            if (flatType.isEligibleForApplicant(applicant)) {
                return this.unitsAvailable.get(flatType) != 0;
            }
        }
        return false;
    }

    public boolean isWithinApplicationPeriod(Date date) {
        return !date.before(this.openingDate) && !date.after(this.closeDate);
    }

    public boolean checkForConflictOfInterest(User user) {
        // don't quite understand what this means
        return false;
    }
}
