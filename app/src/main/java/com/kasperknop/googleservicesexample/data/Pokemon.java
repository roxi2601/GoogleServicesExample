package com.kasperknop.googleservicesexample.data;

public class Pokemon {
    private String name;
    private int number;

    public Pokemon(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Pokemon() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
