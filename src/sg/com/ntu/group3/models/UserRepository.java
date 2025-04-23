package sg.com.ntu.group3.models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;

/** This class is used only to extract information from csv files containing user details.
 *
 */
public class UserRepository {
    private static final String applicantfilepath = "src/ApplicantList.csv"; //read different types of users
    private static final String officerfilepath = "src/OfficerList.csv";
    private static final String managerfilepath = "src/ManagerList.csv";

    public static List<Applicant> getAllApplicants() throws IOException { 
        List<String[]> rows = ExcelFileReader.read(applicantfilepath);
        List<Applicant> applicantList = new ArrayList<>();
        boolean firstRow = true;

        for (String[] row : rows) {
            if (firstRow) {firstRow = false; continue;}
            Applicant applicant = new Applicant(row[0], row[1], Integer.parseInt (row[2]), row[3], row[4]);
            applicantList.add(applicant);

        }

        return applicantList;
    }

    public static List<HDBOfficer> getAllOfficers() throws IOException {
        List<String[]> rows = ExcelFileReader.read(officerfilepath);
        List<HDBOfficer> officerList = new ArrayList<>();
        boolean firstRow = true;

        for (String[] row : rows) {
            if (firstRow) {firstRow = false; continue;}
            HDBOfficer officer = new HDBOfficer(row[0], row[1], Integer.parseInt (row[2]), row[3], row[4]);
            officerList.add(officer);
        }

        return officerList;
    }

    public static List<HDBManager> getAllManagers() throws IOException {
        List<String[]> rows = ExcelFileReader.read(managerfilepath);
        List<HDBManager> managerList = new ArrayList<>();
        boolean firstRow = true;

        for (String[] row : rows) {
            if (firstRow) {firstRow = false; continue;}
            HDBManager manager = new HDBManager(row[0], row[1], Integer.parseInt (row[2]), row[3], row[4]);
            managerList.add(manager);
        }

        return managerList;
    }
}
