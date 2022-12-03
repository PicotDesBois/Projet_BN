package org.Package;
import java.io.IOException;
import java.util.Scanner;
public class Main {



    public static void main(String[] args) {

        /*Partie party = new Partie();
        party.Jouer();

        SousMarins s1 = new SousMarins();
        Destroyer d1 = new Destroyer();
        Croiseur cr1 = new Croiseur();
        Cuirasse cu1 = new Cuirasse();

        Initialisation init = new Initialisation();*/
        String fileName="Test";
        Joueur j1 = new Joueur();
        Joueur j2=null;

        try {
            System.out.println("Saisir le nom de la sauvegarde : ");
            fileName=Saisi();
            j1.sauvegarde(fileName);
        }
        catch (IOException exc)
        {
            System.out.println("exc");
        }
        try{
            System.out.println("Quelle partie voulez vous charger : ");
            fileName=Saisi();
            j2 = new Joueur(fileName);
            System.out.println("Vous avez chargé la partie : "+fileName);
        }
        catch (IOException exc)
        {
            System.out.println("exc");
        }
        j2.Afficher();
    }
    public static String  Saisi()
    {
        Scanner in=null;
        String temp;
        in = new Scanner(System.in);
        temp = in.nextLine();
        return temp;
    }
}
