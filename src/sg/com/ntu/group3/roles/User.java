package sg.com.ntu.group3.roles;

import java.util.ArrayList;
import java.util.List;

/** User class is a superclass to all the different user roles: Applicant, HDBManager, HDBOfficer
 * <p>The user class handles generic data that is shared across all types of user accounts.
 * It represents the data stored in all user accounts regardless of their type, and only handles retrieval and modification of basic attributes.
 * Includes a static list of all registered users.</p>
 *
 */
public class User {
    private String name;
    private String nric = null;
    private String password;
    private int age = 0;
    private String maritalStatus = "Single";
    private static List<User> userList = new ArrayList<>();


    /** User constructor that is called whenever any type of user account is made. Automatically adds itself into the master list upon creation.
     * @param name user's name
     * @param nric user's nric
     * @param age user's age
     * @param maritalStatus
     * @param password
     */
    public User(String name, String nric, int age, String maritalStatus, String password) {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;
        userList.add(this);
    }

    /** Method to set the age of the user. Used when updating user profile.
     * @param age the new age to update to
     */
   public void setAge(int age) {
        this.age = age;
    }

    /** Method to set the name of the user. Used when updating user profile
     * @param name the new name of the user
     */
    public void setName(String name){
        this.name = name;
    }

    /** Method to retrieve the name of the user. Used for filtering in various operations
     * @return the name of the user
     */
    public String getName(){
        return name;
    }

    /** Method to get the user's password. Used for authentication and password change.
     * @return the current password
     */
    public String getPassword() {
        return password;
    }

    /** Method to set a new password. Used during password change.
     * @param password to change the old password to.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Method to retrieve the NRIC of the user. Used during application filtering, and manager in charge assignment.
     * @return this user's NRIC.
     */
    public String getNric() {
        return nric;
    }

    /** Method to change the marital status of this user. Used during user profile updating.
     * @param status the new marital status to update to
     */
    public void setMaritalStatus(String status) {

        this.maritalStatus = status;
    }

    /** Method to retrieve the marital status of this user. Used in application filtering and eligibility checks for applicants.
     * @return the marital status of this user.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /** Method to return the age of the user. Used in eligibility checks for applicants.
     * @return the age of this user.
     */
    public int getAge() {
        return age;
    }


    /** Method to retrieve the current list of all registered users. Used in searching and filtering operations.
     * @return The static userList that stores all currently registered users.
     */
    public static List<User> getUserList() {
        return userList;
    }

    /** Override toString method for all user types.
     * @return the name of this user when printed onto the console.
     */
    @Override
    public String toString() {
        return name;
    }

    /** Alternative method for displaying more information from the user. Used when displaying user info.
     * @return A string format including some of the attributes
     */
    public String getInfo() {
        return  "Name: " + name +
                "\nNRIC: " + nric +
                "\nAge: " + age +
                "\nMarital Status: " + maritalStatus;
    }
}
