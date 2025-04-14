package sg.com.ntu.group3.views;
public class EnquiryView implements View {
    public void displayEnquiryForm();
    public void displayEnquiryList();
    public void showResponseForm();

    
    public static void displayEnquirySubmit(){
        System.out.println("Enquiry submitted");
    }
    public static void showEditEnquiry(){
        System.out.println("Edit Enquiry Form");
    };
}
