package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.User;

import java.util.HashMap;
import java.util.Map;

public class Enquiry {
    private String content;
    private String response;
    private Project proj;
    private String enquiryId;
    private User user;
    private static Map<Project, Enquiry> enquiryMap = new HashMap<>();

    public Enquiry(Project proj, String content, Applicant applicant) {
        this.content = content;
        this.user = applicant;
        this.proj = proj;
        proj.addEnquiry(this);
        applicant.addEnquiry(this);
    }

    public void editEnquiry(String content) {
        this.content = content;
    }

    public void submitEnquiry() {
        
    }

    public String getContent() {
        return content;
    }

    public Project getProj() {
        return proj;
    }
    public User getUser() {
        return user;
    }

    public void reply(String response) {
        this.response = response;
    }

    public void deleteEnquiry() {
        this.content = null;
        this.response = null;
        this.proj = null;
    }

    public static Map<Project, Enquiry> getEnquiryMap() {
        return enquiryMap;
    }

}
