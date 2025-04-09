public class AgeEligibiityRule implements EligibilityRule{
    private int minimumAge;

    public AgeEligibiityRule(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    @Override
    public boolean isSatisfiedBy(Applicant applicant) {
        return false;
    }
}
