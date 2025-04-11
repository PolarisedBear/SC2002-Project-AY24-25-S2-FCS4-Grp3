package sg.com.ntu.group3.views;

import sg.com.ntu.group3.controllers.ProjectController;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectView {
    private static Scanner input = new Scanner(System.in);

    public void displayProjectInfo() {};
    public void displayProjectList() {};
    public void showEditProjectForm() {};
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
