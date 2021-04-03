package ru.job4j.it;

import javax.swing.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (column < data[row].length) {
            return true;
        }
        if ((row + 1) < data.length) {
            if (data[row + 1].length > 0) {
                column = 0;
                row++;
                return true;
            } else {
                for (int i = row + 1; i < data.length; i++) {
                    if (data[i].length > 0) {
                        column = 0;
                        row = i;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}