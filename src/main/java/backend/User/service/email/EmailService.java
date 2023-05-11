package backend.User.service.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;

    private MimeMessage createMessage(String to, String ePw)throws Exception {

        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("이메일 인증 테스트");

        String html = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/email-template.html")));
        html = html.replace("{ePw}", ePw);
        message.setContent(html, "text/html;charset=utf-8");
        message.setFrom(new InternetAddress("hsh4509@gmail.com", "HanSeongJun")); // 보내는 사람

        return message;
    }

    @Transactional
    public String sendSimpleMessage(String to)throws Exception {

        String ePw = UUID.randomUUID().toString().substring(0, 8);

        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to, ePw);

        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
        }

        return ePw;
    }
}
