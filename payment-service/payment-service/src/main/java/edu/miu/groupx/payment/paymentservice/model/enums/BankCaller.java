package edu.miu.groupx.payment.paymentservice.model.enums;

public enum BankCaller {
    BANK_PAYMENT_URI("/bank/make-payment");


    private String bankApiUri;

    public String getBankApiUri() {
        return this.bankApiUri;
    }

    private BankCaller(String bankApiUri) {
        this.bankApiUri = bankApiUri;
    }
}
