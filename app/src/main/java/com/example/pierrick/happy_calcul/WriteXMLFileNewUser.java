package com.example.pierrick.happy_calcul;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

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

public class WriteXMLFileNewUser {

    private static File f;

    public WriteXMLFileNewUser(File _f){
        f = _f;

        System.out.println("*************PROLOGUE************");

    }


    public static void newUser(user _user){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("compte");
            doc.appendChild(rootElement);

            Element name = doc.createElement("nom");
            name.appendChild(doc.createTextNode(_user.getName()));
            rootElement.appendChild(name);

            Element mdp = doc.createElement("mdp");
            mdp.appendChild(doc.createTextNode(_user.getMdp()));
            rootElement.appendChild(mdp);

            // user elements
            Element profil = doc.createElement("profil");
            rootElement.appendChild(profil);

            profil.setAttribute("level",_user.getLevel());


            //name elements
            Element numLevel = doc.createElement("numLevel");
            numLevel.appendChild(doc.createTextNode("1"));
            profil.appendChild(numLevel);

            // mdp elements
            Element serie = doc.createElement("serie");
            serie.appendChild(doc.createTextNode("0"));
            profil.appendChild(serie);

            // mdp elements
            Element pourcentage = doc.createElement("pourcentage");
            pourcentage.appendChild(doc.createTextNode("0"));
            profil.appendChild(pourcentage);

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

            Element profil = (Element) racine.getElementsByTagName("profil").item(0);

            //Affichage d'une personne
            System.out.println("\n*************User************");


            final String level = profil.getAttribute("level");
            System.out.println("level : " + level);

            final Element numLevel = (Element) profil.getElementsByTagName("numLevel").item(0);
            final Element pourcentage = (Element) profil.getElementsByTagName("pourcentage").item(0);
            final Element serie = (Element) profil.getElementsByTagName("serie").item(0);
            final Element nom = (Element) racine.getElementsByTagName("nom").item(0);
            final Element mdp = (Element) racine.getElementsByTagName("mdp").item(0);

            //Affichage du nom et du prénom
            System.out.println("numLevel : " + numLevel.getTextContent());
            System.out.println("poucentage : " + pourcentage.getTextContent());
            System.out.println("serie : " + serie.getTextContent());
            System.out.println("serie : " + nom.getTextContent());
            System.out.println("serie : " + mdp.getTextContent());





        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
