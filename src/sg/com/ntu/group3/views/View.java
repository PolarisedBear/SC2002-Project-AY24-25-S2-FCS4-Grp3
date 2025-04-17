package sg.com.ntu.group3.views;

public interface View {
    static void showOperationOutcome(String action, boolean success) {
        if (success) System.out.println(action + " successful");
        else System.out.println(action + " unsuccessful");
    };
    static void lineSeparator() {
        System.out.println("----------------------------------");
    }
}
