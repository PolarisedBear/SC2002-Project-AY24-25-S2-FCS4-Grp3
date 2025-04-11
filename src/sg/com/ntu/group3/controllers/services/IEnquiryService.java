package sg.com.ntu.group3.controllers.services;

public interface IEnquiryService {
    public boolean submitEnquiry(Enquiry enquiry);
    public void editEnquiry(Enquiry enquiry);
    public void deleteEnquiry(Enquiry enquiry);
    public void replyToEnquiry(Enquiry enquiry, String response);
}
