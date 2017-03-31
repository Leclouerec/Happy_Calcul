package com.example.pierrick.happy_calcul;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by studlerobin on 12/03/2017.
 */

public class redondance {

    public int[][] tableauCalcul;
    private int nbMaxTables;

    public redondance(int _nbMaxTables){
        nbMaxTables = _nbMaxTables;
        tableauCalcul = new int[nbMaxTables + 1][nbMaxTables+1];
        initialisation();
    }

    public void initialisation(){

        for(int i = 0 ; i<=nbMaxTables ; i++)
            for(int j = 0 ; j <= nbMaxTables ; j++)
                tableauCalcul[i][j] = 0;

    }

    public void ajouter(int premierDigit, int secondDigit){
        tableauCalcul[premierDigit][secondDigit]++;
    }

    public boolean aAjouter(int premierDigit, int secondDigit, int[][] tableau){

        int valeurCalcul = valeurCalcul(premierDigit,secondDigit, tableau);
        int valeurMax = valeurMax(tableau);

        boolean toutEgal = toutEgal(tableau);
        if(toutEgal)
            return true;


        return (valeurCalcul < valeurMax);
    }

    public int valeurCalcul(int premierDigit, int secondDigit, int[][] tableau ){
        return tableau[premierDigit][secondDigit];
    }

    public int valeurMax(int[][] tableau){
        int valeurMax = 0;

        for(int i = 1 ; i <=nbMaxTables ; i++)
            for(int j = i; j<= nbMaxTables; j++)
                if(tableau[i][j] > valeurMax)
                    valeurMax = tableau[i][j];

        return valeurMax;
    }

    public boolean toutEgal(int[][] tableau){
        int valeurMax = tableau[1][1];
        int compteur = 0;
        for(int i = 1 ; i <=nbMaxTables ; i++)
            for(int j = i; j<= nbMaxTables; j++)
                if(tableau[i][j] == valeurMax)
                    compteur ++;

        int nbLignes = (int)(0.5 * nbMaxTables * (nbMaxTables+1));

        return(compteur == nbLignes);

    }


    public int ligneALire(int digitMoins, int digitPlus){

        int soustraire = soustraire(digitMoins);

        return (digitMoins * nbMaxTables) - (nbMaxTables - digitPlus) - soustraire;

    }

    //on ne crée pas de doublons dans le fichier (2x1 et 1x2 existe seulement sous la forme 1x2)
    //on a donc moitié moins de lignes, par exemple 2x1 n'existe pas et donc 2x2 est à la ligne 10
    //il faut donc savoir sur quelle ligne se trouve le calcul, il faut supprimer les calculs qui ne sont pas inscrits
    public int soustraire(int i){
        int temp = 0;
        int j = 1;
        while(j < i){
            temp+=j;
            j++;
        }

        return temp;

    }


}
