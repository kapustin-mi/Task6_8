import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch {
    private final int length;
    private final int quantity;
    private final List<String> text;

    public WordSearch(int length, int quantity, List<String> text) {
        this.length = length;
        this.quantity = quantity;
        this.text = text;
    }

    public List<String> findWords() {
        if (text == null || quantity <= 0 || length <= 0) {
            return null;
        } else {
            List<String> allWords = selectWordsFromTextWithGivenLength();
            if (allWords.size() == 0) {
                return null;
            }
            List<String> returnWords = new ArrayList<>();
            SimpleHashMap<String, Integer> hashMap = new SimpleHashMap<>(allWords.size());

            for (String word : allWords) {
                if (hashMap.containsKey(word)) {
                    hashMap.put(word, hashMap.get(word) + 1);
                } else {
                    hashMap.put(word, 1);
                }
            }

            int maxQuantity = 0;
            while (returnWords.size() < quantity) {
                maxQuantity = addNextWords(returnWords, hashMap, maxQuantity);
                if (maxQuantity == 1) {
                    break;
                }
            }

            return returnWords;
        }
    }

    private int addNextWords(List<String> returnWords, SimpleHashMap<String, Integer> hashMap, int maxQuantity) {
        List<String> addedWords = new ArrayList<>();
        int quantity = 0;

        if (maxQuantity == 0) {
            for (String key : hashMap.keySet()) {
                quantity = checkKeyAndQuantity(hashMap, key, quantity, addedWords);
            }
            returnWords.addAll(addedWords);
        } else {
            for (String key : hashMap.keySet()) {
                if (hashMap.get(key) < maxQuantity) {
                    quantity = checkKeyAndQuantity(hashMap, key, quantity, addedWords);
                }
            }
            returnWords.addAll(addedWords);
        }

        return quantity;
    }

    private int checkKeyAndQuantity(SimpleHashMap<String, Integer> hashMap, String key, int quantity, List<String> addedWords) {
        if (hashMap.get(key) > quantity) {
            quantity = hashMap.get(key);

            addedWords.clear();
            addedWords.add(key);
        } else if (hashMap.get(key) == quantity) {
            addedWords.add(key);
        }

        return quantity;
    }

    private List<String> selectWordsFromTextWithGivenLength() {
        List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zа-я0-9ё]+");
        Matcher matcher;

        for (String line : text) {
            matcher = pattern.matcher(line.toLowerCase());

            while (matcher.find()) {
                if (matcher.group().length() == length) {
                    words.add(matcher.group());
                }
            }
        }

        return words;
    }
}
