package sg.com.ntu.group3.models;

import java.util.List;

import enums.ApplicationStatus;
import sg.com.ntu.group3.roles.Applicant;

public class Report {

    public Report() {
    }

    public void generateApplicantBookingReport(int number, List<Applicant> applicants) {
        int counter = 0;
        for (Applicant applicant : applicants){
            if(counter >= number){
                break;
            }
            System.out.println("sg.com.ntu.groupX.roles.Applicant Name: " + applicant.getName());
            System.out.println("Age: " + applicant.getAge());
            System.out.println("Marital Status: " + applicant.getMaritalStatus());
            
// If application for applicant exists and it is successful in status
            if (applicant.getApplication() != null && applicant.getApplication().getStatus() == ApplicationStatus.Successful) {
                System.out.println("Flat Type: " + applicant.getApplication().getProject().getFlatTypes());
                System.out.println("sg.com.ntu.groupX.models.Project Name: " + applicant.getApplication().getProject().getName());
            } else {
                System.out.println("No application yet!");
            }
            counter++;

        }
    }

    public List<Applicant> filterByMaritalStatus(int number) {

    }

    public List<Applicant> filterByFlatType(int number) {

    }

    public List<Applicant> filterByAgeRange(int lowerBound, int higherBound) {

    }
}
