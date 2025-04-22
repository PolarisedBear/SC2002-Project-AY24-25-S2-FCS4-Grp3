package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Enquiry;

public interface IEnquiryService {
    void editEnquiry(Enquiry enquiry);
    void deleteEnquiry(Enquiry enquiry);
    void replyToEnquiry(Enquiry enquiry, String response);
}
