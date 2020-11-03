package edu.miu.groupx.payment.paymentservice.model.enums;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED");
    private String paymentStatus;

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    private PaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
