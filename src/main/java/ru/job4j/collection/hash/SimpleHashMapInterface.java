package ru.job4j.collection.hash;

public interface SimpleHashMapInterface<K, V> {
    boolean insert(K key, V value);
    V get(K key);
    boolean delete(K key);
}
