package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBManager;

import java.util.Date;
import java.util.List;

public interface IProjectService {
    List<Project> findProjectsByManager(HDBManager manager);
    boolean hasActiveProject(HDBManager manager, Date date);
    List<Project> getProjectList();
}
