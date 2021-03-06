import java.util.*;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.frequency;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WordFrequencyGame {
    public String getResult(String inputStr){
            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(inputStr);
                sortWordInfo(wordInfoList);
                return joinWordInfoList(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
    }


    private String joinWordInfoList(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(joining("\n"));
    }

    private void sortWordInfo(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private List<WordInfo> calculateWordFrequency(String inputStr) {
        List<String> words = asList(inputStr.split("\\s+"));
        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream()
                .map(word -> new WordInfo(word, frequency(words, word)))
                .collect(toList());
    }
}
