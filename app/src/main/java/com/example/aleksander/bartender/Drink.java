package com.example.aleksander.bartender;

/**
 * Created by Aleksander on 14.02.2017.
 */

public class Drink {
    private String navn;
    private Is is;
    private Sprit[] sprit;
    private Blandevann[] blandevann;
    private String[] ekstra;
    private Glass glass;
    private String instruksjoner;

    public Drink(String navn, Is is, Sprit[] sprit, Blandevann[] blandevann, String[] ekstra, Glass glass, String instruksjoner) {
        this.navn = navn;
        this.is = is;
        this.sprit = sprit;
        this.blandevann = blandevann;
        this.ekstra = ekstra;
        this.glass = glass;
        this.instruksjoner = instruksjoner;
    }

    public String getNavn() {
        return navn;
    }

    public Is getIs() {
        return is;
    }

    public Sprit[] getSprit() {
        return sprit;
    }

    public Blandevann[] getBlandevann() {
        return blandevann;
    }

    public String[] getEkstra() {
        return ekstra;
    }

    public Glass getGlass() {
        return glass;
    }

    public String getInstruksjoner() {
        return instruksjoner;
    }
}
