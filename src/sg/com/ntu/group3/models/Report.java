package sg.com.ntu.group3.models;

import java.util.List;

import enums.ApplicationStatus;
import sg.com.ntu.group3.roles.Applicant;

/** Report class responsible for printing information to the console as a report.
 *
 */
public class Report {

    public Report() {
    }

    /** Method to generate a number of applicants' booking reports
     * @param number The number of reports to generate
     * @param applicants The list of applicants to generate from
     */
    public void generateApplicantBookingReport(int number, List<Applicant> applicants) {
        int counter = 0;
        for (Applicant applicant : applicants){
            if(counter >= number){
                break;
            }
            System.out.println("Applicant Name: " + applicant.getName());
            System.out.println("Age: " + applicant.getAge());
            System.out.println("Marital Status: " + applicant.getMaritalStatus());
            
// If application for applicant exists and it is successful in status
            if (applicant.getApplication() != null && applicant.getApplication().getStatus() == ApplicationStatus.Successful) {
                System.out.println("Flat Type: " + applicant.getApplication().getProject().getFlatTypes());
                System.out.println("Project Name: " + applicant.getApplication().getProject().getName());
            } else {
                System.out.println("No application yet!");
            }
            counter++;

        }
    }


}
