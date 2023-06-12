package Task_1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {
    public static final String FILE_PATH = "email_traffic.csv";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Email> emailList;

    public EmailManager() {
        emailList = new ArrayList<>();
        loadEmailsFromFile();
    }
    public List<Email> getEmailList() {
        return emailList;
    }
    public void viewEmails(String address, LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println("Все письма адресата: " + address);
        System.out.println("Начало периода: " + startTime.format(DATE_FORMATTER));
        System.out.println("Конец периода: " + endTime.format(DATE_FORMATTER));

        int totalIncomingBytes = 0;
        int totalOutgoingBytes = 0;

        for (Email email : emailList) {
            if (email.getSender().equals(address) && isWithinTimeRange(email.getTimestamp(), startTime, endTime)) {
                totalOutgoingBytes += email.getSizeBytes();
                System.out.println("Исходящее письмо: " + email);
            } else if (email.getRecipient().equals(address) && isWithinTimeRange(email.getTimestamp(), startTime, endTime)) {
                totalIncomingBytes += email.getSizeBytes();
                System.out.println("Входящее письмо: " + email);
            }
        }

        System.out.println("Всего исходящих писем в байтах: " + totalOutgoingBytes);
        System.out.println("Всего входящих писем в байтах: " + totalIncomingBytes);
    }

    public void addEmail(String sender, String recipient, LocalDateTime timestamp, int sizeBytes) {
        Email newEmail = new Email(sender, recipient, timestamp, sizeBytes);
        emailList.add(newEmail);
        saveEmailsToFile();
    }

    public void editEmail(int index, String sender, String recipient, LocalDateTime timestamp, int sizeBytes) {
        if (index >= 0 && index < emailList.size()) {
            Email editedEmail = new Email(sender, recipient, timestamp, sizeBytes);
            emailList.set(index, editedEmail);
            saveEmailsToFile();
        } else {
            System.out.println("Неверный порядковый номер письма.");
        }
    }

    public void deleteEmail(int index) {
        if (index >= 0 && index < emailList.size()) {
            emailList.remove(index);
            saveEmailsToFile();
        } else {
            System.out.println("Неверный порядковый номер письма.");
        }
    }

    public void loadEmailsFromFile() {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(";");
                    if (fields.length == 4) {
                        String sender = fields[0];
                        String recipient = fields[1];
                        LocalDateTime timestamp = LocalDateTime.parse(fields[2], DATE_FORMATTER);
                        int sizeBytes = Integer.parseInt(fields[3]);
                        Email email = new Email(sender, recipient, timestamp, sizeBytes);
                        emailList.add(email);
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка чтения из файла: " + e.getMessage());
            }
        }
    }

    public void saveEmailsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Email email : emailList) {
                String line = email.getSender() + ";" +
                        email.getRecipient() + ";" +
                        email.getTimestamp().format(DATE_FORMATTER) + ";" +
                        email.getSizeBytes();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файла: " + e.getMessage());
        }
    }

    public boolean isWithinTimeRange(LocalDateTime timestamp, LocalDateTime startTime, LocalDateTime endTime) {
        return timestamp.isAfter(startTime) && timestamp.isBefore(endTime);
    }
}