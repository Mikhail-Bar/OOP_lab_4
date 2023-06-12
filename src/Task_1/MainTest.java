package Task_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private EmailManager trafficManager;
    @BeforeEach
    void setUp() {
        trafficManager = new EmailManager();
    }

    @Test
    void addEmail() {
        LocalDateTime timestamp = LocalDateTime.now();
        trafficManager.addEmail("sender@example.com", "recipient@example.com", timestamp, 500);
        List<Email> emailList = trafficManager.getEmailList();
        assertEquals(2, emailList.size());

        Email email = emailList.get(0);
        assertEquals("new_sender@example.com", email.getSender());
        assertEquals("new_recipient@example.com", email.getRecipient());
        assertEquals(timestamp, email.getTimestamp());
        assertEquals(500, email.getSizeBytes());
    }

    @Test
    void editEmail() {
        LocalDateTime timestamp = LocalDateTime.now();
        trafficManager.addEmail("sender@example.com", "recipient@example.com", timestamp, 500);

        trafficManager.editEmail(0, "new_sender@example.com", "new_recipient@example.com",
                timestamp.plusHours(1), 1000);
        List<Email> emailList = trafficManager.getEmailList();
        assertEquals(1, emailList.size());

        Email email = emailList.get(0);
        assertEquals("new_sender@example.com", email.getSender());
        assertEquals("new_recipient@example.com", email.getRecipient());
        assertEquals(timestamp.plusHours(1), email.getTimestamp());
        assertEquals(1000, email.getSizeBytes());
    }

    @Test
    void deleteEmail() {
        LocalDateTime timestamp = LocalDateTime.now();
        trafficManager.addEmail("sender@example.com", "recipient@example.com", timestamp, 500);

        trafficManager.deleteEmail(0);
        List<Email> emailList = trafficManager.getEmailList();
        assertEquals(2, emailList.size());
    }
}
