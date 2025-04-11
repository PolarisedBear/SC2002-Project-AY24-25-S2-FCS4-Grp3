package sg.com.ntu.groupX.controllers.services;

public interface IEnquiryService {
    public boolean submitEnquiry(Enquiry enquiry);
    public void editEnquiry(Enquiry enquiry);
    public void deleteEnquiry(Enquiry enquiry);
    public void replyToEnquiry(Enquiry enquiry, String response);
}
