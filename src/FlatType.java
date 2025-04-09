import java.util.List;

public class FlatType {
    private String type;
    private int totalUnits;
    private int remainingUnits;
    private List<EligibilityRule> eligibilityRules;

    public FlatType(List<EligibilityRule> eligibilityRules, int totalUnits, String type) {
        this.eligibilityRules = eligibilityRules;
        this.totalUnits = totalUnits;
        this.remainingUnits = totalUnits;
        this.type = type;
    }

    public int getRemainingUnits() {
        return this.remainingUnits;
    }

    public void updateRemainingUnits() {

    }

    public boolean isEligibleForApplicant(Applicant applicant) {

    }
}
