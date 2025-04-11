package sg.com.ntu.groupX.models;

import sg.com.ntu.groupX.roles.Applicant;

public interface EligibilityRule {
    public boolean isSatisfiedBy(Applicant applicant);
}
