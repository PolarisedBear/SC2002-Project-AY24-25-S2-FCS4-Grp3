package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBManager;

import java.util.Date;
import java.util.List;

public interface IProjectService {
    public List<Project> findProjectsByManager(HDBManager manager);
    public boolean hasActiveProject(HDBManager manager, Date date);
    public List<Project> getProjectList();
}
