import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String name;
    private String nric = null;
    private String password;
    private int age = 0;
    private String maritalStatus = "Single";

    public User() {
        Scanner scan = new Scanner(System.in);
        System.out.println("New User! Enter your name:");
        this.name = scan.next();
        System.out.println("Enter your password:");
        this.password = scan.next();
    }

    public void setAge() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Set your age:");
            this.age = scan.nextInt();
        } while (this.age<=0);
    }

    public static boolean isValidNric(String nric) {
        // Define the nric string
        String regex = "^[STFG]\\d{7}[A-Z]$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nric);

        return matcher.matches(); // Returns true if nric uses the correct format
    }

    public void setNric() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Set NRIC:");
            this.nric = scan.next();
            if (!User.isValidNric(this.nric)) {
                System.out.println("Invalid NRIC");
            }
        } while (!User.isValidNric(this.nric));

    }

    public void setMaritalStatus() {
        Scanner scan = new Scanner(System.in);
        List<String> validStatuses = List.of("Married", "Single");
        String status=" ";
        do {
            System.out.println("Update Marital Status: Single or Married. Currently set to: " + this.maritalStatus);
            status = scan.next();
            if (!validStatuses.contains(status)) System.out.println("Invalid Marital Status");
        } while (!validStatuses.contains(status));
        this.maritalStatus = status;
    }

    public void notifyStatusChange(Application application) {

    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }
}
