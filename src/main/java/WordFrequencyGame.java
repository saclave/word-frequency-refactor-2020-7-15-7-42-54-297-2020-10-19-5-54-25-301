import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private List<WordFrequency> wordFrequencyList = new ArrayList<>();
    List<WordFrequency> distinctWordList = new ArrayList<>();

    public String getResult(String inputStr){

        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split("\\s+");

                wordFrequencyList = getWordFrequencyList(words);

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordInfoMap =getListMap(wordFrequencyList);

                List<WordFrequency> distinctWordList = getDistinctWordList(wordInfoMap);

                wordFrequencyList = distinctWordList;

                wordFrequencyList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String resultString = wordFrequency.getWord() + " " +wordFrequency.getWordCount();
                    joiner.add(resultString);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordFrequency> getDistinctWordList(Map<String, List<WordFrequency>> wordInfoMap) {
        for (Map.Entry<String, List<WordFrequency>> entry : wordInfoMap.entrySet()){
            WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
            distinctWordList.add(wordFrequency);
        }
        return distinctWordList;
    }

    private List<WordFrequency> getWordFrequencyList(String[] words) {
        for (String word : words) {
            WordFrequency wordFrequency = new WordFrequency(word, 1);
            wordFrequencyList.add(wordFrequency);
        }
        return wordFrequencyList;
    }


    private Map<String,List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())){
                ArrayList wordList = new ArrayList<>();
                wordList.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), wordList);
            }

            else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return wordFrequencyMap;
    }


}
