package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Enquiry;

public interface IEnquiryService {
    public boolean submitEnquiry(Enquiry enquiry);
    public void editEnquiry(Enquiry enquiry);
    public void deleteEnquiry(Enquiry enquiry);
    public void replyToEnquiry(Enquiry enquiry, String response);
}
