package sg.com.ntu.group3.views;

import sg.com.ntu.group3.controllers.ProjectController;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectView {
    private static Scanner input = new Scanner(System.in);

    public void displayProjectInfo(Project project) {
        System.out.println(project.toString());
    };
    public void displayProjectList() {};
    public static String showEditProjectForm() {
        System.out.println("Enter the attribute you'd like to edit");
        return input.nextLine();
    };

    public static String showEditProjectForm(String attribute) {
        System.out.println("Enter the new " + attribute + ":");
        return input.nextLine();
    }

    public static int showEditProjectFlatTypes(Project project) {
        System.out.println("Current Project Flat Types:");
        System.out.println(project.getFlatTypes());
        System.out.println("Enter 1 to add flat type, 0 to remove, any other key to cancel");
        int key = input.nextInt();
        input.nextLine();

        if (key==1 || key==0) {
            return key;
        } else {
            return -1;
        }
    }

    public static String showRemoveProjectFlatTypes(Project project) {
        System.out.println("Type the name of the flat type to remove from this project:");
        return input.nextLine();
    }

    public static String showAddProjectFlatTypes(Project project) {
        System.out.println("Type the name of the flat type to add to this project:");
        return input.nextLine();
    }

    public static Date showEditProjectCloseDate(Project project) throws ParseException {
        System.out.println("Enter the new close date for this project (dd/mm/yyyy)");
        String dateInput = input.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date closeDate = sdf.parse(dateInput);
        return closeDate;
    }

    public static void showOperationOutcome(boolean successful) {
        if (successful) {
            System.out.println("Operation successful");
        } else {
            System.out.println("Operation unsuccessful");
        }
    }

    public static Project showCreateProjectForm() throws ParseException {
        System.out.print("Enter project name: ");
        String name = input.nextLine();

        System.out.print("Enter project ID: ");
        String projectId = input.nextLine();

        System.out.print("Enter neighbourhood: ");
        String neighbourhood = input.nextLine();

        System.out.print("Enter close date (dd/mm/yyyy): ");
        String dateInput = input.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date closeDate = sdf.parse(dateInput);

        System.out.print("Set Project Visibility (true/false): ");
        boolean isVisible = Boolean.parseBoolean(input.nextLine());

        System.out.print("Enter max number of HDB officers: ");
        int maxOfficers = Integer.parseInt(input.nextLine());

        List<FlatType> flatTypes = new ArrayList<>();
        Map<FlatType, Integer> unitsAvailable = new HashMap<>();

        System.out.println("Enter flat types (type 'done' to stop):");
        while (true) {
            System.out.print("New flat type: ");
            String flatInput = input.nextLine();
            if (flatInput.equalsIgnoreCase("done")) break;
            // check if flat type already exists
            if (FlatType.getTypeList().containsKey(flatInput)) {
                FlatType flatType = FlatType.getTypeList().get(flatInput);
                flatTypes.add(flatType);
                System.out.print("Units available for " + flatInput + ": ");
                int units = Integer.parseInt(input.nextLine());
                unitsAvailable.put(flatType, units);
            } else {System.out.println("Invalid Flat Type!");}

        }
        return new Project(name, projectId, flatTypes, neighbourhood, closeDate, isVisible, maxOfficers, unitsAvailable);

    };
}
