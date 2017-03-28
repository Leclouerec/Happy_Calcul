package com.example.pierrick.happy_calcul;

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
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by studlerobin on 27/03/2017.
 */

public class ReadXMLFileHistorique {

    private static File f;

    public ReadXMLFileHistorique(File _f){
        f= _f;

    }


    public static List<calcul> read() {

        List<calcul> calculs = new ArrayList<calcul>();

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

            //final Document document = builder.parse(is);
            final Document document = builder.parse(f);


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
                    final Element personne = (Element) racineNoeuds.item(i);

                    System.out.println("\n*************calcul************");

                    final Element gauche = (Element) personne.getElementsByTagName("premierDigit").item(0);
                    final Element droite = (Element) personne.getElementsByTagName("deuxiemeDigit").item(0);
                    final Element res = (Element) personne.getElementsByTagName("resultat").item(0);

                    //Affichage du nom et du prénom
                    //System.out.println("name : " + gauche.getTextContent());
                    //System.out.println("mdp : " + droite.getTextContent());


                    final calcul cal= new calcul(Integer.parseInt(gauche.getTextContent().toString()), Integer.parseInt(droite.getTextContent().toString()),Integer.parseInt(res.getTextContent().toString()));
                    calculs.add(cal);

                }
            }
            for (Iterator<calcul> i = calculs.iterator(); i.hasNext();
                    ) {
                calcul item = i.next();
                System.out.println("gauche : "+ item.getGauche() + " ,droite : " + item.getDroite() +",resultat : " + item.getResultat());
            }


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

        return calculs;

    }

}
