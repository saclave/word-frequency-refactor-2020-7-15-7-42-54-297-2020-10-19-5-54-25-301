import java.util.*;

public class WordFrequencyGame {
    private static final String WHITE_SPACES = "\\s";
    private StringJoiner joiner = new StringJoiner("\n");
    private List<WordInfo> distinctWordList = new ArrayList<>();

    public String getResult(String inputStr){

        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {

            try {
                List<WordInfo> wordFrequencyList = calculateWordFrequency(inputStr);

                wordFrequencyList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                joiner = joinWords(wordFrequencyList);

                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateWordFrequency(String inputStr) {
        String[] words = inputStr.split(WHITE_SPACES);
        List<WordInfo> wordFrequencyList = new ArrayList<>();

        return new HashSet<>(Arrays.asList(words).stream()
                .map(word -> new WordInfo)
    }

    private StringJoiner joinWords(List<WordInfo> wordFrequencyList) {
        for (WordInfo wordFrequency : wordFrequencyList) {
            String resultString = wordFrequency.getWord() + " " +wordFrequency.getWordCount();
            joiner.add(resultString);
        }
        return joiner;
    }

    private List<WordInfo> getDistinctWordList(Map<String, List<WordInfo>> wordInfoMap) {
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
            WordInfo wordFrequency = new WordInfo(entry.getKey(), entry.getValue().size());
            distinctWordList.add(wordFrequency);
        }
        return distinctWordList;
    }

    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordFrequencyList) {
        Map<String, List<WordInfo>> wordFrequencyMap = new HashMap<>();
        for (WordInfo wordFrequency : wordFrequencyList){
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())){
                ArrayList wordList = new ArrayList<>();
                wordList.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), wordList);
            } else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }

        return wordFrequencyMap;
    }


}
