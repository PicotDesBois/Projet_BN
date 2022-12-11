package Vue;

import Model.Case;
import Controleur.Joueur;
import Model.Navire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Affichage {

    /**
     * couleurs pour la console
     * <a href="https://www.javatpoint.com/java-color-codes">...</a>
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RED_BG = "\033[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";
    public static final String ANSI_MAGENTA_BG = "\u001B[45m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";


    /**
     * constructeur
     */
    public Affichage()
    {

    }

    /**
     * afficher les infos d'un navire
     * @param nav le navire
     */
    public void Afficher(Navire nav)
    {
        System.out.println(ANSI_CYAN_BG + ANSI_BLACK + nav.geType() + ANSI_RESET);
        System.out.print("Puissance "+nav.getPuissance()+"\nPV restant "+nav.getPV() +"\n");

        if (nav.getFusee())
            System.out.println("Le navire peut tirer une fusee eclairante");
        else
            System.out.println("Le navire ne peut pas tirer de fusee eclairante");
        if (!nav.getCoule())
            System.out.println("Le navire est a flot");
        else
            System.out.println("Le navire a été envoyé par le fond");

        if (nav.getOrientation()==1)
        {
            System.out.println("le navire est horizontal "+nav.getOrientation());
        }
        else
        {
            System.out.println("le navire est vertical "+nav.getOrientation());
        }

        for (int i=0;i<nav.getPV();i++)
            System.out.println("Case : touche :"+nav.getCase()[i].getCoorX()+" ; "+nav.getCase()[i].getCoorY()+" )"+nav.getCase()[i].getTouche());
    }

    /**
     * afficher les infos du joueur
     * @param player le joueur
     */
    public void Afficher(Joueur player)
    {
        System.out.println(ANSI_YELLOW_BG + ANSI_BLACK + "Pseudo"+ ANSI_RESET + " :  " +player.getPseudo());
        System.out.println(ANSI_YELLOW_BG + ANSI_BLACK +"Flotte"+ ANSI_RESET + " :  ");
        for (int i=0;i<10;i++)
        {
            Afficher(player.getFlotte1()[i]);
        }
    }

    /**
     * afficher une case du plateau
     * @param cas la case
     */
    public void Afficher(Case cas)
    {
        System.out.println("Case : ( "+cas.getCoorX()+" ; "+cas.getCoorY()+" )");
        System.out.println("Touche : "+cas.getTouche());
        System.out.println("Navire : "+cas.getNavire());
    }

    /**
     * afficher un texte dans la console
     * @param text le text + "saisir"
     */
    public void AfficherSaisir(String text)
    {
        System.out.println("Saisir "+text);
    }

    /**
     * afficher un texte dans la console
     * @param text le texte
     */
    public void AfficherTexte(String text)
    {
        System.out.println(text);
    }

    /**
     * afficher le tour de jeu
     * @param tour le tour de jeu
     */
    public void AfficherTour(int tour)
    {
        System.out.println(" \n\n"+ANSI_MAGENTA_BG + ANSI_BLACK + "Tour" + ANSI_RESET + " :  " + tour + "\n");
    }

    /**
     * afficher le nom du plateau en couleur
     * @param text le nom du plateau
     */
    public void AfficherPlateau(String text)
    {
        System.out.println(" \n\n"+ANSI_RED_BG + ANSI_BLACK + text + ANSI_RESET);
    }

    /**
     * afficher l'aide à partir d'un fichier texte
     * @throws IOException exception
     */
    public void AfficherAide()throws IOException
    {
        String texte;
        // Création d’un fileReader pour lire le fichier
        FileReader fileReader = new FileReader("Aide.txt");
        // Création d’un bufferedReader qui utilise le fileReader
        BufferedReader reader = new BufferedReader (fileReader);

        try
        {
            do {
                texte=reader.readLine();
                AfficherTexte(texte);
            }while(texte!=null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }
}
