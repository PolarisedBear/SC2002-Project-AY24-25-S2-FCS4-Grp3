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

public class Applicant extends User {
    private Application application;
    private List<Enquiry> enquiries = new ArrayList<>();
    private FlatType FlatTypeBooked;
    private Project ProjectBooked;
    /*private String maritalStatus;
    private int age;*/

    public Applicant(){
        super();
    }

    public Applicant(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }


    public void RequestWithdrawal(){
        getApplication().createWithdrawalRequest();
    }
    public void addEnquiry(Enquiry enquiry){
        enquiries.add(enquiry);
    }
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    public Application getApplication(){
        return this.application;
    }
    public void setFlatTypeBooked(FlatType flatTypeBooked) {
        FlatTypeBooked = flatTypeBooked;
    }
    public void setProjectBooked(Project projectBooked) {
        ProjectBooked = projectBooked;
    }
    public void setApplication(Application application) {
        this.application = application;
    }

    public FlatType getFlatTypeBooked() {
        return FlatTypeBooked;
    }
    public Project getProjectBooked() {
        return ProjectBooked;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public Enquiry findEnquiry(int id) {
        return enquiries.stream().filter(enquiry -> enquiry.getId()==id).findFirst().orElse(null);
    }

    public boolean hasEnquiries() {
        return !enquiries.isEmpty();
    }

    public boolean canBookFlat() {
        boolean canBook = false;
        if (application==null) {return false;}
        canBook = FlatTypeBooked==null
                && application.getProject().hasAvailableUnitsForApplicant(this)
                && !(application.getStatus()==ApplicationStatus.Booked || application.getStatus()==ApplicationStatus.Booking)
                && application.getStatus()==ApplicationStatus.Successful;
        return canBook;
    }

    public boolean canApplyForProject() {
        if (application==null) {return true;}
        if (application.getStatus()==ApplicationStatus.Unsuccessful
                || getApplication().getStatus()==ApplicationStatus.Withdrawn) {return true;}
        return false;
    }

}