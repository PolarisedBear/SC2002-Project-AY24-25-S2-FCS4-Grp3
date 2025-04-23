package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

/** A subclass of the abstract class EligibilityRule, this class checks for the age and marital status of an applicant
 *
 */
public class AgeAndMaritalStatusEligibilityRule extends EligibilityRule {
    private boolean requiresMarried;
    private int minimumAge;

    /** To instantiate a new rule, the constructor needs to know the minimum age and the required marital status to count as eligible
     * @param requiresMarried use true if the applicant must be married,
     *                       use false if the marital status of the applicant is not important
     * @param minAge an integer to specify the minimum age to be counted as eligible
     */
    public AgeAndMaritalStatusEligibilityRule(boolean requiresMarried, int minAge) {
        this.minimumAge = minAge;
        this.requiresMarried = requiresMarried;
    }

    /** Inherited from EligibilityRule, this implementation checks the age and marital status of the applicant.
     * @param applicant The applicant to be checked
     * @return true if the applicant meets the eligibility rule, false if otherwise.
     */
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
