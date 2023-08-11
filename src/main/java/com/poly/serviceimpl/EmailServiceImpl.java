package com.poly.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.poly.constain.EmailConstain;

@Service
public class EmailServiceImpl {
	private JavaMailSender javaMailSender;


	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendWelcomeEmail(String toEmail) {
		// Code để gửi email chào mừng đến người dùng sau khi đăng ký thành công.
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(toEmail);
			message.setSubject(EmailConstain.GREETING);
			message.setText(EmailConstain.CONTENT);

			javaMailSender.send(message);
		} catch (MailException ex) {
			// Xử lý nếu có lỗi xảy ra khi gửi email.
		}
	}
	
	public String generateRandomPassword() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // Sinh số ngẫu nhiên trong khoảng 1000 đến 9999
        return String.valueOf(randomNumber);
    }
	
	public void sendEmailFogotPass(String username, String toEmail, String newPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(EmailConstain.COME_BACK);
            message.setText("Xin chào " + username + "\n\n" + EmailConstain.COME_BACK_CONTENT +
                    "Mật khẩu mới của bạn là: " + newPassword + "\n\n" +
                    "Trân trọng");

            javaMailSender.send(message);
            System.out.println("Đã gửi email chào mừng thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
