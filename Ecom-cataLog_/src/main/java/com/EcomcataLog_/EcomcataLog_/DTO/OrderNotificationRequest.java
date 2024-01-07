package com.EcomcataLog_.EcomcataLog_.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNotificationRequest {

    private String recipientEmail;
    private String productName;
    private double productPrice;
}