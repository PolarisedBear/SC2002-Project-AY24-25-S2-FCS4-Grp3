import enums.FlatType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project {
    private String name;
    private String neighbourhood;
    private Date openDate;
    private Date closeDate;
    private boolean isVisible;
    private int maxOfficers;
    private String createdBy;
    private Map<FlatType, Integer> unitsAvailable;
    private List<Application> applications;
    private List<Enquiry> enquiries;
    private List<HDBOfficer> hdbOfficers;

    public Project() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getOpenDate() {
        return openDate;
    }
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
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
}
