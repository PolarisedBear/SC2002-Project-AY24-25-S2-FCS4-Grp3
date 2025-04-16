package sg.com.ntu.group3.views;

import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectView implements View{
    private static Scanner input = new Scanner(System.in);

    public static void displayProjectInfo(Project project) {
        System.out.println(project.toString());
    };
    public static void displayProjectList() {
        System.out.println(Project.getProjectList());
    };
    public static void displayProjectList(List<Project> eligibleProjects) {
        System.out.println(eligibleProjects);
    }
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

    public static Integer AddFlatNumberForm() {
        System.out.println("Enter the number of available flats of this type:");
        return input.nextInt();
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

    public static List<Object> showCreateProjectForm() throws ParseException {
        // enter name
        System.out.print("Enter project name: ");
        String name = input.nextLine();

        // enter neighbourhood
        System.out.print("Enter neighbourhood: ");
        String neighbourhood = input.nextLine();

        //enter date
        Date closeDate = createProjectFormCloseDate();

        //toggle visibility
        System.out.print("Set Project Visibility (true/false): ");
        boolean isVisible = Boolean.parseBoolean(input.nextLine());

        //max number of officers
        System.out.print("Enter max number of HDB officers: ");
        int maxOfficers = Integer.parseInt(input.nextLine());

        List<FlatType> flatTypes = new ArrayList<>();
        Map<FlatType, Integer> unitsAvailable = new HashMap<>();

        // gets flat types and units available
        System.out.println("Enter flat types (type 'done' to stop):");
        while (true) {
            System.out.print("New flat type: ");
            String flatInput = input.nextLine();
            if (flatInput.equalsIgnoreCase("done")) break;
            // check if flat type already exists
            if (FlatType.doesFlatTypeExist(flatInput)) {
                FlatType flatType = FlatType.getTypeList().get(flatInput);
                flatTypes.add(flatType);
                System.out.print("Units available for " + flatInput + ": ");
                int units = Integer.parseInt(input.nextLine());
                unitsAvailable.put(flatType, units);
            } else {System.out.println("Invalid Flat Type!");}

        }
        List<Object> projectParameters = List.of(name, flatTypes, neighbourhood, closeDate, isVisible, maxOfficers, unitsAvailable);
        //return (name, flatTypes, neighbourhood, closeDate, isVisible, maxOfficers, unitsAvailable);
        return projectParameters;
    };

    private static Date createProjectFormCloseDate() throws ParseException {
        while (true) {
            System.out.print("Enter close date (dd/mm/yyyy): ");
            String dateInput = input.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (AuthenticationService.isValidDate(dateInput, sdf)) {
                return sdf.parse(dateInput);
            } else {
                System.out.println("Invalid Date!");
            }
        }
    }


    public static Object showRemoveProjectForm() {
        displayProjectList();
        System.out.println("Enter the name of the project you'd like to remove, or press enter to cancel:");
        String choice = input.nextLine();
        if (choice.isEmpty()) {
            System.out.println("Operation Cancelled");
            return null;
        } else if (!Project.projectExists(choice)) {
            showOperationOutcome(false);
            System.out.println("Project does not exist");
            return null;
        } else {
            return Project.findProject(choice);
        }

    }


    public static int chooseProjectViewScope() {
        System.out.println("\n=== View Projects ===");
        System.out.println("1. View My Created Projects");
        System.out.println("2. View All Projects");
        System.out.println("0. Cancel");
        System.out.print("Choose an option: ");

        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }
}
