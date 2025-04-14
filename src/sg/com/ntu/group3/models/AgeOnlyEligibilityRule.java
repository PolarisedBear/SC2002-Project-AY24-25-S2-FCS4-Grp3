package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

public class AgeOnlyEligibilityRule extends EligibilityRule {
    private int minimumAge;

    public AgeOnlyEligibilityRule(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        return applicant.getAge() >= this.minimumAge;
    }
}
