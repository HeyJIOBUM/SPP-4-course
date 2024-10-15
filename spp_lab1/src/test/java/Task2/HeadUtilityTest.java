package Task2;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HeadUtilityTest {
    private final static Path path =
            Paths.get("D:\\JetBrains\\Projects\\IntelIjProjects\\seventhTerm\\spp\\spp_lab1\\src\\test\\java\\Task2\\test.txt");

    @BeforeEach
    public void setUp() throws IOException {
        Files.createFile(path);
    }

    @Test
    public void head_testExtractLessLines() throws IOException {
        String content =
                """
                        Hello World,
                        I am java.
                        Hello!
                        """;
        writeToFile(content);

        List<String> expectedLines = List.of(
                "Hello World,",
                "I am java."
        );
        List<String> receivedLines = HeadUtility.head("head n -2 " + path);

        Assertions.assertEquals(expectedLines, receivedLines);
    }

    @Test
    public void head_testExtractMoreLines() throws IOException {
        String content =
                """
                        Hello World,
                        I am java.
                        Hello!
                        """;
        writeToFile(content);

        List<String> expectedLines = List.of(
                "Hello World,",
                "I am java.",
                "Hello!"
        );
        List<String> receivedLines = HeadUtility.head("head n -5 " + path);

        Assertions.assertEquals(expectedLines, receivedLines);
    }

    @Test
    public void head_testExtractEqualLines() throws IOException {
        String content =
                """
                        Hello World,
                        I am java.
                        Hello!
                        """;
        writeToFile(content);

        List<String> expectedLines = List.of(
                "Hello World,",
                "I am java.",
                "Hello!"
        );
        List<String> receivedLines = HeadUtility.head("head n -3 " + path);

        Assertions.assertEquals(expectedLines, receivedLines);
    }

    @Test
    public void head_testExtractZeroLinesAndGetException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                HeadUtility.head("head n -0 " + path));
    }

    @Test
    public void head_testExtractMissingFileAndGetException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                HeadUtility.head("head n -10 missingFile.txt"));
    }

    public void writeToFile(String content) throws IOException {
        Files.write(path, content.getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.delete(path);
    }
}
