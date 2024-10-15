package Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;


//3 вариант

// Напишите программу выдачи перекрестных ссылок, т.е. программу, которая печатает
// список всех слов документа и для каждого из этих слов печатает список номеров
// строк, в которые это слово входит.

public class WordCounter {
    public static Map<String, List<Integer>> getWordLinks(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);

        Pattern wordDelim = Pattern.compile("[.,!? \\n]+");
        Map<String, List<Integer>> wordEntries = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] words = wordDelim.split(lines.get(i));
            for (String word : words) {
                if (word.isEmpty())
                    continue;
                if (!wordEntries.containsKey(word))
                    wordEntries.put(word, new ArrayList<>());
                wordEntries.get(word).add(i + 1);
            }
        }
        return wordEntries;
    }
}