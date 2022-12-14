package Controleur;

import Model.Case;
import Model.Navire;
import Vue.Affichage;
import Vue.Plateau;
import Vue.Saisie;

import java.io.IOException;

public class Partie {

    /**
     * joueur et AI de la partie
     */
    private Joueur m_player, m_IA;

    /**
     * variables pour les choix du joueur
     */
    private int m_choixAction;
    private int m_choixNavire;

    /**
     * coordonnée de la case pour tirer
     */
    private Case m_coor;

    /**
     * condition de victoire : 0 pas de victoire, 1 victoire de l'IA, 2 victoire du joueur
     */
    private int m_victoire;


    /**
     * constructeur
     */
    public Partie()
    {
        m_victoire=0;

        m_player=new Joueur();
        m_IA=new Joueur();
        m_IA.setPseudo("IA");

        m_choixAction=0;
        m_choixNavire=-1;

        m_coor= new Case(0,0,0,false);
    }


    /**
     * méthode de choix des coordonnées pour le tir et la fusée
     * @param coor coordonnées du tir
     */
    public void ChoixCoordTir (Case coor)
    {
        // affichage
        Affichage aff=new Affichage();
        // saisie
        Saisie saisir=new Saisie();

        aff.AfficherSaisir("les coordonnées du navire");
        aff.AfficherSaisir("l'abscisse entre 0 et 14");
        coor.setCoorX(saisir.saisirEntier(0, 14));
        aff.AfficherSaisir("l'ordonnée entre A et O");
        coor.setCoorY(saisir.saisirOrdonne());
    }

    /**
     * condition de victoire : 0 pas de victoire, 1 victoire du joueur, 2 victoire de l'IA
     * avec l'attribut m_victoire
     */
    public void QuiAGagne()
    {
        int nb_IA=0, nb_joueur=0;

        for(int i=0;i<10;i++)
        {
            if (m_player.getFlotte2(i).getCoule())
                nb_joueur++;
            if (m_IA.getFlotte2(i).getCoule())
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

    /**
     * méthode de la partie de jeu en console
     */
    public void Jouer()
    {
        // scanner pour la saisie
        String fileName;

        // choix du tir pour destroyer
        int choix_tir;
        int choir_dep;
        int tour=0;
        boolean chargement;
        Affichage vue=new Affichage();
        Saisie saisie=new Saisie();

        do {
            // choix de l'action à réaliser
            vue.AfficherTexte("Voulez vous charger un ancienne partie ou commencer une nouvelle partie ?\n1- Nouvelle partie\n2- Charger une ancienne partie");
            m_choixAction = saisie.saisirEntier(1, 2);

            if (m_choixAction == 1) {
                // initialisation du joueur : pseudo + flotte de navires aléatoire
                Initialisation init = new Initialisation();
                vue.AfficherSaisir("votre pseudo");
                m_player.setPseudo(saisie.saisirChaine());
                init.PositionAleaNavire(m_player.getFlotte1());

                // initialisation de l'IA : flotte de navires aléatoire
                init.PositionAleaNavire(m_IA.getFlotte1());
                chargement=true;
            }
            else {
                vue.AfficherSaisir("la sauvegarde à charger");
                fileName = saisie.saisirChaine();
                try {
                    m_player = new Joueur(fileName);
                    m_IA = new Joueur(fileName + "_IA");
                    vue.AfficherTexte("Vous avez bien chargé la partie : " + fileName);
                    chargement=true;
                } catch (IOException exc) {
                    vue.AfficherTexte("ERREUR chargement de "+fileName);
                    chargement=false;
                }
            }
        }while(!chargement);

        Plateau plato_IA = new Plateau(15, 15);
        plato_IA.PlateauFill( m_IA.getFlotte1());
        Plateau plato_joueur = new Plateau(15, 15);
        plato_joueur.PlateauFill( m_player.getFlotte1());

        vue.Afficher(m_player);
        int [][] cordTir=new int[0][0];
        // jouer tant que le joueur ne veut pas quitter
        do{
            tour++;
            /* tour du joueur ************/
            boolean Deplacement;

            vue.AfficherTour(tour);
            saisie.ValiderTour();

            /* Affiche le plateau du joueur **********/
            plato_joueur.PlateauFill(m_player.getFlotte1());
            vue.AfficherPlateau("Votre plateau");
            plato_joueur.afficher(m_player.getFlotte1());
            vue.AfficherPlateau("Plateau adverse 1");
            plato_IA.afficherTir(cordTir,m_IA.getFlotte1(),m_choixNavire);
            /* ***************************************************************/

            /* Affiche le plateau de l'IA pour mode triche **********/
            plato_IA.PlateauFill(m_IA.getFlotte1());
            vue.AfficherPlateau("Plateau de l'IA");
            plato_IA.afficher(m_IA.getFlotte1());
            /* ***************************************************************/

            // choix de l'action à réaliser
            vue.AfficherPlateau("Tour du joueur");
            vue.AfficherTexte("Voulez vous tirer ou déplacer un navire ?\n1- Tirer\n2- Déplacer\n3- Sauvegarder\n4- Aide\n5- Quitter");
            m_choixAction=saisie.saisirEntier(1,5);
            // choix du navire pour réaliser l'action
            // tir
            if(m_choixAction == 1||m_choixAction == 2) {
                if (m_choixAction == 1)
                    vue.AfficherTexte("De quel navire voulez vous tirer ?");
                    // déplacement
                else
                    vue.AfficherTexte("Quel navire voulez vous déplacer");
                do {
                    m_choixNavire=saisie.saisirEntier(0,9);
                    if(m_player.getFlotte1()[m_choixNavire].getCoule())
                        vue.AfficherTexte("Reselectionner les navires");
                }while(m_player.getFlotte1()[m_choixNavire].getCoule());

                // Réalisation de l'action
                // tir
                if (m_choixAction == 1) {
                    // si le bateau peut tirer une fusée éclairante
                    if (m_player.getFlotte2(m_choixNavire).getFusee()) {
                        // choix entre fusée et tir normal
                        vue.AfficherTexte("Quel type de tir voulez-vous faire\n1- Fusée éclairante\n2- Tir");
                        choix_tir= saisie.saisirEntier(1,2);

                        ChoixCoordTir(m_coor);
                        if (choix_tir == 1) {
                            plato_IA.afficherFusee(m_player.getFlotte1()[m_choixNavire].tirFusee(m_coor, m_IA.getFlotte1()),m_player.getFlotte1());
                        } else {
                            // choix des coordonnées par le joueur
                            // tirer
                             cordTir =m_player.getFlotte1()[m_choixNavire].Tirer(m_coor, m_IA.getFlotte1());
                            vue.AfficherPlateau("Plateau adverse");
                            plato_IA.afficherTir(cordTir,m_IA.getFlotte1(),m_choixNavire);
                        }
                    }
                    // le navire ne peut pas tirer de fusée
                    else {
                        // choix des coordonnées par le joueur
                        ChoixCoordTir(m_coor);
                        // tirer
                         cordTir=m_player.getFlotte1()[m_choixNavire].Tirer(m_coor, m_IA.getFlotte1());
                        vue.AfficherPlateau("Plateau adverse");
                        plato_IA.afficherTir(cordTir,m_IA.getFlotte1(),m_choixNavire);
                    }
                }

                // déplacement
                else {
                    do {
                        // 1 = horizontale
                        if (m_player.getFlotte2(m_choixNavire).getOrientation()==1) {
                            // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                            // choix entre fusée et tir normal
                            vue.AfficherTexte("Dans quelle direction voulez-vous vous déplacer ?\n1- vers la droite\n2- vers la gauche");
                        }
                        // 2 = verticale
                        else {
                            // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                            // choix entre fusée et tir normal
                            vue.AfficherTexte("Dans quelle direction voulez-vous vous déplacer ?\n1- vers le haut\n2- vers le bas");
                        }
                        choir_dep= saisie.saisirEntier(1,2);


                        Deplacement = m_player.getFlotte2(m_choixNavire).Deplacer(choir_dep, m_player.getFlotte1(), m_choixNavire);

                        if (!Deplacement) {
                            vue.AfficherTexte("vous ne pouvez pas déplacer votre bateau dans cette direction\nQuel navire voulez vous déplacer");
                            m_choixAction=saisie.saisirEntier(0,9);
                        } else
                            vue.AfficherTexte("vous pouvez déplacer votre bateau");
                    } while (!Deplacement);
                }
                /* ************ tour de l'IA ************/
                vue.AfficherPlateau("Tour de l'IA");

                // choix de l'action à réaliser
                m_choixAction = (int) (Math.random() * (2 - 1 + 1) + 1);

                // choix du navire
                do {
                    m_choixNavire = (int) (Math.random() * 9 + 1);
                }while(m_IA.getFlotte1()[m_choixNavire].getCoule());

                // Réalisation de l'action
                // choix = tirer
                if (m_choixAction == 1) {
                    // si le bateau peut tirer une fusée éclairante
                    if (m_IA.getFlotte2(m_choixNavire).getFusee()) {
                        // aléatoire entre fusée et tir normal
                        choix_tir = (int) (Math.random() * (2 - 1 + 1) + 1);

                        // choix des coordonnées aléatoires
                        m_coor.setCoorX((int) (Math.random() * 14 + 1));
                        m_coor.setCoorY((int) (Math.random() * 14 + 1));

                        // fusée éclairante
                        if (choix_tir == 1) {
                            // tirer la fusée éclairante
                            vue.AfficherTexte("tir de fusée éclairante en ( "+m_coor.getCoorX()+" ; "+m_coor.getCoorY()+" )");
                            //plato_joueur.afficherFusee(m_IA.getFlotte1()[m_choixNavire].tirFusee(m_coor, m_player.getFlotte1()),m_IA.getFlotte1());
                        }
                        // tir normal
                        else {
                            cordTir = m_IA.getFlotte1()[m_choixNavire].Tirer(m_coor, m_player.getFlotte1());
                            vue.AfficherTexte("tir normal en ( "+m_coor.getCoorX()+" ; "+m_coor.getCoorY()+" ) avec le "+m_IA.getFlotte2(m_choixNavire).geType());
                            //plato_joueur.afficherTir(cordTir,m_player.getFlotte1(),m_choixNavire);
                        }
                    }
                    // tir normal
                    else {
                        // choix des coordonnées aléatoires
                        m_coor.setCoorX((int) (Math.random() * 14 + 1));
                        m_coor.setCoorY((int) (Math.random() * 14 + 1));

                        cordTir = m_IA.getFlotte1()[m_choixNavire].Tirer(m_coor, m_player.getFlotte1());
                        vue.AfficherTexte("tir normal en ( "+m_coor.getCoorX()+" ; "+m_coor.getCoorY()+" ) avec le "+m_IA.getFlotte2(m_choixNavire).geType());
                        //plato_joueur.afficherTir(cordTir,m_player.getFlotte1(),m_choixNavire);
                    }
                }
                // choix = déplacer
                else {
                    // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                    choir_dep = (int) ((Math.random() * (2 - 1 + 1)) + 1);
                    do {
                        Deplacement = m_IA.getFlotte2(m_choixNavire).Deplacer(choir_dep, m_IA.getFlotte1(), m_choixNavire);

                        if (!Deplacement) {
                            // choix du navire
                            m_choixNavire = (int) (Math.random() * 9 + 1);
                        }

                    } while (!Deplacement);
                    vue.AfficherTexte("déplacement du navire "+m_choixNavire+" en ( "+m_coor.getCoorX()+" ; "+m_coor.getCoorY()+" )");
                }
            }
            else if(m_choixAction == 3)
            {
                vue.AfficherTexte("Quel nom voulez vous donner à votre sauvegarde ?");
                fileName=saisie.saisirChaine();

                try {
                    m_player.sauvegarde(fileName);
                    m_IA.sauvegarde(fileName + "_IA");
                    vue.AfficherTexte("Vous avez bien sauvegardé la partie : "+fileName);
                }
                catch (IOException exc)
                {
                    vue.AfficherTexte("exc");
                }
            }
            else if(m_choixAction == 4)
            {
                try
                {
                    vue.AfficherAide();
                }
                catch (IOException exc)
                {
                    vue.AfficherTexte("exc");
                }
            }
            QuiAGagne();
        } while (m_choixAction != 5 && m_victoire==0);

        if (m_victoire==1)
            vue.AfficherTexte("\nl'IA a gagné, vous avez perdu...\n");
        else if (m_victoire==2)
            vue.AfficherTexte("\nVous avez gagné !\n");

        vue.AfficherTexte("Vous avez mis fin à la partie");
    }
}
