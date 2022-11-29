package org.Package;

import java.util.Scanner;

public class Partie {

    // si la partie va etre sauvegarder
    private boolean m_save;

    // si l'on veut quitter la partie ou non
    private boolean m_quit;

    // joueur et AI de la partie
    private Joueur m_player, m_IA;

    private int m_choixAction;

    private int m_choixNavire;


    // constructeur
    public Partie()
    {
        m_save=false;
        m_quit=false;

        m_player=new Joueur();
        m_IA=new Joueur();
        m_IA.setPseudo("IA");

        m_choixAction=0;
        m_choixNavire=-1;
    }

    public void Jouer()
    {
        // initialisation du joueur : pseudo + flotte de navires aléatoire
        Initialisation init = new Initialisation();
        m_player.ChoixPseudo();
        init.PositionAleaNavire(m_player.getFlotte1());

        // initialisation de l'IA : flotte de navires aléatoire
        init.PositionAleaNavire(m_IA.getFlotte1());

        // scanner pour la saisie
        Scanner in=null;

        // choix du tir pour destroyer
        int choix_tir=0;

        // jouer tant que le joueur ne veut pas quitter
        do{
            /************* tour du joueur ************/

            // choix de l'action à réaliser
            System.out.println("Voulez vous tirer ou déplacer un navire ?");
            System.out.println("1- Tirer");
            System.out.println("2- Déplacer");
            do {
                in = new Scanner(System.in);
                m_choixAction = in.nextInt();
                if (m_choixAction != 1 && m_choixAction != 2)
                    System.out.println("Mauvaise saisie, veuillez ressayer");
            } while (m_choixAction != 1 && m_choixAction != 2);

            // choix du navire pour réaliser l'action
            // tir
            if (m_choixAction == 1)
                System.out.println("De quel navire voulez vous tirer ?");
            // déplacement
            else
                System.out.println("Quel navire voulez vous déplacer");
            do {
                in = new Scanner(System.in);
                m_choixNavire = in.nextInt();
                if (m_choixNavire < 0 || m_choixNavire > 9)
                    System.out.println("Mauvaise saisie, veuillez ressayer");
            } while (m_choixNavire < 0 || m_choixNavire > 9);

            // Réalisation de l'action
            // tir
            if (m_choixAction == 1)
            {
                // si le bateau est un destroyer : fusée ou tir
                if (m_choixNavire==3 || m_choixNavire==4 || m_choixNavire==5)
                {
                    System.out.println("Quel type de tir voulez-vous faire");
                    System.out.println("1- Fusée éclairante");
                    System.out.println("2- Tir");
                    do {
                        in = new Scanner(System.in);
                        choix_tir = in.nextInt();
                        if (choix_tir != 1 && choix_tir != 2)
                            System.out.println("Mauvaise saisie, veuillez ressayer");
                    } while (choix_tir != 1 && choix_tir != 2);

                    // vérifier aussi qu'il peut encore tirer une fusée
                    if (choix_tir==1)
                    {
                        // tirer la fusée éclairante -> un carré de 4*4 cases dans la grille adverse à partir du coin haut et gauche
                        // le destroyer ne peut plus tirer de fusée
                    }
                    else
                        m_player.getFlotte2(m_choixNavire).Tirer();
                }
                else
                    m_player.getFlotte2(m_choixNavire).Tirer();
            }
            // déplacement
            else
            {
                // demander au joueur les coordonnées du déplacement
                m_player.getFlotte2(m_choixNavire).Deplacer(0,0);
            }

            /************* tour de l'IA ************/

            // choix de l'action à réaliser
            m_choixAction =(int)(Math.random() * (2 - 1 + 1) + 1);

            // choix du navire
            m_choixNavire =(int)(Math.random() * 9 + 1);

            // Réalisation de l'action
            if (m_choixAction==1)
            {
                // tirer
                if (m_choixNavire==3 || m_choixNavire==4 || m_choixNavire==5)
                {
                    // choix du type de tir pour le destroyer
                    choix_tir =(int)(Math.random() * (2 - 1 + 1) + 1);

                    // vérifier aussi qu'il peut encore tirer une fusée
                    if (choix_tir==1)
                    {
                        // tirer la fusée éclairante -> un carré de 4*4 cases dans la grille adverse à partir du coin haut et gauche
                        // le destroyer ne peut plus tirer de fusée
                    }
                    else
                        m_player.getFlotte2(m_choixNavire).Tirer();
                }
                else
                {
                    m_player.getFlotte2(m_choixNavire).Tirer();
                }
            }
            else
            {
                // déplacer
                m_player.getFlotte2(m_choixNavire).Deplacer(0,0);
            }


        } while (!m_quit);

        // si le joueur veut sauvegarder sa partie
        if (m_save)
        {
            // lancer la sauvegarde
        }
    }
}
