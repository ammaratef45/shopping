package edu.miu.groupx.bankservice.model.wrappermodel;

public enum OperationStatus {
    SUCCESS("SUCCESS"),
    FAILED("FAILED");
    private String operationStatusMessages;

    public String getOperationStatusMessages() {
        return this.operationStatusMessages;
    }

    private OperationStatus(String operationStatusMessages) {
        this.operationStatusMessages = operationStatusMessages;
    }
}
