package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.controllers.Session;
import sg.com.ntu.group3.models.Application;

import java.util.List;
import java.util.Map;

public class ApplicationFilterService implements IApplicationFilterService{
    private Session session;

    public ApplicationFilterService(Session session) {
        this.session = session;
    }

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

    @Override
    public List<Application> filterByAge(int minAge, int maxAge, List<Application> AppList) {

        return AppList.stream().filter(application ->
        {return application.getApplicant().getAge()>=minAge && application.getApplicant().getAge()<=maxAge;}).toList();
    }

    @Override
    public List<Application> filterByFlatType(String flatType, List<Application> AppList) {

        return AppList.stream().filter(application ->
                application.getProject().checkForFlatType(flatType)).toList();
    }

    public Application filterByNRIC(List<Application> applications, String nric) {
        return applications.stream()
                .filter(appl -> appl.getApplicant()
                        .getNric().equalsIgnoreCase(nric))
                .findFirst().orElse(null);
    }

    @Override
    public List<Application> filterByCompositeCriteria(Map criteria) {
        return List.of();
    }
}
