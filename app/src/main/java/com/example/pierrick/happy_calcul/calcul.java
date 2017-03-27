package com.example.pierrick.happy_calcul;

/**
 * Created by studlerobin on 26/03/2017.
 */

public class calcul {

    private int gauche;
    private int droite;
    private int resultat;

    public calcul(){
        gauche = 0;
        droite = 0;
        resultat = 0;
    }

    public calcul(int _gauche, int _droite, int _resultat){
        gauche = _gauche;
        droite = _droite;
        resultat = _resultat;
    }


    public int getGauche() {
        return gauche;
    }

    public void setGauche(int gauche) {
        this.gauche = gauche;
    }

    public int getDroite() {
        return droite;
    }

    public void setDroite(int droite) {
        this.droite = droite;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }
}
