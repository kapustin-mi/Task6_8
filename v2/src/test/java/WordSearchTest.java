import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class WordSearchTest {
    private final List<String> text;
    private final int length;
    private final int quantity;
    private final List<String> words;

    public WordSearchTest(List<String> text, int length, int quantity, List<String> words) {
        this.text = text;
        this.length = length;
        this.quantity = quantity;
        this.words = words;
    }

    @Parameterized.Parameters
    public static List<Object[]> cases() throws FileNotFoundException {
        return Arrays.asList(new Object[][]{
                {Utils.readLinesFromFile(new File("src/test/resources/input01.txt")), 10, 150, Arrays.asList("полагалось",
                        "появлялись", "вообразить", "окружалась", "переодетым", "чудовищных", "отсиживать", "прекрасные",
                        "признаться", "разбойники", "останешься", "порядочным", "правильным", "загадочных", "привлекала",
                        "библейское", "служанками", "сладостный", "собственно", "снадобьями", "опрятность", "образцовое",
                        "прекрасной", "призраками")},
                {Utils.readLinesFromFile(new File("src/test/resources/input02.txt")), 9, 5, Arrays.asList("рассказал",
                        "засмеялся", "родителей", "разошелся", "обращался", "задыхался", "выжидающе", "поведение", "приходили",
                        "испуганно", "учениками", "отношения", "посмотрел", "испугался", "лесничего", "невинными", "скверными",
                        "раскаяния", "держаться", "случалось", "сочельник", "обжигался", "сводчатой", "считалось", "наверняка",
                        "фабричным", "прекрасно", "искусство", "проволоки", "приятелем", "одобрение", "получился", "найденное",
                        "положение", "наступали", "отверстие", "отозвался", "миленький", "кончилось", "мерзостью", "выпавшего",
                        "вернулись", "взрослого", "находился", "предметов", "сбрасывал", "свободные", "сделаться", "занавески",
                        "оборотами", "презирали", "умолкнуть", "мгновения")},
                {Utils.readLinesFromFile(new File("src/test/resources/input03.txt")), 5, 5, Arrays.asList("когда",
                        "денег", "может", "таким", "марки", "этого")},
                {Utils.readLinesFromFile(new File("src/test/resources/input04.txt")), 6, 4, Arrays.asList("ничего",
                        "только", "деньги", "сказал")},
                {Utils.readLinesFromFile(new File("src/test/resources/input05.txt")), 7, 8, Arrays.asList("однажды",
                        "который", "большей", "ученика", "сказать", "задание", "немного", "никогда", "демиана", "холоден",
                        "сегодня", "прежних")},
                {Utils.readLinesFromFile(new File("src/test/resources/input01.txt")), 0, 4, null},
                {null, 2, 4, null},
                {Utils.readLinesFromFile(new File("src/test/resources/input02.txt")), 2, 0, null},
                {Utils.readLinesFromFile(new File("src/test/resources/input03.txt")), 0, 0, null},
                {Utils.readLinesFromFile(new File("src/test/resources/input04.txt")), 150, 10, null}
        });
    }

    @Test
    public void findWords() {
        Assert.assertEquals(words, new WordSearch(length, quantity, text).findWords());
    }
}