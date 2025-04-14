package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

public abstract class EligibilityRule {
    public boolean isSatisfiedBy(Applicant applicant) {return true;};
}
