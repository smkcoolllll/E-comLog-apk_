package com.EcomcataLog_.EcomcataLog_.service;

import com.EcomcataLog_.EcomcataLog_.DTO.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderNotificationService {

    @Autowired
    private EmailUtil emailUtil;

    public void sendOrderNotification(String recipientEmail, String productName, double productPrice) throws MessagingException, jakarta.mail.MessagingException {
        String subject = "Order Notification";
        String message = String.format("""
                <div>
                    <p>Dear Customer,</p>
                    <p>Thank you for placing an order with us!</p>
                    <p>Your product details:</p>
                    <p><strong>Product Name:</strong> %s</p>
                    <p><strong>Product Price:</strong> %.2f</p>
                    <p>You will receive your order in the upcoming days.</p>
                    <p>If you want to track your package or delivery progress, click <a href="TRACKING_LINK">here</a>.</p>
                    <p>Thank you for choosing us!</p>
                    <p>Best regards,</p>
                    <p>Your E-commerce Team</p>
                </div>
                """, productName, productPrice);

        emailUtil.sendEmail(recipientEmail, subject, message);
    }
}
