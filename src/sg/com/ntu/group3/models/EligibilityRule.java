package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

/** Abstract EligibilityRule class for determining the eligibility conditions for a specific flat type.
 * Inherited by subclass rules that add their own implementations and attributes.
 */
public abstract class EligibilityRule {
    /** Abstract method to check if the applicant meets the elgibility conditions
     * @param applicant The applicant to be checked
     * @return true if the conditions are met, false otherwise.
     */
    public boolean isSatisfiedBy(Applicant applicant) {return true;};
}
