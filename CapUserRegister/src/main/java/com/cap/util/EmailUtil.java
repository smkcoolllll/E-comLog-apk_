package com.cap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendOtpEmail(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Verify OTP");
    mimeMessageHelper.setText("""
        <div>
          <p>Your verification code: %s</p>
          <p>Click the following link to verify your account:</p>
          <a href="http://localhost:8082/verify-account?email=%s&otp=%s" target="_blank">Verify Account</a>
        </div>
        """.formatted(otp, email, otp), true);

    javaMailSender.send(mimeMessage);
  }

  // public void sendOtpEmail(String email, String otp) throws MessagingException
  // {
  // MimeMessage mimeMessage = javaMailSender.createMimeMessage();
  // MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
  // mimeMessageHelper.setTo(email);
  // mimeMessageHelper.setSubject("Verify OTP");
  // mimeMessageHelper.setText("""
  // <div>
  // <a href="http://localhost:8082/verify-account?email=%s&otp=%s"
  // target="_blank">click link to verify</a>
  // </div>
  // """.formatted(email, otp), true);

  // javaMailSender.send(mimeMessage);
  // }

  public void sendPasswordResetEmail(String email, String newPassword) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Set Password");

    String emailContent = String.format("""
        <div>
          <p>Dear User,</p>
          <p>A password reset request has been initiated for your account.</p>
          <p>Your new temporary password is: %s</p>
          <p>Please click on the following link to set your new password:</p>
          <p><a href="http://localhost:8082/set-password?email=%s" target="_blank">Set Password</a></p>
          <p>If you did not request a password reset, please ignore this email.</p>
          <p>Best regards,</p>
          <p>Your Application Team</p>
        </div>
        """, newPassword, email);

    mimeMessageHelper.setText(emailContent, true);

    javaMailSender.send(mimeMessage);
  }

  // public void sendPasswordResetEmail(String email) throws MessagingException {
  // MimeMessage mimeMessage = javaMailSender.createMimeMessage();
  // MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
  // mimeMessageHelper.setTo(email);
  // mimeMessageHelper.setSubject("set password...");
  // mimeMessageHelper.setText("""
  // <div>
  // <a href="http://localhost:8082/set-password?email=%s" target="_blank">click
  // link to set password</a>
  // </div>
  // """.formatted(email), true);

  // javaMailSender.send(mimeMessage);
  // }

}
