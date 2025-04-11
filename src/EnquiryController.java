import java.util.Map;

public class EnquiryController implements EnquiryView, IEnquiryService{
    private IEnquiryService enquiryService;
    private static Map<Project, Enquiry> enquiryMap;

    public EnquiryController(IEnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }
    public EnquiryController() {}

    public void submitEnquiry() {
    }

    public void editEnquiry() {
    }

    public void deleteEnquiry() {
    }

    public void replyToEnquiry() {
    }

    @Override
    public void displayEnquiryForm() {

    }

    @Override
    public void displayEnquiryList() {

    }

    @Override
    public void showEditEnquiry() {

    }

    @Override
    public void showResponseForm() {

    }

    @Override
    public boolean submitEnquiry(Enquiry enquiry) {

    }

    @Override
    public void editEnquiry(Enquiry enquiry) {

    }

    @Override
    public void deleteEnquiry(Enquiry enquiry) {

    }

    @Override
    public void replyToEnquiry(Enquiry enquiry, String response) {

    }
}
