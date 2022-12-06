package org.Package;

import java.io.IOException;
import java.util.Scanner;

public class Partie {

    // si la partie va etre sauvegarder
    private boolean m_save;
    // si l'on veut quitter la partie ou non
    private boolean m_quit;

    // joueur et AI de la partie
    private Joueur m_player, m_IA;

    // variable pour les choix du joueur
    private int m_choixAction;
    private int m_choixNavire;

    // coordonnée de la case pour tirer (ou se déplacer ?)
    private Case m_coor;

    // condition de victoire : 0 pas de victoire, 1 victoire du joueur, 2 victoire de l'IA
    private int m_victoire;


    // constructeur
    public Partie()
    {
        m_save=false;
        m_quit=false;
        m_victoire=0;

        m_player=new Joueur();
        m_IA=new Joueur();
        m_IA.setPseudo("IA");

        m_choixAction=0;
        m_choixNavire=-1;

        m_coor= new Case(0,0,0,false);
    }

    // choix des coordonnées pour le tir et la fusée
    public void ChoixCoordTir (Case coor)
    {
        // faire le choix des coordonnées
        Scanner in=null;

        System.out.println("Choisissez les coordonnees du navire");
        System.out.println("Choisir abscisse entre 0 et 14");

        do {
            in = new Scanner(System.in);
            int temp = in.nextInt();
            coor.setCoorX(temp);
            if (coor.getCoorX() < 0 && coor.getCoorX() > 14)
                System.out.println("Mauvaise saisie, veuillez ressayer");
        } while (coor.getCoorX() < 0 && coor.getCoorX() > 14);

        System.out.println("Choisir la coordonnee ordonnées entre 0 et 14");

        do {
            in = new Scanner(System.in);
            int temp = in.nextInt();
            coor.setCoorY(temp);
            if (coor.getCoorY() < 0 && coor.getCoorY() > 14)
                System.out.println("Mauvaise saisie, veuillez ressayer");
        } while (coor.getCoorY() < 0 && coor.getCoorY() > 14);
    }

    // condition de victoire : 0 pas de victoire, 1 victoire du joueur, 2 victoire de l'IA
    public void QuiAGagne()
    {
        int nb_IA=0, nb_joueur=0;

        for(int i=0;i<10;i++)
        {
            if (m_player.getFlotte2(i).m_coule)
                nb_joueur++;
            if (m_IA.getFlotte2(i).m_coule)
                nb_IA++;
        }

        if (nb_joueur==10)
        {
            m_victoire=1;
        }
        else if (nb_IA==10)
        {
            m_victoire=2;
        }
        else
        {
            m_victoire=0;
        }
    }


    public void Jouer()
    {
        // initialisation du joueur : pseudo + flotte de navires aléatoire + plateau
        Initialisation init = new Initialisation();
        System.out.println("Veillez saisir votre pseudo :");
        m_player.setPseudo(m_player.Saisi());
        init.PositionAleaNavire(m_player.getFlotte1());
        Plateau plato_joueur = new Plateau(15,15);
        plato_joueur.PlateauFill(plato_joueur,m_player.getFlotte1());

        m_player.Afficher();
        plato_joueur.afficher();

        // initialisation de l'IA : flotte de navires aléatoire + plateau
        init.PositionAleaNavire(m_IA.getFlotte1());
        Plateau plato_IA = new Plateau(15,15);
        plato_IA.PlateauFill(plato_IA,m_IA.getFlotte1());

        // scanner pour la saisie
        Scanner in=null;
        String fileName;

        // choix du tir pour destroyer
        int choix_tir=0;
        int choir_dep=0;
        int tour=0;

        // jouer tant que le joueur ne veut pas quitter
        do{
            tour++;
            /************* tour du joueur ************/
            boolean Deplacement=true;

            System.out.println("Tour : "+tour);

            plato_joueur.PlateauFill(plato_joueur,m_player.getFlotte1());
            System.out.println("Votre plateau");
            plato_joueur.afficher();

            /********* Affiche le plateau de l'IA pour mode triche **********/
            System.out.println("Plateau de l'IA");
            plato_IA.afficher();
            /****************************************************************/

            // choix de l'action à réaliser
            System.out.println("Voulez vous tirer ou déplacer un navire ?");
            System.out.println("1- Tirer");
            System.out.println("2- Déplacer");
            System.out.println("3- Sauvegarder");
            System.out.println("4- Quitter");
            do {
                in = new Scanner(System.in);
                m_choixAction = in.nextInt();
                if (m_choixAction != 1 && m_choixAction != 2 && m_choixAction != 3 && m_choixAction != 4)
                    System.out.println("Mauvaise saisie, veuillez ressayer");
            } while (m_choixAction != 1 && m_choixAction != 2 && m_choixAction != 3 && m_choixAction != 4);

            // choix du navire pour réaliser l'action
            // tir
            if(m_choixAction == 1||m_choixAction == 2) {
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
                if (m_choixAction == 1) {
                    // si le bateau peut tirer une fusée éclairante
                    if (m_player.getFlotte2(m_choixNavire).getFusee() == true) {
                        // choix entre fusée et tir normal
                        System.out.println("Quel type de tir voulez-vous faire");
                        System.out.println("1- Fusée éclairante");
                        System.out.println("2- Tir");
                        do {
                            in = new Scanner(System.in);
                            choix_tir = in.nextInt();
                            if (choix_tir != 1 && choix_tir != 2)
                                System.out.println("Mauvaise saisie, veuillez ressayer");
                        } while (choix_tir != 1 && choix_tir != 2);

                        if (choix_tir == 1) {
                            ChoixCoordTir(m_coor);
                            plato_IA.afficherFusee(m_player.getFlotte1()[m_choixNavire].tirFusee(m_coor, m_IA.getFlotte1()));
                        } else {
                            // choix des coordonnées par le joueur
                            ChoixCoordTir(m_coor);
                            // tirer
                            m_player.getFlotte1()[m_choixNavire].Tirer(m_coor, m_IA.getFlotte1());
                        }
                    }
                    // le navire ne peut pas tirer de fusée
                    else {
                        //m_IA.getFlotte1()[0].Afficher();
                        // choix des coordonnées par le joueur
                        ChoixCoordTir(m_coor);
                        // tirer
                        m_player.getFlotte1()[m_choixNavire].Tirer(m_coor, m_IA.getFlotte1());
                    }
                }

                // déplacement
                else {
                    do {
                        // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                        // choix entre fusée et tir normal
                        System.out.println("Dans quelle direction voulez-vous vous déplacer ?");
                        System.out.println("1- vers le haut");
                        System.out.println("2- vers le bas");
                        System.out.println("3- vers la droite");
                        System.out.println("4- vers la gauche");
                        do {
                            in = new Scanner(System.in);
                            choir_dep = in.nextInt();
                            if (choir_dep < 1 && choir_dep > 4)
                                System.out.println("Mauvaise saisie, veuillez ressayer");
                        } while (choir_dep < 1 && choir_dep > 4);

                        Deplacement = m_player.getFlotte2(m_choixNavire).Deplacer(choir_dep, m_player.getFlotte1(), m_choixNavire);

                        if (!Deplacement) {
                            System.out.println("vous ne pouvez pas déplacer votre bateau");
                            System.out.println("Quel navire voulez vous déplacer");
                            do {
                                in = new Scanner(System.in);
                                m_choixNavire = in.nextInt();
                                if (m_choixNavire < 0 || m_choixNavire > 9)
                                    System.out.println("Mauvaise saisie, veuillez ressayer");
                            } while (m_choixNavire < 0 || m_choixNavire > 9);
                        } else
                            System.out.println("vous pouvez déplacer votre bateau");
                    } while (!Deplacement);
                }
                /************* tour de l'IA ************/
                System.out.println("Tour de l'IA");

                // choix de l'action à réaliser
                m_choixAction = (int) (Math.random() * (2 - 1 + 1) + 1);

                // choix du navire
                m_choixNavire = (int) (Math.random() * 9 + 1);

                // Réalisation de l'action
                // choix = tirer
                if (m_choixAction == 1) {
                    // si le bateau peut tirer une fusée éclairante
                    if (m_IA.getFlotte2(m_choixNavire).getFusee() == true) {
                        // aléatoire entre fusée et tir normal
                        choix_tir = (int) (Math.random() * (2 - 1 + 1) + 1);

                        // fusée éclairante
                        if (choix_tir == 1) {
                            // choix des coordonnées aléatoires
                            m_coor.setCoorX((int) (Math.random() * 14 + 1));
                            m_coor.setCoorY((int) (Math.random() * 14 + 1));
                            // tirer la fusée éclairante
                        }
                        // tir normal
                        else {
                            // choix des coordonnées aléatoires
                            m_coor.setCoorX((int) (Math.random() * 14 + 1));
                            m_coor.setCoorY((int) (Math.random() * 14 + 1));

                            m_IA.getFlotte1()[m_choixNavire].Tirer(m_coor, m_player.getFlotte1());
                        }
                    }
                    // tir normal
                    else {
                        // choix des coordonnées aléatoires
                        m_coor.setCoorX((int) (Math.random() * 14 + 1));
                        m_coor.setCoorY((int) (Math.random() * 14 + 1));

                        m_IA.getFlotte1()[m_choixNavire].Tirer(m_coor, m_player.getFlotte1());
                    }
                }
                // choix = déplacer
                else {
                    // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                    choir_dep = (int) (Math.random() * (4 - 1 + 1) + 1);
                    do {
                        Deplacement = m_IA.getFlotte2(m_choixNavire).Deplacer(choir_dep, m_IA.getFlotte1(), m_choixNavire);

                        if (!Deplacement) {
                            System.out.println("vous ne pouvez pas déplacer votre bateau");
                            // choix du navire
                            m_choixNavire = (int) (Math.random() * 9 + 1);
                        } else
                            System.out.println("vous pouvez déplacer votre bateau");

                    } while (!Deplacement);
                }
            }
            else if(m_choixAction == 3)
            {
                System.out.println("Quel nom voulez vous donner à votre sauvegarde ?");
                in = new Scanner(System.in);
                fileName = in.nextLine();
                try {
                    m_player.sauvegarde(fileName);
                    m_IA.sauvegarde(fileName + "_IA");
                    System.out.println("Vous avez bien sauvegardé la partie : "+fileName);
                }
                catch (IOException exc)
                {
                    System.out.println("exc");
                }
                QuiAGagne();
            }
        } while (m_choixAction != 4&&m_victoire==0);
        System.out.println("Vous avez mis fin à la partie");
    }
}
