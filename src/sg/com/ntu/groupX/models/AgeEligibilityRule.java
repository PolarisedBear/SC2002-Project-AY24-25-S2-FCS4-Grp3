package sg.com.ntu.groupX.models;

import sg.com.ntu.groupX.roles.Applicant;

public class AgeEligibilityRule implements EligibilityRule {
    private int minimumAge;

    public AgeEligibilityRule(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        return applicant.getAge() >= this.minimumAge;
    }
}
