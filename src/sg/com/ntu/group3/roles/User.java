package sg.com.ntu.group3.roles;

import sg.com.ntu.group3.controllers.services.IUserService;
import sg.com.ntu.group3.models.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User implements IUserService {
    private String name;
    private String id;
    private String nric = null;
    private String password;
    private int age = 0;
    private String maritalStatus = "Single";
    private static List<User> userList = new ArrayList<>();

    public User() {
        Scanner scan = new Scanner(System.in);
        System.out.println("New sg.com.ntu.groupX.roles.User! Enter your name:");
        this.name = scan.next();
        System.out.println("Enter your password:");
        this.password = scan.next();
        userList.add(this);
    }

   public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
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
            if (!sg.com.ntu.groupX.roles.User.isValidNric(this.nric)) {
                System.out.println("Invalid NRIC");
            }
        } while (!sg.com.ntu.groupX.roles.User.isValidNric(this.nric));

    } */

    public String getNric() {
        return nric;
    }

    public void setMaritalStatus(String status) {

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

    public static List<User> getUserList() {
        return userList;
    }

    public String getInfo() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", nric='" + nric + '\'' +
                ", age=" + age +
                ", maritalStatus='" + maritalStatus + '\'' +
                '}';
    }
}
