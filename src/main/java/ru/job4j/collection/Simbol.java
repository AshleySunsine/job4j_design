package ru.job4j.collection;

import java.util.Objects;

public class Simbol {
    char simbol;

    public Simbol(char simbol) {
        this.simbol = simbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Simbol simbol1 = (Simbol) o;
        return Objects.equals(simbol, simbol1.simbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simbol);
    }
}
