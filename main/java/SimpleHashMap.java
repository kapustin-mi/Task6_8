import java.util.*;

public class SimpleHashMap<K, V> implements Map<K, V> {
    private final int DEFAULT_CAPACITY = 100;
    private MyEntry<K, V>[] table;
    private int size;

    public SimpleHashMap(int capacity) {
        table = new MyEntry[capacity];
    }

    public SimpleHashMap() {
        table = new MyEntry[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int location = getLocation(Math.abs(key.hashCode()));

        if (location < table.length) {
            MyEntry<K, V> entry = table[location];
            while (entry != null) {
                if (entry.getKey().hashCode() == key.hashCode()) {
                    return true;
                }
                entry = entry.getNext();
            }
        }

        return false;
    }

    private int getLocation(int hashCode) {
        return hashCode % table.length;
    }

    @Override
    public boolean containsValue(Object value) {
        for (V val : values()) {
            if (value.equals(val)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        V result = null;
        MyEntry<K, V> entry = null;
        int location = getLocation(Math.abs(key.hashCode()));

        if (location < table.length) {
            entry = table[location];
        }

        while (entry != null) {
            if (entry.getKey().hashCode() == key.hashCode()) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }

        return result;
    }

    @Override
    public V put(K key, V value) {
        V result = null;
        int location = getLocation(Math.abs(key.hashCode()));
        if (location < table.length) {
            MyEntry<K, V> entry = table[location];
            if (entry != null) {
                size++;
                return addLink(entry, key, value);
            } else {
                MyEntry<K, V> newEntry = new MyEntry<>(key, value);
                table[location] = newEntry;
                size++;
            }
        }

        return result;
    }

    private V addLink(MyEntry<K, V> entry, K key, V value) {
        while (entry != null) {
            if (entry.getKey().hashCode() == key.hashCode()) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }

            if (entry.getNext() == null) {
                entry.setNext(new MyEntry<>(key, value));
                return value;
            }
            entry = entry.getNext();
        }

        return null;
    }

    @Override
    public V remove(Object key) {
        V result = null;
        int location = getLocation(Math.abs(key.hashCode()));
        if (location < table.length) {
            if (table[location] != null) {
                if (table[location].getKey().hashCode() == key.hashCode()) {
                    result = table[location].getValue();
                    if (table[location].getNext() != null) {
                        table[location] = table[location].getNext();
                    } else {
                        MyEntry<K, V> entry = null;
                        table[location] = entry;
                    }
                } else {
                    MyEntry<K, V> entry = table[location];
                    while (entry != null) {
                        if (entry.getNext().getKey().hashCode() == key.hashCode()) {
                            result = entry.getNext().getValue();
                            entry.setNext(entry.getNext().getNext());
                        }
                        entry = entry.getNext();
                    }
                }
                size--;
            }
        }

        return result;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
            K key = e.getKey();
            V value = e.getValue();
            put(key, value);
        }
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            size = 0;
            Arrays.fill(table, null);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (MyEntry<K, V> myEntry : table) {
            while (myEntry != null) {
                keySet.add(myEntry.getKey());
                myEntry = myEntry.getNext();
            }
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (MyEntry<K, V> myEntry : table) {
            while (myEntry != null) {
                values.add(myEntry.getValue());
                myEntry = myEntry.getNext();
            }
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> myEntrySet = new HashSet<>();
        for (MyEntry<K, V> myEntry : table) {
            if (myEntry != null) {
                myEntrySet.add(myEntry);
            }
        }
        return myEntrySet;
    }

    private static class MyEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        private MyEntry<K, V> next;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public MyEntry<K, V> getNext() {
            return next;
        }

        public void setNext(MyEntry<K, V> next) {
            this.next = next;
        }

        public String toString() {
            return key + "=" + value;
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        if (entrySet() != null) {
            for (MyEntry<K, V> e : table) {
                while (e != null) {
                    sb.append(e).append(", ");
                    e = e.getNext();
                }
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
}
