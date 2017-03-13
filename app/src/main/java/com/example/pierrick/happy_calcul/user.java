package com.example.pierrick.happy_calcul;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by studlerobin on 10/03/2017.
 */

public class user {

    private String name;
    private String mdp;


    public user(){

        name = "";
        mdp = "";

    }

    public user(String _name, String _mdp){
        name = _name;
        mdp = _mdp;

    }

    public String getName(){
        return name;
    }

    public String getMdp(){
        return mdp;
    }

    public void setName(String _name){
        name = _name;
    }

    public void setMdp(String _mdp){
        mdp = _mdp;
    }


}
