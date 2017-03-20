package com.example.pierrick.happy_calcul;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by studlerobin on 10/03/2017.
 */

public class user {

    private String name;
    private String mdp;
    private String level;
    private int numLevel;
    private int serie;
    private int pourcentage;


    public user(){

        name = "";
        mdp = "";
        level = "";
        numLevel = 0;
        serie = 0;
        pourcentage = 0;


    }

    public user(String _name, String _mdp){
        name = _name;
        mdp = _mdp;

    }
    public user(String _name, String _mdp, String _level, int _numLevel, int _serie, int _pourcentage){
        name = _name;
        mdp = _mdp;
        level = _level;
        numLevel = _numLevel;
        serie = _serie;
        pourcentage = _pourcentage;

    }

    public String getName(){
        return name;
    }

    public String getMdp(){
        return mdp;
    }

    public String getLevel(){
        return level;
    }

    public int getNumLevel(){
        return numLevel;
    }

    public int getSerie(){
        return serie;
    }

    public int getPourcentage(){
        return pourcentage;
    }

    public void setName(String _name){
        name = _name;
    }

    public void setMdp(String _mdp){
        mdp = _mdp;
    }

    public void setLevel(String _level){
        level = _level;
    }

    public void setNumLevel(int _numLevel){
        numLevel = _numLevel;
    }

    public void setSerie(int _serie){
        serie = _serie;
    }

    public void setPourcentage(int _pourcentage){
        pourcentage = _pourcentage;
    }


}
