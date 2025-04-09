public class MaritalStatusEligibilityRule implements EligibilityRule{
    private boolean requiresMarried;

    public MaritalStatusEligibilityRule(boolean requiresMarried) {
        this.requiresMarried = requiresMarried;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        return false;
    }
}
