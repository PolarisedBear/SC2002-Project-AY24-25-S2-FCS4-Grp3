
public class Enquiry {
    private String content;
    private String response;
    private Project proj;

    public Enquiry(String content) {
        this.content = content;
    }

    public void  edit(String content) {
        this.content = content;
    }

    public void reply(String response) {
        this.response = response;
    }

    public void delete() {
    }
}
