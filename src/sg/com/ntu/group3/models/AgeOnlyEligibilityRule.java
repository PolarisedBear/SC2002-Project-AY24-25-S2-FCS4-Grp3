package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

/** A subclass of the abstract class EligibilityRule, this class only checks the age of applicants.
 *
 */
public class AgeOnlyEligibilityRule extends EligibilityRule {
    private int minimumAge;

    /** Instantiating a new rule requires a minimum age that eligible applicants must meet.
     * @param minimumAge the minimum age as an integer
     */
    public AgeOnlyEligibilityRule(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    /** Inherited from EligibilityRule, this implementation checks the applicant's age to see if it meets the eligibility rule.
     * @param applicant Applicant to be checked
     * @return true if the applicant's age is equal to or above the minimum, false if otherwise.
     */
    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        return applicant.getAge() >= this.minimumAge;
    }
}
