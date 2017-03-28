package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class resultat extends AppCompatActivity {

    public static String resGauche = "";
    public static String resDroite = "";
    public static int resultat=0;
    public static boolean changementLevel = false;
    public static List<calcul> calculs = new ArrayList<calcul>();
    private WriteXMLFileHistorique fileHistorique;
    public String heure = "";
    public String jour = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);



        TextView gauche = (TextView) findViewById(R.id.textView_resultat_gauche);
        TextView droite = (TextView) findViewById(R.id.textView_resultat_droite);
        TextView res = (TextView) findViewById(R.id.textView_score_final);

        gauche.setText(Html.fromHtml(resGauche));
        droite.setText(Html.fromHtml(resDroite));
        Log.e("resultat " , " " + resultat );
        Log.e("serie " , " " + connexion.current.getSerie() );
        res.setText(resultat + " / 20");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        jour = dateFormat.format(date);
        heure = dateFormat2.format(date);


        File f = new File(this.getFilesDir(),jour + heure + ".xml");
        fileHistorique = new WriteXMLFileHistorique(f);
        fileHistorique.newHistorique(calculs);
        fileHistorique.read();


        File f2 = new File(this.getFilesDir(), connexion.current.getName() + "historique.xml");
        fileHistorique = new WriteXMLFileHistorique(f2);
        if(f2.exists()){
            fileHistorique = new WriteXMLFileHistorique(f2);
            fileHistorique.addHistorique(f2, jour, heure, String.valueOf(resultat));
            fileHistorique.read2();
            //f.delete();

        } else{
            fileHistorique = new WriteXMLFileHistorique(f2);
            fileHistorique.addHistoriqueNew(f2, jour, heure, String.valueOf(resultat));
            fileHistorique.read2();
            //fileUsers.read();
        }



        updateProfil();


    }

    public void updateProfil(){
        if((resultat/0.2)>= page_calcul.getPourcentageSuivant()){
            connexion.current.setSerie(connexion.current.getSerie()+1);
        }
        else{
            connexion.current.setSerie(0);
        }

        Log.e("test serie ", "" + choix_jeux.profil1.getSerie());

        if((connexion.current.getSerie() == choix_jeux.profil1.getSerie())&&(choix_jeux.profil1.getSerie()!=0)){
            connexion.current.setNumLevel(connexion.current.getNumLevel()+1);
            changementLevel =true;
            connexion.current.setSerie(0);
        }


        File f = new File(this.getFilesDir(),connexion.current.getName() + ".xml");


        WriteXMLFileUsers fileUsers;
        fileUsers = new WriteXMLFileUsers(f);
        fileUsers.majUser(f,connexion.current);

        read(f);


    }

    public void test(View view){

        resGauche="";
        resDroite="";
        resultat = 0;

        button b = new button();
        b.home(view, this);
    }

    private void writeToFile(int data, Context context, String chemin) {
        try {
            File path = context.getFilesDir();
            File file = new File(path, chemin);
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



    public static void read(File f) {

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

            document = builder.parse(f);




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
            System.out.println("level :" + level);

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
