package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.IWithdrawalService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.views.WithdrawalRequestView;

import java.util.List;

import enums.ApplicationStatus;

public class WithdrawalController implements IWithdrawalService {

    public WithdrawalController() {

    }

    public void submitWithdrawalRequest(Applicant applicant){
        if(applicant.getApplication().getStatus()==ApplicationStatus.Withdrawn
                || applicant.getApplication().getStatus()==ApplicationStatus.RequestWithdrawal) {
            System.out.println("a withdrawal request has already been submitted");
            return;
        } else if (applicant.getApplication()!=null) {
            String choice = WithdrawalRequestView.showWithdrawalConfirmation(applicant);
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
                WithdrawalRequestView.showOperationOutcomes(true);
                applicant.RequestWithdrawal();
            } else {
                WithdrawalRequestView.showOperationOutcomes(false);
            }
        } else {
            WithdrawalRequestView.showOperationOutcomes(false);
        }

    }

    public void processWithdrawalRequest(WithdrawalRequest request, boolean approved) {
        
    }


    @Override
    public WithdrawalRequest createRequest(Application application) {
        return null;
    }

    @Override
    public List<WithdrawalRequest> getPendingRequests() {
        return List.of();
    }

    @Override
    public void approveRequest(WithdrawalRequest request) {

    }

    @Override
    public void rejectRequest(WithdrawalRequest request) {

    }


    public void approveWithdrawal(Application application) {
        application.setStatus(ApplicationStatus.Withdrawn);
        application.getApplicant().setApplication(null);
        application.getProject().getApplicants().remove(application.getApplicant());
        System.out.println("application withdrawal approved");
    }
    public void rejectWithdrawal(Application application) {
        application.setStatus(ApplicationStatus.WithdrawnUnsuccessful);
        System.out.println("application withdrawal");
    }
}
