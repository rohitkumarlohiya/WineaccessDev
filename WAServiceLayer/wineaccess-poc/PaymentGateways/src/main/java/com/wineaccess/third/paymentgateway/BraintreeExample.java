package com.wineaccess.third.paymentgateway;

import java.math.BigDecimal;
import com.braintreegateway.*;

public class BraintreeExample {
    public static void main(String[] args) {
        BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "zsgzyqss89kybnzq",
            "c6g2mkxhtjgfvj27",
            "b13a53c5a6fa9105f7f55433264accd2"
        );

        TransactionRequest request = new TransactionRequest()
          .amount(new BigDecimal("1000.00"))
          .creditCard()
            .number("4111111111111111")
            .expirationMonth("05")
            .expirationYear("2019")
            .done();

        Result<Transaction> result = gateway.transaction().sale(request);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            System.out.println("Success!: " + transaction.getId());
        } else if (result.getTransaction() != null) {
            System.out.println("Message: " + result.getMessage());
            Transaction transaction = result.getTransaction();
            System.out.println("Error processing transaction:");
            System.out.println("  Status: " + transaction.getStatus());
            System.out.println("  Code: " + transaction.getProcessorResponseCode());
            System.out.println("  Text: " + transaction.getProcessorResponseText());
        } else {
            System.out.println("Message: " + result.getMessage());
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                System.out.println("Attribute: " + error.getAttribute());
                System.out.println("  Code: " + error.getCode());
                System.out.println("  Message: " + error.getMessage());
            }
        }
    }
}
