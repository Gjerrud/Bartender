package com.example.aleksander.bartender;

/**
 * Created by Aleksander on 14.02.2017.
 */

public class Sprit {
    private String type;
    private String cl_mengde;

    public Sprit(String type, String cl_mengde) {
        this.type = type;
        this.cl_mengde = cl_mengde;
    }

    public String getType() {
        return type;
    }

    public String getCl_mengde() {
        return cl_mengde;
    }

    @Override
    public String toString() {
        return type +
                " (" + cl_mengde + ")";
    }
}
