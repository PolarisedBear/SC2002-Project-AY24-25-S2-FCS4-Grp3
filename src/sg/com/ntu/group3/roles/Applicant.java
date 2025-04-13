package sg.com.ntu.group3.roles;

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
import sg.com.ntu.group3.views.EnquiryView;

public class Applicant extends User {
    private Application application;
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

    public void viewEligibleProjects(ProjectController projectController){
        List<Project> eligibleProjects = projectController.displayEligibleProjects(this);
        System.out.println(eligibleProjects);
    }
    public void applyForEligibleProject(Project project, FlatType flatType){
        if(ApplicationController.hasExistingBooking(this)){
            System.out.println("you have an existing booking");
            return;
        }
        if (project.isEligibleForApplication(this)) {
            ApplicationController.applyForProject(this, project, flatType);
        }
        else{
            System.out.println("Uneligible, please apply for an eligible project");
        }
    }
    public void viewApplicationStatus(){
        if(this.application!=null){
            System.out.println("sg.com.ntu.groupX.models.Application Status:" + this.application.getStatus());
        }
        else{
            System.out.println("Not applied for any projects.");
        }
    }
    public void RequestWithdrawal(){
        WithdrawalController.submitWithdrawalRequest();
    }
    public void SubmitEnquiry(String content, Project project){
        EnquiryController.submitEnquiry(this, content, project);
        System.out.println("Enquiry has been sent, please wait for a reply.");
    }
    public void editEnquiry(){
        EnquiryController.editEnquiry();

    }
    public void deleteEnquiry(){

        EnquiryController.deleteEnquiry(this);

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



}