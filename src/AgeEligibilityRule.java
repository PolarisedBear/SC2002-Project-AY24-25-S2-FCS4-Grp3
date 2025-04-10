public class AgeEligibilityRule implements EligibilityRule{
    private int minimumAge;

    public AgeEligibilityRule(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        return applicant.getAge() >= this.minimumAge;
    }
}
