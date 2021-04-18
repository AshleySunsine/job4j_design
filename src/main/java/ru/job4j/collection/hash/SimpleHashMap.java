package ru.job4j.collection.hash;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable {
    private int defaultSize = 16;
    private Node<K, V>[] table = new Node[defaultSize];
    private int size;
    private static final float LOAD_FACTOR = 0.75f;
    private int modCount = 0;

    private int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h  ^ (h >>> defaultSize);
    }

    private int bucket(Node<K, V>[] table, int hash) {
        return (table.length - 1) & hash;
    }

    private void resize() {
        defaultSize = defaultSize * 2;
        Node<K, V>[] newTable = new Node[defaultSize];
        for (Node<K, V> item : table) {
            if (item != null) {
                insertFor(newTable, item.key, item.value);
            }
        }
        table = newTable;
        System.out.println("Resize! New size = " + defaultSize);
    }

    private boolean insertFor(Node<K, V>[] table, K key, V value) {
        int bucket;
        Node<K, V> node;
        bucket = bucket(table, hash(key));
        node = new Node<K, V>(hash(key), key, value, null);
        if ((table[bucket] != null) && (table[bucket].key.equals(key))) {
            delete(key);
            insert(key, value);
            System.out.println("DOUBLE! Rewrite");
        }
        table[bucket] = node;
        if (size > (table.length * LOAD_FACTOR)) {
            resize();
        }
        return true;
    }

    public boolean insert(K key, V value) {
        if (insertFor(table, key, value)) {
            size++;
            modCount++;
            return true;
        }
        return false;
    }

    public boolean delete(K key) {
        int bucket = bucket(table, hash(key));
        if (table[bucket].key.equals(key)) {
            table[bucket] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        int bucket = (table.length - 1) & hash(key);
        return table[bucket].key.equals(key) ? table[bucket].value : null;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator() {
            final int expectedModCount = modCount;
            int cursor = 0;


            @Override
            public boolean hasNext() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                       cursor = i;
                       return true;
                    }
                }
                return false;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> rsl = table[cursor];
                cursor++;
                return rsl;
            }
        };
    }

    class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> table = new SimpleHashMap<>();
        System.out.println(table.insert("first", "1"));
        System.out.println(table.insert("sec", "2"));
        System.out.println(table.insert("t", "4"));
        System.out.println(table.insert("g", "3"));
        System.out.println(table.insert("g", "3"));
        System.out.println(table.insert("g", "3"));

        Iterator it = table.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println();
        SimpleHashMap<Integer, Integer> table2 = new SimpleHashMap<>();
        System.out.println(table2.defaultSize);
        for (int i = 0; i < 101; i++) {
            table2.insert(i, i + 2);
        }
    }
}
