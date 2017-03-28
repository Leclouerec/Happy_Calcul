package com.example.pierrick.happy_calcul;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * Created by studlerobin on 26/03/2017.
 */

public class WriteXMLFileHistorique {
    private static File f;

    public WriteXMLFileHistorique(File _f){
        f = _f;

        System.out.println("*************PROLOGUE************");

    }


    public static void newHistorique(List<calcul> calculs){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("historique");
            doc.appendChild(rootElement);

            for(int i = 0; i<20;i++){

                // calcul elements
                Element calcul = doc.createElement("calcul");
                rootElement.appendChild(calcul);



                //premierDigit elements
                Element premierDigit = doc.createElement("premierDigit");
                premierDigit.appendChild(doc.createTextNode(String.valueOf(calculs.get(i).getGauche())));
                calcul.appendChild(premierDigit);

                // deuxiemeDigit elements
                Element deuxiemeDigit = doc.createElement("deuxiemeDigit");
                deuxiemeDigit.appendChild(doc.createTextNode(String.valueOf(calculs.get(i).getDroite())));
                calcul.appendChild(deuxiemeDigit);

                // resultat elements
                Element resultat = doc.createElement("resultat");
                resultat.appendChild(doc.createTextNode(String.valueOf(calculs.get(i).getResultat())));
                calcul.appendChild(resultat);

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            /*final File test = new File ("file:///android_asset/qoqoqo");
            test.mkdirs();*/
            StreamResult result = new StreamResult(f);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }





    public static void read() {

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

    }



    public static void addHistoriqueNew(File fi,String jour, String heure, String res){
            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("historique");
                doc.appendChild(rootElement);

                // user elements
                Element partie = doc.createElement("partie");
                rootElement.appendChild(partie);

                partie.setAttribute("date",jour);
                partie.setAttribute("heure",heure);
                partie.setAttribute("res",res);


                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
            /*final File test = new File ("file:///android_asset/qoqoqo");
            test.mkdirs();*/
                StreamResult result = new StreamResult(fi);

                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println("File saved!");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }

        }


    public static void addHistorique(File fi,String jour, String heure, String res){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // root elements
            try{
                doc = docBuilder.parse(fi);
            }
            catch (final SAXException e) {
                e.printStackTrace();
            }
            catch (final IOException e) {
                e.printStackTrace();
            }


            Node users= doc.getFirstChild();

            // user elements
            Element partie = doc.createElement("partie");
            users.appendChild(partie);


            partie.setAttribute("date",jour);
            partie.setAttribute("heure",heure);
            partie.setAttribute("res",res);



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(f);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }


    //uniquement pour tester si l'ecriture c'est bien faite
    public static void read2() {

        List<String> operation = new ArrayList<String>();

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

                    System.out.println("\n*************User************");


                    //Affichage du nom et du prénom
                    final String date = personne.getAttribute("date");
                    final String heure = personne.getAttribute("heure");
                    final String res = personne.getAttribute("res");
                    System.out.println("date : " + date);
                    System.out.println("heure : " + heure);
                    System.out.println("res : " + res);




                    operation.add(date+heure+res);

                }
            }
            for (Iterator<String> i = operation.iterator(); i.hasNext();
                    ) {
                String item = i.next();
                System.out.println("mot de passe "+ item);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(f);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);


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
        catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

}
