package Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/*
Вариант 3 — head
Утилита head выводит несколько (по умолчанию 10) первых строк из файла.
Формат использования:
head [−n] file
Ключ -n <line numbers> (или просто −<line numbers>) позволяет изменить количество
выводимых строк.
Пример использования:
head n -20 app.log
head -20 app.log
Выводит 20 первых строк из файла app.log.
Для решения задачи подойдет класс java.io.RandomAccessFile, реализующий
произвольный доступ к файлу (чтение и запись с любой позиции в файле).
 */

public class HeadUtility {
    private static Path filePath;
    private static int linesCount;

    public static List<String> head(String command) {
        parseCommand(command);
        return getFirstLines();
    }

    private static void parseCommand(String command) {
        Pattern commandComponentsDelim = Pattern.compile(" +");
        String[] commandComponents = commandComponentsDelim.split(command);

        if (commandComponents.length == 0 || !commandComponents[0].equals("head")) {
            throw new IllegalArgumentException("Invalid command: " + command + ", utility name - head");
        }

        Pattern utilityPattern = Pattern.compile("^head *(-n)? +(-?[1-9][0-9]*) (.+)$");
        Matcher matcher = utilityPattern.matcher(command);
        if (matcher.matches()) {
            if (matcher.group(1) == null)
                linesCount = - Integer.parseInt(matcher.group(2));
            else
                linesCount = Integer.parseInt(matcher.group(2));
            if (linesCount < 0)
                throw new IllegalArgumentException("Invalid command: " + command +
                        ", utility example: head [-n] 20 app.log");
            filePath = Paths.get(matcher.group(3));
            if (!Files.isReadable(filePath))
                throw new IllegalArgumentException("Invalid file: " + filePath);
        } else {
            throw new IllegalArgumentException("Invalid command: " + command +
                    ", utility example: head [-n] 20 app.log");
        }
    }

    private static List<String> getFirstLines() {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.limit(linesCount).toList();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
