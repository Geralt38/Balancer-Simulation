package ru.isu.compmodels.imitation;

import java.util.Random;

public class SimpleRequest implements Request {
    private int units;

    public SimpleRequest() {
        Random r = new Random();
        // [1..5]
        setLoad(r.nextInt(5) + 1);
    }

    public SimpleRequest(int units) {
        setLoad(units);
    }

    @Override
    public int getLoad() {
        return units;
    }

    @Override
    public void setLoad(int units) {
        this.units = units;
    }
}
