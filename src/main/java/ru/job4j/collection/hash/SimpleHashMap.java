package ru.job4j.collection.hash;

import ru.job4j.collection.list.SimpleLinkedList;

import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable {
    int defaultSize = 16;
    private Node<K, V>[] table = new Node[defaultSize];
    private int size;
    int threshold;
    final float loadFactor = 0.75f;
    int modCount = 0;

    private final int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h  ^ (h >>> 16);
    }

    private final int bucket(Node<K, V>[] table, int hash) {
        return (table.length - 1) & hash;
    }

    private void resize() {
        Node<K, V>[] newTable = new Node[defaultSize * 2];
        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;
        defaultSize = newTable.length;
        System.out.println("Resize! New size = " + defaultSize);
    }

    public boolean insert(K key, V value) {
        int hash;
        int bucket;
        Node<K, V> node;
        hash = hash(key);
        bucket = bucket(table, hash);
        node = new Node<K, V>(hash, key, value, null);
        if ((table[bucket] != null) && (table[bucket].key != key)) {
            System.out.println("DOUBLE!");
            return false;
        }
        table[bucket] = node;
        if (size > (table.length * loadFactor)) {
            resize();
        }
        size++;
        modCount++;
        return true;
    }

    public boolean delete(K key) {
        int bucket = bucket(table, hash(key));
        table[bucket] = null;
        size--;
        modCount++;
        return false;
    }

    public V get(K key) {
        int bucket = (table.length - 1) & hash(key);
        return table[bucket].value;
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
        private int hash;
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
