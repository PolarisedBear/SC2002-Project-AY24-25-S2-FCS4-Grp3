import java.util.Date;
import java.util.List;
import java.util.Map;

public class Project {
    private String name;
    private String neighbourhood;
    private Date openDate;
    private Date closeDate;
    private boolean isVisible;
    private int maxOfficers;
    private String createdBy;
    private Map<FlatType, Integer> unitsAvailable;
    private List<Application> applications;
    private List<Enquiry> enquiries;
    private List<HDBOfficer> hdbOfficers;
}
