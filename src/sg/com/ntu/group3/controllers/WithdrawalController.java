package sg.com.ntu.group3.controllers;

import enums.WithdrawalStatus;
import sg.com.ntu.group3.controllers.services.IWithdrawalService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.views.View;
import sg.com.ntu.group3.views.WithdrawalRequestView;

import java.util.List;

import enums.ApplicationStatus;

public class WithdrawalController implements IWithdrawalService {

    public WithdrawalController() {

    }

    public void submitWithdrawalRequest(Applicant applicant){
        try {
            if(applicant.getApplication().getStatus()==ApplicationStatus.Withdrawn
                    || applicant.getApplication().getStatus()==ApplicationStatus.RequestWithdrawal) {
                System.out.println("a withdrawal request has already been submitted");
                return;
            } else if (applicant.getApplication()!=null) {
                String choice = WithdrawalRequestView.showWithdrawalConfirmation(applicant);
                if (choice.equalsIgnoreCase("y")) {
                    WithdrawalRequestView.showOperationOutcomes(true);
                    applicant.RequestWithdrawal();
                } else if (choice.equalsIgnoreCase("n")){
                    System.out.println("Withdrawal Request Cancelled");
                } else {
                    WithdrawalRequestView.showOperationOutcomes(false);
                }
            } else {
                WithdrawalRequestView.showOperationOutcomes(false);
            }
        } catch (NullPointerException e) {
            View.showOperationOutcome("Withdrawal Request", false);
            System.out.println("No Application Found!");
        }

    }


    @Override
    public List<WithdrawalRequest> getPendingRequests(HDBManager manager) {
        return WithdrawalRequest.getAllWithdrawalRequests().stream()
                .filter(req -> req.status == WithdrawalStatus.PENDING)
                .filter(req -> req.getApplication().getProject().getCreatedBy().equalsIgnoreCase(manager.getName()))
                .toList();
    }

    @Override
    public void approveRequest(WithdrawalRequest request) {
        request.approve();
        // get application and its respective applicant
        Application application = request.getApplication();
        Applicant applicant = application.getApplicant();
        //update respective statuses
        application.setStatus(ApplicationStatus.Withdrawn);
        applicant.setApplication(null);
        application.getProject().removeApplicant(applicant);
        System.out.println("Application Withdrawal Approved");
    }

    @Override
    public void rejectRequest(WithdrawalRequest request) {
        request.reject();
        Application application = request.getApplication();
        application.setStatus(ApplicationStatus.WithdrawnUnsuccessful);
        System.out.println("Application Withdrawal Rejected");
    }


    public void reviewWithdrawalRequests(HDBManager manager) {
        List<WithdrawalRequest> pendingRequests = getPendingRequests(manager);

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending withdrawal requests for your projects.");
            return;
        }

        int index = WithdrawalRequestView.selectWithdrawalRequest(pendingRequests);
        if (index >= 0 && index < pendingRequests.size()) {
            WithdrawalRequest selected = pendingRequests.get(index);
            int decision = WithdrawalRequestView.promptApproveOrReject(selected);

            if (decision == 1) {
                approveRequest(selected);
                selected.approve();
                System.out.println("Withdrawal request approved.");
            } else if (decision == 2) {
                rejectRequest(selected);
                selected.reject();
                System.out.println("Withdrawal request rejected.");
            } else {
                System.out.println("Action cancelled.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
