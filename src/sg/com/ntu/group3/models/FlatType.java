package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Flat type class representing the data held by each flat type object. Each instantiation of a flat type is shared across all projects
 * <p>This class tracks the total number of units across all projects that offer flats of this type.
 * Includes a static map of all flat types, for use in searching during project creation.</p>
 */
public class FlatType {
    private String type;
    private int totalUnits;
    private int remainingUnits; //Remaining units of this flatType across all projects
    private List<EligibilityRule> eligibilityRules;
    private static Map<String, FlatType> typeList = new HashMap<>();

    /** Flat Type constructor. Automatically adds itself to the static map of all flat types.
     * @param eligibilityRules The list of rules that determine an applicant's eligibility for this type of flat
     * @param totalUnits The starting total number of units across all flats of this type
     * @param type The name of this flat type, which is what it will be mapped to in the static map typeList.
     */
    public FlatType(List<EligibilityRule> eligibilityRules, int totalUnits, String type) {
        this.eligibilityRules = eligibilityRules;
        this.totalUnits = totalUnits;
        this.remainingUnits = totalUnits;
        this.type = type;
        typeList.put(type, this);
    }

    public int getRemainingUnits() {
        return this.remainingUnits;
    }

    /** Method to update the number of remaining units. Used whenever a successful booking is made.
     * @param change The number of flats to update by
     * @param operator either subtraction or addition to the number of remaining units
     */
    public void updateRemainingUnits(int change, char operator) {
        if (operator == '+') {
            this.remainingUnits += change;
        }
        if (operator == '-') {
            this.remainingUnits -= change;
        }
    }

    /** Method to check if an applicant is eligible for this flat type. Used during application
     * @param applicant The applicant to be checked
     * @return true if the applicant satisfies at least one eligibility rule, false if they satisfy none of the rules.
     */
    public boolean isEligibleForApplicant(Applicant applicant) {
        for (EligibilityRule eligibilityRule : this.eligibilityRules) {
            if (eligibilityRule.isSatisfiedBy(applicant)) {
                return true;
            }
        }
        return false;
    }

    public void addEligibilityRule(EligibilityRule rule) {
        this.eligibilityRules.add(rule);
    }


    /** Method to search if the name of a given flat type exists in the static map typeList. Used during project creation
     * @param type The string type to be compared
     * @return true if this flat type exists, false if otherwise
     */
    public static boolean doesFlatTypeExist(String type) {
        return FlatType.getTypeList().containsKey(type);
    }

    /** Method to retrieve the static map of all flat types.
     * @return the static map typeList
     */
    public static Map<String, FlatType> getTypeList() {
        return typeList;
    }

    public String getType() {
        return type;
    }

    /** Override toString method for Flat Types.
     * @return formatting for flat types whenever they are printed to the console.
     */
    @Override
    public String toString() {
        return "FlatType: "+ type;
    }
    public boolean isFullyBooked(){ 
        return remainingUnits<=0;
    }

}
