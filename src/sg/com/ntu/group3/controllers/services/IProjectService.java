package sg.com.ntu.group3.controllers.services;

import java.util.Date;
import java.util.List;

public interface IProjectService {
    public List<Project> findProjectsByManager(HDBManager manager);
    public boolean hasActiveProject(HDBManager manager, Date date);
}
