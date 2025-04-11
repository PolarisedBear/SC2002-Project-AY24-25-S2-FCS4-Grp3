package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

public interface EligibilityRule {
    public boolean isSatisfiedBy(Applicant applicant);
}
