package io.github.Yuurim98.digi_capsule.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String SUBJECT = "회원가입 이메일 인증";
    private final String TEXT = "발급된 번호를 입력해 주세요. 번호 : ";

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationEmail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject(SUBJECT);
        message.setText(TEXT + verificationCode);
        javaMailSender.send(message);
    }
}
