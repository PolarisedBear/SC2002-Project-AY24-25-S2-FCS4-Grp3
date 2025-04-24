package sg.com.ntu.group3.views;

/** View interface showing default methods for use by all view classes.
 *
 */
public interface View {
    /** Generic method to show the result of an operation
     * @param action The operation taken
     * @param success true if successful, false otherwise.
     */
    static void showOperationOutcome(String action, boolean success) {
        if (success) System.out.println(action + " successful");
        else System.out.println(action + " unsuccessful");
    };

    /** Generic method to print a line separator for easier viewing.
     */
    static void lineSeparator() {
        System.out.println("----------------------------------");
    }
}
