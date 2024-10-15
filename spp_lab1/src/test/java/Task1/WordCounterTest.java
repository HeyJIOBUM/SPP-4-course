package Task1;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class WordCounterTest {
    private final static Path path =
            Paths.get("D:\\JetBrains\\Projects\\IntelIjProjects\\seventhTerm\\spp\\spp_lab1\\src\\test\\java\\Task1\\test.txt");

    @BeforeEach
    public void setUp() throws IOException {
        Files.createFile(path);
    }

    @Test
    public void getWordLinks_TestMultiline() throws IOException {
        String content =
                """
                Hello World,
                I am java.
                Hello!
                """;
        writeToFile(content);

        Map<String, List<Integer>> expectedWordEntries = Map.of(
                "Hello", new ArrayList<>(List.of(1, 3)),
                "World", new ArrayList<>(List.of(1)),
                "I", new ArrayList<>(List.of(2)),
                "am", new ArrayList<>(List.of(2)),
                "java", new ArrayList<>(List.of(2))
        );
        Map<String, List<Integer>> receivedWordEntries = WordCounter.getWordLinks(path.toString());

        Assertions.assertEquals(expectedWordEntries, receivedWordEntries);
    }

    @Test
    public void getWordLinks_TestOneLine() throws IOException {
        String content =
                """
                Hello World. I am java. Hello everyone!!!!
                """;
        writeToFile(content);

        Map<String, List<Integer>> expectedWordEntries = Map.of(
                "Hello", new ArrayList<>(List.of(1, 1)),
                "World", new ArrayList<>(List.of(1)),
                "I", new ArrayList<>(List.of(1)),
                "am", new ArrayList<>(List.of(1)),
                "java", new ArrayList<>(List.of(1)),
                "everyone", new ArrayList<>(List.of(1))
        );
        Map<String, List<Integer>> receivedWordEntries = WordCounter.getWordLinks(path.toString());

        Assertions.assertEquals(expectedWordEntries, receivedWordEntries);
    }

    @Test
    public void getWordLinks_TestEmpty() throws IOException {
        String content =
                """
                
                
                """;
        writeToFile(content);

        Map<String, List<Integer>> expectedWordEntries = Collections.emptyMap();
        Map<String, List<Integer>> receivedWordEntries = WordCounter.getWordLinks(path.toString());

        Assertions.assertEquals(expectedWordEntries, receivedWordEntries);
    }

    public void writeToFile(String content) throws IOException {
        Files.write(path, content.getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.delete(path);
    }
}
