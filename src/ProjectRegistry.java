import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Locale.filter;

public class ProjectRegistry{
    private static List<Project> allProjects = new ArrayList<>();
    public static void addProject(Project project){
        allProjects.add(project);
    }
    public static List<Project> removeProject(Project project){
        if(allProjects.remove(project)){
            return allProjects;
        }
        return allProjects;
    }

    public static List<Project> getAllProjects(){
        return allProjects;
    }

    public static List<Project> findEligibleProjects(Applicant applicant){
        // Conditions for specific flat types
        List<Project> eligibleProjects;
        // Check if applicant is single, 35 yrs and above or 21 years and above, married
        if (applicant.getAge() >= 35 && applicant.getMaritalStatus().equals("Single")) {
            // use stream to filter by the number of available flat types. check if its above 0, then add to the list
            eligibleProjects = getAllProjects().stream()
                    .filter(project -> {
                        Map<FlatType, Integer> eligibleUnits = project.getUnitsAvailable();
                        return eligibleUnits.containsKey(FlatType.TWO_ROOM) && eligibleUnits.get(FlatType.TWO_ROOM) > 0;
                    }).toList();

        } else if (applicant.getAge() >= 21 && applicant.getMaritalStatus().equals("Married")) {

            eligibleProjects = getAllProjects().stream()
                    .filter(project -> {
                        Map<FlatType, Integer> eligibleUnits = project.getUnitsAvailable();
                        return (eligibleUnits.getOrDefault(FlatType.TWO_ROOM, 0) > 0) || (eligibleUnits.getOrDefault(FlatType.THREE_ROOM, 0) > 0);
                    }).toList();

        } else {
            // if applicant fits neither criteria return empty list.
            eligibleProjects = new ArrayList<>();
        }
        return eligibleProjects;
    }

}
