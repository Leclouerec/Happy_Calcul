package com.example.pierrick.happy_calcul;

/**
 * Created by studlerobin on 27/03/2017.
 */

public class partie {

    private String jour;
    private String heure;
    private String res;

    public partie(String jour, String heure, String res) {
        this.jour = jour;
        this.heure = heure;
        this.res = res;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
