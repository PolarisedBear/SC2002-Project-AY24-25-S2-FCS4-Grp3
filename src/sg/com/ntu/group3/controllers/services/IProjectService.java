package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBManager;

import java.util.Date;
import java.util.List;

/** Project service including methods related to project searching for managers.
 * Implemented by ProjectController.
 *
 */
public interface IProjectService {
    List<Project> findProjectsByManager(HDBManager manager);
    boolean hasActiveProject(HDBManager manager, Date date);

}
