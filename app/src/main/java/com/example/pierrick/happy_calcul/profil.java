package com.example.pierrick.happy_calcul;

/**
 * Created by studlerobin on 19/03/2017.
 */

public class profil {

    private int num;
    private int bornePremiereDigit;
    private int borneDeuxiemeDigit;
    private int serie;
    private int pourcentage;
    private int pourcentage2x2;

    public profil(){

        num = 0;
        bornePremiereDigit = 0;
        borneDeuxiemeDigit = 0;
        serie = 0;
        pourcentage = 0;
        pourcentage2x2 = 0;

    }

    public profil(int _num, int _bornePremiere, int _borneDeuxieme, int _serie, int _pourcentage, int _pourcentage2x2){
        num = _num;
        bornePremiereDigit = _bornePremiere;
        borneDeuxiemeDigit = _borneDeuxieme;
        serie = _serie;
        pourcentage = _pourcentage;
        pourcentage2x2 = _pourcentage2x2;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int _num){
        num = _num;
    }

    public int getBornePremiereDigit(){
        return bornePremiereDigit;
    }

    public void setBornePremiereDigit(int _bornePremiere){
        bornePremiereDigit = _bornePremiere;
    }

    public int getBorneDeuxiemeDigitDigit(){
        return borneDeuxiemeDigit;
    }

    public void setBorneDeuxiemeDigitDigit(int _borneDeuxieme){
        borneDeuxiemeDigit = _borneDeuxieme;
    }

    public int getSerie(){
        return serie;
    }

    public void setSerie(int _serie){
        serie = _serie;
    }

    public int getPourcentage(){
        return pourcentage;
    }

    public void setPourcentage(int _pourcentage){
        pourcentage = _pourcentage;
    }

    public int getPourcentage2x2(){
        return pourcentage2x2;
    }

    public void setPourcentage2x2(int _pourcentage2x2){
        pourcentage2x2 = _pourcentage2x2;
    }
}
