package Task_1;

import java.time.LocalDateTime;

public class Email {
    private String sender;
    private String recipient;
    private LocalDateTime timestamp;
    private int sizeBytes;

    public Email(String sender, String recipient, LocalDateTime timestamp, int sizeBytes) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.sizeBytes = sizeBytes;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getSizeBytes() {
        return sizeBytes;
    }

    @Override
    public String toString() {
        return "Отправитель: " + sender + ", Получатель: " + recipient + ", Дата и время: " + timestamp +
                ", Размер: " + sizeBytes + " байт";
    }
}