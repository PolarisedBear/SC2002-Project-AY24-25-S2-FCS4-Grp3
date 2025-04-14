package sg.com.ntu.group3.models;

import enums.ApplicationStatus;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project {
    private String name;
    private List<FlatType> flatTypes;
    private String neighbourhood;
    private Date openingDate;
    private Date closeDate;
    private boolean isVisible;
    private int maxOfficers;
    private int officerSlots;
    private String createdBy;
    private Map<FlatType, Integer> unitsAvailable; //units Available for that particular flat type in this project
    private List<Application> applications = new ArrayList<>();
    private List<Enquiry> enquiries = new ArrayList<>();
    private List<HDBOfficer> hdbOfficers = new ArrayList<>();
    private List<Applicant> applicants = new ArrayList<>(); //added
    private static List<Project> projectList = new ArrayList<>(); //master list of all created projects

    public Project() {
        this.officerSlots = 0;
        this.openingDate = new Date();
        projectList.add(this);
    }

    public Project(String name,
                   List<FlatType> flatTypes,
                   String neighbourhood,
                   Date closeDate,
                   boolean isVisible,
                   int maxOfficers,
                   Map<FlatType, Integer> unitsAvailable) {
        this.name = name;
        this.flatTypes = flatTypes;
        this.neighbourhood = neighbourhood;
        this.openingDate = new Date();
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

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                //", projectId='" + projectId + '\'' +
                "\nFlatTypes=" + flatTypes +
                "\nNeighbourhood='" + neighbourhood + '\'' +
                "\nOpening Date=" + openingDate +
                "\nCloseDate=" + closeDate +
                "\nVisibility=" + isVisible +
                "\nmaxOfficers=" + maxOfficers +
                "\nOfficer Slots=" + officerSlots +
                "\nCreator='" + createdBy + '\'' +
                "\nAvailableUnits=" + unitsAvailable +
                '}';
    }



    public boolean isEligibleForApplication(Applicant applicant) {
        boolean isEligible = false;
        for (FlatType flatType : this.flatTypes) {
            if (flatType.isEligibleForApplicant(applicant)) {isEligible = true; break;}
        }
        return isEligible;
    }

    public boolean isEligibleFlatAvailable(Applicant applicant, FlatType eligibleFlat) {
        return unitsAvailable.get(eligibleFlat)!=0;
    }

    public boolean hasAvailableUnitsForApplicant(Applicant applicant) {
        for (FlatType flatType : this.flatTypes) {
            if (flatType.isEligibleForApplicant(applicant)) {
                return this.unitsAvailable.get(flatType) != 0;
            }
        }
        return false;
    }

    public void updateAvailableUnits(FlatType flatType, int change, char operator) {
        if (operator == '+') {
            this.unitsAvailable.compute(flatType, (k, current) -> current + change);
        }
        else if (operator == '-') {
            this.unitsAvailable.compute(flatType, (k, current) -> current - change);
        }
        flatType.updateRemainingUnits(change, operator);
    }

    public List<Application> searchApplicationByStatus(ApplicationStatus applicationStatus) {
        return applications.stream().filter(application -> application.getStatus()==applicationStatus).toList();
    }

    public boolean isWithinApplicationPeriod(Date date) {
        return !date.before(this.openingDate) && !date.after(this.closeDate);
    }

    public boolean checkForConflictOfInterest(User user) {
        // don't quite understand what this means
        return false;
    }

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

    public boolean addFlatType(String name, Integer number) {
        boolean exists = FlatType.getTypeList().containsKey(name);
        if (exists) {
            this.flatTypes.add(FlatType.getTypeList().get(name));
            this.unitsAvailable.put(FlatType.getTypeList().get(name), number);
        }
        return exists;
    }

    public boolean checkForFlatType(String name) {
        return this.flatTypes.stream().anyMatch(flatType -> flatType.getType().equalsIgnoreCase(name));
    }

    public static Project findProject(String name) {
        return projectList.stream()
                .filter(proj -> proj.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public static boolean projectExists(String name) {
        return projectList.stream()
                .anyMatch(proj -> proj.getName().equalsIgnoreCase(name));
    }

    public static boolean projectExists(String name, List<Project> projectList) {
        return projectList.stream()
                .anyMatch(proj -> proj.getName().equalsIgnoreCase(name));
    }

    public static void removeProject(Project project) {
        projectList.remove(project);
    }
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
