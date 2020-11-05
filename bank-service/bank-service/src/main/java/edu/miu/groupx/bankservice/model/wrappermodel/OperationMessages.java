package edu.miu.groupx.bankservice.model.wrappermodel;

public enum OperationMessages {
    CANNOT_VERIFY_RECIPIENT_ACCOUNT("CANNOT_VERIFY_RECIPIENT'S_ACCOUNT "),
    CANNOT_VERIFY_PAYER_ACCOUNT("SOURCE_ACCOUNT_CANNOT_BE_VERIFIED"),
    NOT_ENOUGH_BALANCE("NOT_ENOUGH_BALANCE"),
    ACCOUNT_CANNOT_MAKE_PAYMENTS("ACCOUNT_IS_DISABLED_FOR_MAKING_PAYMENTS"),
    PAYMENT_COMPLETED_SUCCESSFULLY("TRANSACTION_HAS_COMPLETED_SUCCESSFULLY"),
    DEPOSIT_SUCCESSFUL("DEPOSIT_SUCCESSFUL"),
    DEPOSIT_UNSUCCESSFUL("CANNOT_COMPLETE_DEPOSIT"),
    ACCOUNT_NOT_FOUND("CANNOT_FIND_ACCOUNT"),
    WITHDRAWAL_UNSUCCESSFUL("CANNOT_COMPLETE_WITHDRAWAL "),
    WITHDRAWAL_SUCCESSFUL("WITHDRAWAL_SUCCESSFUL");
    private String operationMessages;

    public String getOperationMessages() {
        return this.operationMessages;
    }

    private OperationMessages(String operationMessages) {
        this.operationMessages = operationMessages;
    }
    }
