package sg.com.ntu.groupX.models;

import sg.com.ntu.groupX.roles.User;

public class Enquiry {
    private String content;
    private String response;
    private Project proj;
    private String enquiryId;
    private User user;

    public Enquiry(Project proj, String content, User user) {
        this.content = content;
        this.user = user;
        this.proj = proj;
    }

    public void editEnquiry(String content) {
        this.content = content;
    }

    public void submitEnquiry() {

    }

    public Project getProj() {
        return proj;
    }

    public void reply(String response) {
        this.response = response;
    }

    public void deleteEnquiry() {
        this.content = null;
        this.response = null;
        this.proj = null;
    }

}
