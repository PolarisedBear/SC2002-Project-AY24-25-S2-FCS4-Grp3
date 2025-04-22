package sg.com.ntu.group3.roles;

import sg.com.ntu.group3.models.Application;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String nric = null;
    private String password;
    private int age = 0;
    private String maritalStatus = "Single";
    private static List<User> userList = new ArrayList<>();

    public User() {
        this.name = "None";
        //Create a dummy user that doesn't ger searched since it is not in the masterlist
    }

    public User(String name, String nric, int age, String maritalStatus, String password) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;
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


    public String getNric() {
        return nric;
    }

    public void setMaritalStatus(String status) {

        this.maritalStatus = status;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public int getAge() {
        return age;
    }

    public void notifyStatusChange(Application application) {

    }

    public static List<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getInfo() {
        return  "Name: " + name +
                "\nNRIC: " + nric +
                "\nAge: " + age +
                "\nMarital Status: " + maritalStatus;
    }
}
