package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.graphics.Path;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static java.lang.System.in;

/**
 * Created by studlerobin on 10/03/2017.
 */

public class ReadXMLFileUsers {

    public static List<user> users = new ArrayList<user>();
    private static InputStream is;
    private int pos = -1;
    private static File f;
    private static boolean docum = false;

    public ReadXMLFileUsers(InputStream _is){
        users = new ArrayList<user>();
        is =_is;

        System.out.println("*************PROLOGUE************");

        read();

    }

    public ReadXMLFileUsers(File _f){
        users = new ArrayList<user>();
        f = _f;
        docum = true;


        System.out.println("*************PROLOGUE************");



        read();

    }

    public List<user> getUsers() {
        return users;
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

            Document document = builder.newDocument();
            if(!(docum)){
                document = builder.parse(is);
            }else{
                document = builder.parse(f);
            }

            //


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




                    //Affichage d'une personne
                    System.out.println("\n*************User************");
                    //System.out.println("nom : " + racine.getAttribute("nom"));

                /*
             * Etape 6 : récupération du nom et du prénom
             */
                    final Element name = (Element) personne.getElementsByTagName("name").item(0);
                    final Element mdp = (Element) personne.getElementsByTagName("mdp").item(0);

                    //Affichage du nom et du prénom
                    System.out.println("name : " + name.getTextContent());
                    System.out.println("mdp : " + mdp.getTextContent());

                    final user utilisateur= new user(name.getTextContent(), mdp.getTextContent());
                    users.add(utilisateur);


                }
            }
            for (Iterator<user> i = users.iterator(); i.hasNext();
                    ) {
                user item = i.next();
                System.out.println("mot de passe "+ item.getMdp() + " name : " + item.getName());
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


    }

    public boolean estPresent(String _pseudo, String _mdp){
        pos = 0;

        for (Iterator<user> i = users.iterator(); i.hasNext(); pos++
                ) {
            user item = i.next();
            if((item.getName().equals( _pseudo))&& (item.getMdp().equals(_mdp))){
                return true;
            }


        }
        pos = -1;
        return false;

    }

    public int getPosition(){
        return pos;
    }

    public static String istoString(){
        StringBuilder sb = null;
        try (
             BufferedReader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) { String str = null;  sb = new StringBuilder(8192); while ((str = r.readLine()) != null) { sb.append(str); } System.out.println("data from InputStream as String : " + sb.toString()); } catch (IOException ioe) { ioe.printStackTrace(); }
        return sb.toString();
    }


}
