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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by studlerobin on 25/03/2017.
 */

public class WriteXMLFileUsers {

    private static File f;

    public WriteXMLFileUsers(File _f){
        f = _f;


        System.out.println("*************PROLOGUE************");

    }

    public static void newUsers(user _user){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            // user elements
            Element user = doc.createElement("user");
            rootElement.appendChild(user);


            //name elements
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(_user.getName()));
            user.appendChild(name);

            // mdp elements
            Element mdp = doc.createElement("mdp");
            mdp.appendChild(doc.createTextNode(_user.getMdp()));
            user.appendChild(mdp);

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


    public static void addUser(user _user){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // root elements
            try{
                doc = docBuilder.parse(f);
            }
            catch (final SAXException e) {
                e.printStackTrace();
            }
            catch (final IOException e) {
                e.printStackTrace();
            }


            Node users= doc.getFirstChild();

            // user elements
            Element user = doc.createElement("user");
            users.appendChild(user);


            //name elements
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(_user.getName()));
            user.appendChild(name);

            // mdp elements
            Element mdp = doc.createElement("mdp");
            mdp.appendChild(doc.createTextNode(_user.getMdp()));
            user.appendChild(mdp);


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

    //supprime l'utilisateur du fichier des utilisateurs et son fichier profil
    public static void delete(String _name) {

        List<user> users = new ArrayList<user>();

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

                    final Element name = (Element) personne.getElementsByTagName("name").item(0);
                    final Element mdp = (Element) personne.getElementsByTagName("mdp").item(0);

                    //Affichage du nom et du prénom
                    System.out.println("name : " + name.getTextContent());
                    System.out.println("mdp : " + mdp.getTextContent());


                    if((name.getTextContent().toString().equals(_name))){
                        Log.e("ALLO"," ");
                        personne.getParentNode().removeChild(personne);
                    }

                    final user utilisateur= new user(name.getTextContent().toString(), mdp.getTextContent().toString());
                    users.add(utilisateur);

                }
            }
            for (Iterator<user> i = users.iterator(); i.hasNext();
                    ) {
                user item = i.next();
                System.out.println("mot de passe "+ item.getMdp() + " name : " + item.getName());
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



    //uniquement pour tester si l'ecriture c'est bien faite
    public static void read() {

        List<user> users = new ArrayList<user>();

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

                    final Element name = (Element) personne.getElementsByTagName("name").item(0);
                    final Element mdp = (Element) personne.getElementsByTagName("mdp").item(0);

                    //Affichage du nom et du prénom
                    System.out.println("name : " + name.getTextContent());
                    System.out.println("mdp : " + mdp.getTextContent());


                    if((name.getTextContent().toString().equals("test2"))&&(mdp.getTextContent().toString().equals("test"))){
                        Log.e("ALLO"," ");
                        personne.getParentNode().removeChild(personne);
                    }

                    final user utilisateur= new user(name.getTextContent().toString(), mdp.getTextContent().toString());
                    users.add(utilisateur);

                }
            }
            for (Iterator<user> i = users.iterator(); i.hasNext();
                    ) {
                user item = i.next();
                System.out.println("mot de passe "+ item.getMdp() + " name : " + item.getName());
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
