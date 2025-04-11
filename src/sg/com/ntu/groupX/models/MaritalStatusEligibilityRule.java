package sg.com.ntu.groupX.models;

import sg.com.ntu.groupX.roles.Applicant;

public class MaritalStatusEligibilityRule implements EligibilityRule {
    private boolean requiresMarried;

    public MaritalStatusEligibilityRule(boolean requiresMarried) {
        this.requiresMarried = requiresMarried;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        if (!this.requiresMarried) {
            return true;
        } else {
            return applicant.getMaritalStatus().equalsIgnoreCase("Married");
        }
    }
}
