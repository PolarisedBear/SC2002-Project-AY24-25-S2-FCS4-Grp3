package sg.com.ntu.group3.roles;

import java.util.List;

import enums.ApplicationStatus;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;

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

    public void viewEligibleProjects(){
        List<Project> eligibleProjects = ProjectRegistry.findEligibleProjects(this);
        System.out.println("Elligible projects:\n");
        for(Project project : eligibleProjects){
            System.out.println(project.getName());
        }
    }
    public void applyForEligibleProject(Project project, FlatType flatType){
        List<Project> eligibleProjects = ProjectRegistry.findEligibleProjects(this);
        if(eligibleProjects.contains(project)){ //if eligible for project
            this.application = new Application(project, flatType, this);
            this.application.setStatus(ApplicationStatus.Pending);
        }
        else{
            System.out.println("Uneligible sg.com.ntu.groupX.models.Project, Please apply for an eligible project");
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
    /* public void requestFlatBooking(){
        if(this.application!=null && this.application.getstatus()==ApplicationStatus.Successful){
            bookFlat(application.getflatType());
            System.out.println("Flat booking requested, please wait for a HDB officer to book a flat for you.");
        }
        else{
            System.out.println("Since your application is unsuccessful, you cannot request to book a flat.");
        }
    } */
    public void RequestWithdrawal(){
        if(this.application!=null && this.application.getStatus()==ApplicationStatus.Withdrawn){
            this.application = null;
            System.out.println("sg.com.ntu.groupX.models.Application withdrawn.");
        }
        else{
            System.out.println("sg.com.ntu.groupX.models.Application does not exist.");
        }
    }
    public void SubmitEnquiry(String content, Project project){
        Enquiry newEnquiry = new Enquiry(project, content, this);
        enquiries.add(newEnquiry);
        System.out.println("sg.com.ntu.groupX.models.Enquiry has been sent, please wait for a reply.");
    }
    public void editEnquiry(){
        

    }
    public void deleteEnquiry(){

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