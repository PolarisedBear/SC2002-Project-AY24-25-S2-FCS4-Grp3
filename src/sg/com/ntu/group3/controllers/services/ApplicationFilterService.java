package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.controllers.Session;
import sg.com.ntu.group3.models.Application;

import java.util.List;
import java.util.Map;

/** Class for handling filtering of a list of applications
 *
 */
public class ApplicationFilterService implements IApplicationFilterService{
    private Session session;

    public ApplicationFilterService(Session session) {
        this.session = session;
    }

    /** Method to filter a list of applications by the applicant's marital status.
     * Returns the filtered list of applications.
     * @param married boolean to determine if the applicants should all be married (true) or not (false)
     * @param AppList the list of applications to be filtered
     * @return the filtered list of applications, where all the applicants are either married or single
     */
    @Override
    public List<Application> filterByMaritalStatus(boolean married, List<Application> AppList) {
        List<Application> filteredList;
        if (married) {
            filteredList = AppList.stream().filter(application ->
                    application.getApplicant().getMaritalStatus().equalsIgnoreCase("married")).toList();
        } else {
            filteredList = AppList.stream().filter(application ->
                    application.getApplicant().getMaritalStatus().equalsIgnoreCase("single")).toList();
        }
        return filteredList;
    }

    /** Method to filter a list of applications by the age range (inclusive) of their applicants
     * @param minAge integer input for the minimum age
     * @param maxAge integer input for the maximum age
     * @param AppList the list of applications to be filtered
     * @return the filtered list of applications whose applicants fall between the minimum and maximum age
     */
    @Override
    public List<Application> filterByAge(int minAge, int maxAge, List<Application> AppList) {

        return AppList.stream().filter(application ->
                application.getApplicant().getAge()>=minAge && application.getApplicant().getAge()<=maxAge).toList();
    }

    /** Method to filter a list of applications, checking if the project applied to offers a specific flat type
     * @param flatType the FlatType object to check the applications against
     * @param AppList the list of applications to be filtered
     * @return the filtered list of applications to projects offering a specific flat type
     */
    @Override
    public List<Application> filterByFlatType(String flatType, List<Application> AppList) {

        return AppList.stream().filter(application ->
                application.getProject().checkForFlatType(flatType)).toList();
    }

    /** Method to search for a specific applications belonging to an applicant by the NRIC of the applicant
     * @param nric the string NRIC to search
     * @param applications the list of applications to search from
     * @return the application belonging to the respective applicant, null if no matching application was found
     */
    public Application filterByNRIC(String nric, List<Application> applications) {
        return applications.stream()
                .filter(appl -> appl.getApplicant()
                        .getNric().equalsIgnoreCase(nric))
                .findFirst().orElse(null);
    }

}
