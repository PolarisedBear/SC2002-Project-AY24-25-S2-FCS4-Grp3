package sg.com.ntu.group3.models;

import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enquiry {
    private String content;
    private String response;
    private Project proj;
    private int enquiryId;
    private User user;
    private static Map<Project, List<Enquiry>> enquiryMap = new HashMap<>();
    private static int latestId = 1;

    public Enquiry(Project proj, String content, Applicant applicant) {
        this.content = content;
        this.user = applicant;
        this.proj = proj;
        this.enquiryId = latestId;
        latestId+=1;
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
    public int getId() {return enquiryId;}

    public void reply(String response) {
        this.response = response;
    }

    public void deleteEnquiry() {
        this.content = null;
        this.response = null;
        this.proj = null;
        this.enquiryId = Integer.parseInt(null);
    }


    public static Map<Project, List<Enquiry>> getEnquiryMap() {
        return enquiryMap;
    }

    @Override
    public String toString() {
        return "Enquiry{" +
                "\nContent: " + content +
                "\nResponse: " + response +
                "\nProject: " + proj.getName() +
                "\nEnquiryId: " + enquiryId +
                '}';
    }
}
