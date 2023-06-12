package Task_1;

import java.time.LocalDateTime;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmailManager manager = new EmailManager();

        while (true) {
            System.out.println("ПОЧТА - v2");
            System.out.println("1. Показать почту");
            System.out.println("2. Добавить письмо");
            System.out.println("3. Редактировать письмо");
            System.out.println("4. Удалить письмо");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Напишите адрес эл.почты: ");
                    String address = scanner.nextLine();
                    System.out.print("Дата и время начала поиска (гггг-ММ-дд ЧЧ:мм:сс): ");
                    LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine(), EmailManager.DATE_FORMATTER);
                    System.out.print("Дата и время конца поиска (гггг-ММ-дд ЧЧ:мм:сс): ");
                    LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine(), EmailManager.DATE_FORMATTER);
                    manager.viewEmails(address, startTime, endTime);
                    break;
                case 2:
                    System.out.print("Напишите отправителя: ");
                    String sender = scanner.nextLine();
                    System.out.print("Напишите получателя: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Дата и время (гггг-ММ-дд ЧЧ:мм:сс): ");
                    LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine(), EmailManager.DATE_FORMATTER);
                    System.out.print("Напишите размер (байт): ");
                    int sizeBytes = scanner.nextInt();
                    scanner.nextLine();
                    manager.addEmail(sender, recipient, timestamp, sizeBytes);
                    System.out.println("Письмо успешно добавлено.");
                    break;
                case 3:
                    System.out.print("Напишите порядковый номер письма: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Напишите отправителя: ");
                    sender = scanner.nextLine();
                    System.out.print("Напишите получателя: ");
                    recipient = scanner.nextLine();
                    System.out.print("Дата и время (гггг-ММ-дд ЧЧ:мм:сс): ");
                    timestamp = LocalDateTime.parse(scanner.nextLine(), EmailManager.DATE_FORMATTER);
                    System.out.print("Напишите размер (байт): ");
                    sizeBytes = scanner.nextInt();
                    scanner.nextLine();
                    manager.editEmail(index, sender, recipient, timestamp, sizeBytes);
                    System.out.println("Письмо успешно обновлено.");
                    break;
                case 4:
                    System.out.print("Напишите порядковый номер письма: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteEmail(index);
                    System.out.println("Письмо успешно удалено.");
                    break;
                case 5:
                    System.out.println("Выход из программы");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите действие от 1 до 5.");
            }
        }
    }
}



