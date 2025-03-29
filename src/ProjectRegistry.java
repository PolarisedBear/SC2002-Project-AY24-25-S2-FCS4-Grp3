import java.util.ArrayList;
import java.util.List;

public class ProjectRegistry{
    private static List<Project> allProjects = new ArrayList<>();
    private static void addProject(Project project){
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
        //
    }

}
