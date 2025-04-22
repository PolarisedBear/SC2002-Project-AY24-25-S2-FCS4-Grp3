package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Enquiry;

/** Interface for modifying enquiries.
 *  Implemented with enquiry controllers
 */
public interface IEnquiryService {
    void editEnquiry(Enquiry enquiry);
    void deleteEnquiry(Enquiry enquiry);
    void replyToEnquiry(Enquiry enquiry, String response);
}
