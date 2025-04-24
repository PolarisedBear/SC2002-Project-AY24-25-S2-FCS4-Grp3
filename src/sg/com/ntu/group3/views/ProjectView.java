package sg.com.ntu.group3.views;

import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** Project View class is responsible for displaying and gathering user input for operations related to projects.
 *
 */
public class ProjectView implements View{
    private static Scanner input = new Scanner(System.in);

    /** Method to print information from a project. Used for filtering, application and registration.
     * @param project The project to be displayed
     */
    public static void displayProjectInfo(Project project) {
        System.out.println(project.toString());
    };

    /** Overloaded method to display the master list of all projects
     *
     */
    public static void displayProjectList() {
        for (Project project : Project.getProjectList()) {
            System.out.println(project);
            View.lineSeparator();
        }
    };

    /** Overloaded method to display a given list of projects
     * @param projectList The list of projects to print
     */
    public static void displayProjectList(List<Project> projectList) {
        for (Project project : projectList) {
            System.out.println(project);
            View.lineSeparator();
        }
    }

    /** Form used to query the name of a project from the user
     * @return The name entered by the user.
     */
    public static String queryProjectName() {
        System.out.print("Enter project name: ");
        return input.nextLine();
    }

    /** Form used to initiate the project editing process
     * @return User input for the attribute of a project they wish to edit.
     */
    public static String showEditProjectForm() {
        System.out.println("Enter the attribute you'd like to edit");
        System.out.println("name, flattypes, neighbourhood, closedate, visibility, maxofficers, or incharge");
        return input.nextLine();
    };

    /** Overloaded form method to narrow the project editing process to a given attribute
     * @param attribute The attribute to edit
     * @return The new value of that attribute
     */
    public static String showEditProjectForm(String attribute) {
        System.out.println("Enter the new " + attribute + ":");
        return input.nextLine();
    }

    /** Specialised form to get user's choice to add or remove available flat types in a project.
     * @param project The project to be edited
     * @return User choice
     */
    public static int showEditProjectFlatTypes(Project project) {
        int key = -1;
        System.out.println("Current Project Flat Types:");
        System.out.println(project.getFlatTypes());
        System.out.println("Enter 1 to add flat type, 0 to remove, any other key to cancel");
        try {
            key = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
        }

        if (key==1 || key==0) {
            return key;
        } else {
            return -1;
        }
    }

    /** Form to query the user for the name of the flat type to remove from a project
     * @return The user input type name
     */
    public static String showRemoveProjectFlatTypes() {
        System.out.println("Type the name of the flat type to remove from this project:");
        return input.nextLine();
    }

    /** Form to query the user for the name of the flat type to add to a project
     * @return The user input type name
     */
    public static String showAddProjectFlatTypes() {
        System.out.println("Type the name of the flat type to add to this project:");
        return input.nextLine();
    }

    /** Form as a follow-up to when the user decides to add a new type of flat to the project. Queries the user for the number of available units to set.
     * @return The integer reflecting the number of units of the new flat type
     */
    public static Integer AddFlatNumberForm() {
        System.out.println("Enter the number of available flats of this type:");
        return input.nextInt();
    }

    /** Form to query the user for the new closing date of an existing project.
     * @return The date formatted from the user's string input
     * @throws ParseException if the user inputs an incorrect date format.
     */
    public static Date showEditProjectCloseDate() throws ParseException {
        System.out.println("Enter the new close date for this project (dd/mm/yyyy)");
        String dateInput = input.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date closeDate = sdf.parse(dateInput);
        return closeDate;
    }

    /** Form to query for a new manager in charge of a selected project
     * @return The selected manager's NRIC
     */
    public static String showEditManagerInCharge() {
        System.out.println("Enter the new NRIC of the manager to take charge:");
        return input.nextLine();
    }

    /** Overloaded method from View interface to show the outcome of an operation
     * @param successful true if the operation was successful, false if otherwise.
     */
    public static void showOperationOutcome(boolean successful) {
        if (successful) {
            System.out.println("Operation successful");
        } else {
            System.out.println("Operation unsuccessful");
        }
    }

    /** Generic form to gather user input for the next course of action to take.
     * Used by managers when indicating their choice to create/edit/delete project
     * @return The user input
     */
    public static String showCreateEditOrDeleteForm() {
        System.out.println("Create, edit, or delete BTO project.");
        System.out.println("Enter the corresponding number to select an action, or any other key to cancel");
        System.out.println("1. Create New BTO Project");
        System.out.println("2. Edit Existing BTO Project");
        System.out.println("3. Delete Existing BTO Project");
        return input.nextLine();
    }

    /** Main form for creating a new project. Involves other smaller methods to query the user for relevant information to include.
     * Used by managers for project creation.
     * @return The list of various parameters to include when creating a new project.
     * @throws ParseException if the format of the dates entered by the user are incorrect.
     */
    public static List<Object> showCreateProjectForm() throws ParseException {
        // enter name
        String name = queryProjectName();

        // enter neighbourhood
        System.out.print("Enter neighbourhood: ");
        String neighbourhood = input.nextLine();

        //enter dates
        Date openDate = createProjectFormOpenDate();
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
        //return (name, flatTypes, neighbourhood, openDate, closeDate, isVisible, maxOfficers, unitsAvailable);
        return List.of(name, flatTypes, neighbourhood, openDate, closeDate, isVisible, maxOfficers, unitsAvailable);
    };

    /** Form to query the user for an opening date for their newly created project.
     * @return the formatted date from the user input
     * @throws ParseException if the user inputs an incorrect format
     */
    private static Date createProjectFormOpenDate() throws ParseException {
        while (true) {
            System.out.print("Enter open date (dd/mm/yyyy), or any key to default to today: ");
            String dateInput = input.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (AuthenticationService.isValidDate(dateInput, sdf)) {
                return sdf.parse(dateInput);
            } else {
                return new Date();
            }
        }
    }

    /** Form to query the user for a closing date for their newly created project.
     * @return the formatted date from the user input
     * @throws ParseException if the user inputs an incorrect format
     */
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


    /** Form to query the user for the project to be removed
     * @return the project to be removed. If no such project was found, null is returned instead.
     */
    public static Project showRemoveProjectForm() {
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


    /** Form for managers to view projects.
     * @return The choice given by the manager user
     */
    public static int chooseProjectViewScope() {
        int choice = -1;
        try {
            System.out.println("\n=== View Projects ===");
            System.out.println("1. View My Created Projects");
            System.out.println("2. View All Projects");
            System.out.println("0. Cancel");
            System.out.print("Choose an option: ");

            choice = input.nextInt();
            input.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            input.nextLine();
        }

        return choice;
    }
}
