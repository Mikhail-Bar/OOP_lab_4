package Task_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;

public class MainTest {
    private static final String TEST_FILE_NAME = "test.txt";

    @BeforeEach
    public void setUp() {
        File testFile = new File(TEST_FILE_NAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testAddWord() {
        Main testText = new Main();
        Set<String> dictionary = testText.loadDictionaryFromFile(TEST_FILE_NAME);
        dictionary.add("qwerty");
        Assertions.assertTrue(dictionary.contains("qwerty"));
    }

    @Test
    public void testRemoveWord() {
        Main testText = new Main();
        Set<String> dictionary = testText.loadDictionaryFromFile(TEST_FILE_NAME);
        dictionary.add("qwerty");
        dictionary.add("qwerty1");
        dictionary.remove("qwerty");
        Assertions.assertFalse(dictionary.contains("qwerty"));
        Assertions.assertTrue(dictionary.contains("qwerty1"));
    }

    @Test
    public void testSaveAndLoadDictionary() {
        Main testText = new Main();
        Set<String> dictionary = testText.loadDictionaryFromFile(TEST_FILE_NAME);
        dictionary.add("qwertry");
        dictionary.add("qwertry1");
        testText.saveDictionaryToFile(dictionary, TEST_FILE_NAME);
        Set<String> loadedDictionary = testText.loadDictionaryFromFile(TEST_FILE_NAME);
        Assertions.assertTrue(loadedDictionary.contains("qwertry"));
        Assertions.assertTrue(loadedDictionary.contains("qwertry1"));
    }
}
