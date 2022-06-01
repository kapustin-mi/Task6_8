import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<String, Integer> hashMap = new SimpleHashMap<>();

    @Test
    public void put() {
        hashMap.put("Я", 1);
        hashMap.put("было", 2);
        hashMap.put("хотел", 3);
        hashMap.put("все", 4);
        hashMap.put("это", 5);
        hashMap.put("стихами", 6);
        hashMap.put("изобразить", 7);
        String expected = "{это=5, хотел=3, было=2, все=4, стихами=6, изобразить=7, Я=1}";
        Assert.assertEquals(hashMap.toString(), expected);
    }

    @Test
    public void size() {
        hashMap.put("Я", 1);
        hashMap.put("было", 2);
        hashMap.put("хотел", 3);
        hashMap.put("все", 4);
        hashMap.put("это", 5);
        hashMap.put("стихами", 6);
        hashMap.put("изобразить", 7);
        Assert.assertEquals(hashMap.size(), 7);
        hashMap.clear();
        Assert.assertEquals(hashMap.size(), 0);
    }

    @Test
    public void isEmpty() {
        assertTrue(hashMap.isEmpty());
        hashMap.put("Я", 1);
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void containsKey() {
        assertFalse(hashMap.containsKey("Я"));
        hashMap.put("Я", 1);
        assertTrue(hashMap.containsKey("Я"));
    }

    @Test
    public void containsValue() {
        assertFalse(hashMap.containsValue(1));
        hashMap.put("Я", 1);
        assertTrue(hashMap.containsValue(1));
    }

    @Test
    public void get() {
        hashMap.put("Я", 1);
        hashMap.put("было", 2);
        hashMap.put("хотел", 3);
        hashMap.put("все", 4);
        Object expected = 1;
        Assert.assertEquals(hashMap.get("Я"), expected);
        expected = null;
        Assert.assertEquals(hashMap.get(""), expected);
    }

    @Test
    public void remove() {
        hashMap.put("Я", 1);
        hashMap.put("было", 2);
        hashMap.put("хотел", 3);
        hashMap.put("все", 4);
        hashMap.remove("было");
        hashMap.remove("стиами");
        String expected = "{хотел=3, все=4, Я=1}";
        Assert.assertEquals(expected, hashMap.toString());
        Assert.assertEquals(3, hashMap.size());
    }

    @Test
    public void keySet() {
        hashMap.put("Я", 1);
        hashMap.put("было", 2);
        hashMap.put("хотел", 3);
        hashMap.put("все", 4);
        hashMap.put("это", 5);
        hashMap.put("стихами", 6);
        hashMap.put("изобразить", 7);
        String expected = "[было, все, стихами, хотел, изобразить, это, Я]";
        Assert.assertEquals(expected, hashMap.keySet().toString());
    }

    @Test
    public void values() {
        hashMap.put("Я", 1);
        hashMap.put("было", 2);
        hashMap.put("хотел", 3);
        hashMap.put("все", 4);
        hashMap.put("это", 5);
        hashMap.put("стихами", 6);
        hashMap.put("изобразить", 7);
        List<Integer> expected = Arrays.asList(5, 3, 2, 4, 6, 7, 1);
        Assert.assertEquals(expected, hashMap.values());
    }
}