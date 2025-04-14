package sg.com.ntu.group3.roles;

import java.util.ArrayList;
import java.util.List;

import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.EnquiryController;
import sg.com.ntu.group3.controllers.ProjectController;
import sg.com.ntu.group3.controllers.WithdrawalController;
import sg.com.ntu.group3.controllers.ApplicationController;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.views.ApplicationView;
import sg.com.ntu.group3.views.EnquiryView;
import sg.com.ntu.group3.views.ProjectView;

public class Applicant extends User {
    private Application application;
    private ApplicationController applicationController;
    private EnquiryController enquiryController;
    private WithdrawalController withdrawalController;
    private ApplicationView applicationView;
    private EnquiryView enquiryView;
    private List<Enquiry> enquiries;
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

    public List<Project> viewEligibleProjects(Applicant applicant){

        List<Project> Projects = Project.getProjectList();
        List<Project> eligibleProjects = new ArrayList<>();
        
        for (Project project : Projects) {
            if (project.isVisible() && project.isEligibleForApplication(applicant)) {
                eligibleProjects.add(project);
            }
        }

        return eligibleProjects;

    }
    /*public void applyForEligibleProject(Project project, FlatType flatType){
        if(applicationController.hasExistingBooking(this)){
            System.out.println("you have an existing booking");
            return;
        }
        if (project.isEligibleForApplication(this)) {
            applicationController.applyForProject(this, project, flatType);
        }
        else{
            System.out.println("Uneligible, please apply for an eligible project");
        }
    }*/
    /*public void viewApplicationStatus(){
        if(this.application!=null){
            this.application.getStatus();
        }

    }*/
    public void requestFlatBooking(){
        if(this.application!=null && this.application.getStatus()==ApplicationStatus.Successful){
            applicationController.requestFlatBooking(this.application);
            System.out.println("requested to book a flat based on application");
        }

    }

    public void RequestWithdrawal(){
        getApplication().createWithdrawalRequest();
    }
    public void addEnquiry(Enquiry enquiry){
        enquiries.add(enquiry);
    }
    public void editEnquiry(Enquiry enquiry){
        enquiryController.editEnquiry(enquiry);
    }
    public void deleteEnquiry(Enquiry enquiry){

        enquiryController.deleteEnquiry(enquiry);

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

    public boolean canBookFlat() {
        boolean canBook = true;
        canBook = !(application==null) //list fail conditions
                && !(FlatTypeBooked==null)
                && application.getProject().hasAvailableUnitsForApplicant(this)
                && !(application.getStatus()==ApplicationStatus.Booked || application.getStatus()==ApplicationStatus.Booking)
                && application.getStatus()==ApplicationStatus.Successful;
        return canBook;
    }

    public boolean canApplyForProject() {
        if (getApplication()==null) {return true;}
        if (getApplication().getStatus()==ApplicationStatus.Unsuccessful
                || getApplication().getStatus()==ApplicationStatus.Withdrawn) {return true;}
        return false;
    }

}