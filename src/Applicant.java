import java.util.List;

import enums.ApplicationStatus;

public class Applicant extends User{
    private Application application;
    private List<Enquiry> enquiries;
    private String maritalStatus;
    private int age;

    public Applicant(String martialStatus, int age){
        this.maritalStatus = maritalStatus;
        this.age = age;
    }

    public void viewEligibleProjects(){
        List<Project> eligibleProjects = ProjectRegistry.findEligibleProjects(this);
        System.out.println("Elligible projects:\n");
        for(Project project : eligibleProjects){
            System.out.println(project.getName());
        }
    }
    public void applyForEligibleProject(Project project, enums.FlatType flatType){
        List<Project> eligibleProjects = ProjectRegistry.findEligibleProjects(this);
        if(eligibleProjects.contains(project)){ //if eligible for project
            this.application = new Application(project, flatType, this);
            this.application.setStatus(ApplicationStatus.Pending);
        }
        else{
            System.out.println("Uneligible Project, Please apply for an eligible project");
        }
    }
    public void viewApplicationStatus(){
        if(this.application!=null){
            System.out.println("Application Status:" + this.application.getStatus());
        }
        else{
            System.out.println("Not applied for any projects.");
        }
    }
    public void requestFlatBooking(){
        if(this.application!=null && this.application.getstatus()==ApplicationStatus.Successful){
            bookFlat(application.getflatType());
            System.out.println("Flat booking requested, please wait for a HDB officer to book a flat for you.");
        }
        else{
            System.out.println("Since your application is unsuccessful, you cannot request to book a flat.");
        }
    }
    public void RequestWithdrawal(){
        if(this.application!=null && this.application.getstatus()==ApplicationStatus.Withdrawn){
            this.application = null;
            System.out.println("Application withdrawn.");
        }
        else{
            System.out.println("Application does not exist.");
        }
    }
    public void SubmitEnquiry(String content, Project project){
        Enquiry newEnquiry = new Enquiry(content);
        enquiries.add(newEnquiry);
        System.out.println("Enquiry has been sent, please wait for a reply.");
    }
    public void editEnquiry(){
        

    }
    public void deleteEnquiry(){

    }
    public Application getApplication(){
        return this.application;
    }
    public String getMaritalStatus(){
        return maritalStatus;
    }

    public int getAge(){
        return age;
    }
    //
    public String getName(){
        return name;
    }
}