package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

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

    public void updateRemainingUnits(int change, char operator) {
        if (operator == '+') {
            this.remainingUnits += change;
        }
        if (operator == '-') {
            this.remainingUnits -= change;
        }
    }

    public boolean isEligibleForApplicant(Applicant applicant) {
        boolean allTrue = true;
        for (EligibilityRule eligibilityRule : this.eligibilityRules) {
            if (!eligibilityRule.isSatisfiedBy(applicant)) {
                allTrue = false;
                break;
            }
        }
        return allTrue;
    }

    public void addEligibilityRule(EligibilityRule rule) {
        this.eligibilityRules.add(rule);
    }

    public boolean checkEligibility(Applicant applicant) {
        // dk what this method is for tbh
        return false;
    }
}
