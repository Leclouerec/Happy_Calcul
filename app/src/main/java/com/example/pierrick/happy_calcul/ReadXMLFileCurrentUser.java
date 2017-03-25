package com.example.pierrick.happy_calcul;

import android.util.Log;

import org.w3c.dom.Attr;
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
 * Created by studlerobin on 11/03/2017.
 */

public class ReadXMLFileCurrentUser {


    private static InputStream is;
    private static File f;
    private static boolean isFile = false;

    public ReadXMLFileCurrentUser(InputStream _is) {
        is = _is;

        System.out.println("*************PROLOGUE************");

        read();

    }

    public ReadXMLFileCurrentUser(File _f) {
        f = _f;
        isFile = true;

        System.out.println("*************PROLOGUE************");

        read();

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

            System.out.println("repertoire courant " + System.getProperties().get("user.dir"));
            //final Document document= builder.parse(new File("/Users/studlerobin/Downloads/Happy_Calcul/app/src/main/assets/users.xml"));

            Document document = builder.newDocument();
            if(!(isFile)){
                document = builder.parse(is);
            }
            else{
                document = builder.parse(f);
            }



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

            Element profil = (Element) racine.getElementsByTagName("profil").item(0);

            //Affichage d'une personne
            System.out.println("\n*************User************");


            final String level = profil.getAttribute("level");
            System.out.println("level : " + level);

            final Element numLevel = (Element) profil.getElementsByTagName("numLevel").item(0);
            final Element pourcentage = (Element) profil.getElementsByTagName("pourcentage").item(0);
            final Element serie = (Element) profil.getElementsByTagName("serie").item(0);

            //Affichage du nom et du prénom
            System.out.println("numLevel : " + numLevel.getTextContent());
            System.out.println("poucentage : " + pourcentage.getTextContent());
            System.out.println("serie : " + serie.getTextContent());

            //modifications des éléments de l'utilisateur courrant
            connexion.current.setLevel(level);

            connexion.current.setPourcentage(Integer.parseInt(pourcentage.getTextContent()));
            if(connexion.firstConnexion){
                connexion.current.setSerie(Integer.parseInt(serie.getTextContent()));
                connexion.current.setNumLevel(Integer.parseInt(numLevel.getTextContent()));
            }




        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
