package com.example.aleksander.bartender;

/**
 * Created by Aleksander on 14.02.2017.
 */

public class Blandevann {
    private String navn;

    public Blandevann(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
