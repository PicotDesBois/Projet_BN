package org.Package;

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

        m_coor= new Case();
    }

    // choix des coordonnées pour le tir et la fusée
    public void ChoixCoordTir (Case coor)
    {
        // faire le choix des coordonnées
        Scanner in=null;

        System.out.println("Choisissez les coordonnees du navire");
        System.out.println("Choisir la coordonnee des ordonnees entre 0 et 14");

        do {
            in = new Scanner(System.in);
            int temp = in.nextInt();
            coor.setCoorX(temp);
            if (coor.getCoorX() < 0 && coor.getCoorX() > 14)
                System.out.println("Mauvaise saisie, veuillez ressayer");
        } while (coor.getCoorX() < 0 && coor.getCoorX() > 14);

        System.out.println("Choisir la coordonnee des abscisses entre 0 et 14");

        do {
            in = new Scanner(System.in);
            int temp = in.nextInt();
            coor.setCoorY(temp);
            if (coor.getCoorY() < 0 && coor.getCoorY() > 14)
                System.out.println("Mauvaise saisie, veuillez ressayer");
        } while (coor.getCoorY() < 0 && coor.getCoorY() > 14);
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

        // choix du tir pour destroyer
        int choix_tir=0;
        int choix_dep=0;

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
                // si le bateau peut tirer une fusée éclairante
                if (m_player.getFlotte2(m_choixNavire).getFusee()==true)
                {
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

                    if (choix_tir==1)
                    {
                        // choix des coordonnées par le joueur
                        ChoixCoordTir(m_coor);
                        // tirer la fusée éclairante
                    }
                    else
                    {
                        // choix des coordonnées par le joueur
                        ChoixCoordTir(m_coor);
                        // tirer
                        m_player.getFlotte2(m_choixNavire).Tirer(m_coor);
                    }
                }
                // le navire ne peut pas tirer de fusée
                else
                {
                    // choix des coordonnées par le joueur
                    ChoixCoordTir(m_coor);
                    // tirer
                    m_player.getFlotte2(m_choixNavire).Tirer(m_coor);
                }
            }

            // déplacement
            else
            {
                // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4

                // vérifier si le navire peut se déplacer

                m_player.getFlotte2(m_choixNavire).Deplacer(choix_dep, m_player.getFlotte1(), m_choixNavire);
            }

            /************* tour de l'IA ************/

            // choix de l'action à réaliser
            m_choixAction =(int)(Math.random() * (2 - 1 + 1) + 1);

            // choix du navire
            m_choixNavire =(int)(Math.random() * 9 + 1);

            // Réalisation de l'action
            // choix = tirer
            if (m_choixAction==1)
            {
                // si le bateau peut tirer une fusée éclairante
                if (m_IA.getFlotte2(m_choixNavire).getFusee()==true)
                {
                    // aléatoire entre fusée et tir normal
                    choix_tir=(int)(Math.random() * (2 - 1 + 1) + 1);

                    // fusée éclairante
                    if (choix_tir==1)
                    {
                        // choix des coordonnées aléatoires
                        m_coor.setCoorX((int)(Math.random() * 14 + 1));
                        m_coor.setCoorY((int)(Math.random() * 14 + 1));
                        // tirer la fusée éclairante
                    }
                    // tir normal
                    else {
                        // choix des coordonnées aléatoires
                        m_coor.setCoorX((int)(Math.random() * 14 + 1));
                        m_coor.setCoorY((int)(Math.random() * 14 + 1));

                        m_IA.getFlotte2(m_choixNavire).Tirer(m_coor);
                    }
                }
                // tir normal
                else
                {
                    // choix des coordonnées aléatoires
                    m_coor.setCoorX((int)(Math.random() * 14 + 1));
                    m_coor.setCoorY((int)(Math.random() * 14 + 1));

                    m_IA.getFlotte2(m_choixNavire).Tirer(m_coor);
                }
            }
            // choix = déplacer
            else
            {
                // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                choix_dep=(int)(Math.random() * (4 - 1 + 1) + 1);
                // faire méthode pour le déplacement
                // déplacer
                m_IA.getFlotte2(m_choixNavire).Deplacer(choix_dep,m_IA.getFlotte1(), m_choixNavire);
            }


        } while (!m_quit);

        // si le joueur veut sauvegarder sa partie
        if (m_save)
        {
            // lancer la sauvegarde
        }
    }
}
