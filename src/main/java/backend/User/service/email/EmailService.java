package backend.User.service.email;

import backend.User.entity.QUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;
    private final EntityManager em;

    // 인증번호 생성
    public static final String ePw = UUID.randomUUID().toString().substring(0, 8);

    private MimeMessage createMessage(String to)throws Exception {

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
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);

        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
        }

        return ePw;
    }

    // == 이메일 중복 검증 ==
    public boolean isEmailExists(String email) {
        QUser user = QUser.user;
        BooleanExpression emailPredicate = user.email.eq(email);
        long count = new JPAQueryFactory(em)
                .selectFrom(user)
                .where(emailPredicate)
                .fetchCount();
        return count > 0;
    }
}
