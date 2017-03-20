package com.example.pierrick.happy_calcul;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by studlerobin on 19/03/2017.
 */

public class ReadXMLFileProfil {

    private static InputStream is;
    private static profil profil1 = new profil();
    private static File f;
    private static int num;

    public ReadXMLFileProfil(InputStream _is, int _num){
        is =_is;
        num = _num;


        read();

    }

    public profil getProfil1(){
        return profil1;
    }

    public static void read() {

        /*
         * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
         */
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            /*
             * Etape 2 : création d'un parseur
             */
            final DocumentBuilder builder = factory.newDocumentBuilder();

        /*
         * Etape 3 : création d'un Document
         *
         *
         */

            System.out.println("repertoire courant " +System.getProperties().get("user.dir") );
            //final Document document= builder.parse(new File("/Users/studlerobin/Downloads/Happy_Calcul/app/src/main/assets/users.xml"));



            final Document document = builder.parse(is);
            //final Document document = builder.parse(f);


            //Affiche du prologue
            System.out.println("*************PROLOGUE************");
            System.out.println("version : " + document.getXmlVersion());
            System.out.println("encodage : " + document.getXmlEncoding());
            System.out.println("standalone : " + document.getXmlStandalone());

        /*
         * Etape 4 : récupération de l'Element racine
         */
            final Element racine = document.getDocumentElement();


            //Affichage de l'élément racine
            System.out.println("\n*************RACINE************");
            System.out.println(racine.getNodeName());

        /*
         * Etape 5 : récupération des personnes
         */
            final NodeList racineNoeuds = racine.getChildNodes();
            final int nbRacineNoeuds = racineNoeuds.getLength();

            for (int i = 0; i<nbRacineNoeuds; i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element profil = (Element) racineNoeuds.item(i);



                    /*try {

                        Log.e("je suis la"," ta mere");

                        Element test = (Element) document.createElement("bibliotheque");
                        test.appendChild(document.createTextNode("allez"));

                        personne.appendChild(test);


                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(document);

                        //String theString = istoString();

                        //Log.e("le string", " chemin " + theString);

                        StreamResult result = new StreamResult(new File("stp.xml"));

                        transformer.transform(source, result);

                        System.out.println("Done");

                    } catch (Exception e) {

                        e.printStackTrace();

                    }*/


                    //test();



                    //Affichage d'une personne
                    System.out.println("\n*************User************");
                    //System.out.println("nom : " + racine.getAttribute("nom"));

                /*
             * Etape 6 : récupération du nom et du prénom
             */
                    final Element premierDigit = (Element) profil.getElementsByTagName("premierDigit").item(0);
                    final Element deuxiemeDigit = (Element) profil.getElementsByTagName("secondDigit").item(0);
                    final Element serie = (Element) profil.getElementsByTagName("serie").item(0);
                    final Element pourcentageSuivant = (Element) profil.getElementsByTagName("pourcentageSuivant").item(0);
                    final Element pourcentage2x2 = (Element) profil.getElementsByTagName("pourcentage2X2").item(0);
                    //final Element mdp = (Element) personne.getElementsByTagName("mdp").item(0);

                    //Affichage du nom et du prénom
                    System.out.println("level : " + premierDigit.getTextContent());
                    //System.out.println("mdp : " + mdp.getTextContent());
                    int temp = Integer.parseInt(profil.getAttribute("num"));
                    int firstDigit = Integer.parseInt(premierDigit.getTextContent());
                    int secondDigit = Integer.parseInt(deuxiemeDigit.getTextContent());
                    int _serie = Integer.parseInt(serie.getTextContent());
                    int _pourcentageSuivant = Integer.parseInt(pourcentageSuivant.getTextContent());
                    int _pourcentage2X2 = Integer.parseInt(pourcentage2x2.getTextContent());

                    if(temp == num)
                        profil1 = new profil(num,firstDigit,secondDigit,_serie,_pourcentageSuivant,_pourcentage2X2);

                    //final profil prof= new profil(name.getTextContent(), mdp.getTextContent());
            /*
             * Etape 7 : récupération des numéros de téléphone
             */
                    /*final NodeList telephones = racine.getElementsByTagName("level");
                    final int nbTelephonesElements = telephones.getLength();

                    for(int j = 0; j<nbTelephonesElements; j++) {
                        final Element telephone = (Element) telephones.item(j);

                        //Affichage du téléphone
                        System.out.println(telephone.getAttribute("type") + " : " + telephone.getTextContent());
                    }*/
                }
            }


            /*Node test = document.createElement("test");
            test.setTextContent("allo");
            racine.appendChild(test);*/
        }


        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }


    }


}
