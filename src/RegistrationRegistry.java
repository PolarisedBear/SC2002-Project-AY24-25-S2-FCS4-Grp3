import sg.com.ntu.groupX.models.Project;
import sg.com.ntu.groupX.models.Registration;
import sg.com.ntu.groupX.roles.HDBOfficer;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRegistry {
    private static List<Registration> allRegistrations = new ArrayList<>();

    public static void addRegistration(Registration registration) {
        allRegistrations.add(registration);
    }

    public static List<Registration> getAllRegistrations() {
        return allRegistrations;
    }

    public static List<Registration> getRegistrationsByOfficer(HDBOfficer officer) {

    }

    public static List<Registration> getRegistrationsByProject(Project project) {

    }
}
