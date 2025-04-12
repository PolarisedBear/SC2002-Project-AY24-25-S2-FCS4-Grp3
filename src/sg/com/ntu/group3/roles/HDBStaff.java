package sg.com.ntu.group3.roles;
import sg.com.ntu.group3.models.Enquiry;

abstract class HDBStaff {

    public HDBStaff() {
    }

    public void replyToEnquiry(Enquiry enquiry, String reply) {
        enquiry.reply(reply);
    }
}
