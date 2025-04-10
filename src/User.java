import java.util.List;
import java.util.Scanner;

public class User implements IUserService{
    private String name;
    private String id;
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

   /* public void setAge() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Set your age:");
            this.age = scan.nextInt();
        } while (this.age<=0);
    } */


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public void setNric() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Set NRIC:");
            this.nric = scan.next();
            if (!User.isValidNric(this.nric)) {
                System.out.println("Invalid NRIC");
            }
        } while (!User.isValidNric(this.nric));

    } */

    public String getNric() {
        return nric;
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

    @Override
    public boolean login(String nric, String password) {
        return nric.equalsIgnoreCase(this.nric) && password.equals(this.password);
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public void updateProfile(User user) {

    }
}
