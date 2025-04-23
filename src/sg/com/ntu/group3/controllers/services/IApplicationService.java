package sg.com.ntu.group3.controllers.services;

import enums.ApplicationStatus;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.roles.Applicant;

/** Includes methods to modify and update application statuses, getting application statuses and requesting
 * flat booking for applicant
 * Implemented by application controllers
 */
public interface IApplicationService {
    ApplicationStatus getApplicationStatus(Application application);
    Boolean requestFlatBooking(Applicant applicant);
}
