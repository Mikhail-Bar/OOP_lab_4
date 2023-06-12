package Task_2;

import java.io.*;
import java.util.*;

public class Main implements Serializable {
    public static void main(String[] args) {
        Set<String> dictionary = loadDictionaryFromFile("words.txt");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("СЛОВАРИК - v2");
            System.out.println("1. Добавить слова");
            System.out.println("2. Удалить слово");
            System.out.println("3. Вывести словарь");
            System.out.println("4. Завершить программу");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите слова для добавления (разделите пробелами):");
                    String wordsToAdd = scanner.nextLine();
                    String[] words = wordsToAdd.split("\\s+");
                    dictionary.addAll(Arrays.asList(words));
                    break;
                case 2:
                    System.out.println("Введите слово для удаления:");
                    String wordToRemove = scanner.nextLine();
                    dictionary.remove(wordToRemove);
                    break;
                case 3:
                    System.out.println("Словарь:");
                    for (String word : dictionary) {
                        System.out.println(word);
                    }
                    break;
                case 4:
                    saveDictionaryToFile(dictionary, "words.txt");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите действие от 1 до 4.");
                    break;
            }
        }
    }

    public static Set<String> loadDictionaryFromFile(String fileName) {
        Set<String> dictionary = new TreeSet<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            dictionary = (Set<String>) inputStream.readObject();
            System.out.println("Словарь успешно загружен из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл словаря не найден. Используется пустой словарь.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке словаря из файла. Используется пустой словарь.");
        }

        return dictionary;
    }

    public static void saveDictionaryToFile(Set<String> dictionary, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(dictionary);
            System.out.println("Словарь успешно сохранен в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении словаря в файл.");
        }
    }
}
