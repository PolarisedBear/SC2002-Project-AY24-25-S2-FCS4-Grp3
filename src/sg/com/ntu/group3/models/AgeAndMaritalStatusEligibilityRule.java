package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

public class AgeAndMaritalStatusEligibilityRule extends EligibilityRule {
    private boolean requiresMarried;
    private int minimumAge;

    public AgeAndMaritalStatusEligibilityRule(boolean requiresMarried, int minAge) {
        this.minimumAge = minAge;
        this.requiresMarried = requiresMarried;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        if (applicant.getAge() >= this.minimumAge) {
            if (!this.requiresMarried) {
                return true;
            } else {
                return applicant.getMaritalStatus().equalsIgnoreCase("Married");
            }
        } else {
            return false;
        }
    }
}
