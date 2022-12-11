package Vue;
import Controleur.*;
import Model.*;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class GameGUI extends javax.swing.JFrame {

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameGUI().setVisible(true);


            }
        });
    }

    static Graphics g;
    static Graphics f;

    int DeplacementOn = 0;
    int TireOn = 0;


    int sx;
    int sy;
    public GameGUI() {
        initComponents();
        g = jPanel1.getGraphics();
        jPanel1.paintComponents(g);
        f = jPanel2.getGraphics();
        jPanel2.paintComponents(f);

        addMouseListener (new MouseAdapter()
        {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for (int i = 0; i < 15; i++) {
                    if (x >= 145 && x <= 535 && y >= 62 && y <= 452) {

                        if ((x - 145)/26 >= i && (x - 145)/26 < i + 1) {
                            //System.out.println("coordonnée en x " + (i+1));
                            sx = i;
                        }
                        if ((y - 62)/26 >= i && (y - 62)/26 < i + 1) {
                            //System.out.println("coordonnée en y " + (i+1));

                            sy = i;

                        }
                    }


                }
                if (x >= 145 && x <= 535 && y >= 62 && y <= 452) {
                    System.out.println("coordonnée du plateau 1 : x et y " + Selection(sx, sy));
                }
                for (int i = 0; i < 15; i++) {
                    if (x >= 563 && x <= 953 && y >= 62 && y <= 452) {

                        if ((x - 563)/26 >= i && (x - 563)/26 < i + 1) {
                            //System.out.println("coordonnée en x " + (i+1));
                            sx = i;
                        }
                        if ((y - 62)/26 >= i && (y - 62)/26 < i + 1) {
                            //System.out.println("coordonnée en y " + (i+1));
                            sy = i;

                        }
                    }

                }
                if (x >= 563 && x <= 953 && y >= 62 && y <= 452) {
                    System.out.println("coordonnée du plateau 2: x et y " + Selection(sx, sy));
                }
                if(DeplacementOn ==1) {
                    m_choixNavire = P1.getCase(sx, sy);
                    do {
                        // 1 = horizontale
                        if (m_player.getFlotte2(m_choixNavire).getOrientation() == 1) {
                            // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                            // choix entre fusée et tir normal
                            System.out.println("Dans quelle direction voulez-vous vous déplacer ?");
                            System.out.println("1- vers la droite");
                            System.out.println("2- vers la gauche");
                        }
                        // 2 = verticale
                        else if (m_player.getFlotte2(m_choixNavire).getOrientation() == 2) {
                            // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                            // choix entre fusée et tir normal
                            System.out.println("Dans quelle direction voulez-vous vous déplacer ?");
                            System.out.println("1- vers le haut");
                            System.out.println("2- vers le bas");
                        }
                        do {
                            in = new Scanner(System.in);
                            choir_dep = in.nextInt();
                            if (choir_dep < 1 && choir_dep > 2)
                                System.out.println("Mauvaise saisie, veuillez ressayer");
                        } while (choir_dep < 1 && choir_dep > 2);


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

                    TourIA();
                    QuiAGagne();
                    if (m_victoire == 1) {
                        System.out.println("Vous avez perdu");
                    }
                    if (m_victoire == 2) {
                        System.out.println("Vous avez perdu");
                    }
                    Actualisation();
                    DeplacementOn = 0;
                }
                if (TireOn ==1){
                    m_choixNavire = P2.getCase(sx, sy);
                    ChoixCoordTir(m_coor);
                    // tirer
                    m_player.getFlotte1()[m_choixNavire].Tirer(m_coor, m_IA.getFlotte1());

                    TourIA();
                    QuiAGagne();
                    if (m_victoire == 1){
                        System.out.println("Vous avez perdu");}
                    if (m_victoire == 2){
                        System.out.println("Vous avez gagné");}
                    Actualisation();
                }

            }
        });
    }


    //public void Grille(){
    Plateau P2 = new Plateau(15,15);
    Plateau P1 = new Plateau(15,15);
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        int col = 15;
        int ligne = 15;

        int OrigineX = 0;
        int OrigineY = 0;
        int taille = 26;
        //System.out.println("Veillez saisir votre pseudo :");
        //m_player.setPseudo(m_player.Saisi());
        g.drawRect(OrigineX,OrigineY, 389, 389);
        f.drawRect(OrigineX,OrigineY, 390, 389);

        for(int i = 0; i <= ligne+1; i++){

            g.drawLine(OrigineX,(OrigineY + i)*taille, (OrigineX +col)*taille, (OrigineY + i) * taille);

        }

        for(int i = 0; i <= col+1; i++){

            g.drawLine((OrigineX + i)*taille ,OrigineY,  (OrigineX + i) * taille, (OrigineY +ligne)*taille);

        }

        for(int i = 0; i <= ligne+1; i++){

            f.drawLine(OrigineX,(OrigineY + i)*taille, (OrigineX +col)*taille, (OrigineY + i) * taille);

        }

        for(int i = 0; i <= col+1; i++){

            f.drawLine((OrigineX + i)*taille ,OrigineY,  (OrigineX + i) * taille, (OrigineY +ligne)*taille);

        }


        init.PositionAleaNavire(m_player.getFlotte1());
        init.PositionAleaNavire(m_IA.getFlotte1());

        P1.PlateauFill(m_player.getFlotte1());
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P1.getCase(i,j) >= 0){
                    if(P1.getCase(i,j)==0) {
                        f.setColor(Color.orange);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==1) {
                        f.setColor(Color.GREEN);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==2) {
                        f.setColor(Color.BLUE);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==3) {
                        f.setColor(Color.YELLOW);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==4) {
                        f.setColor(Color.RED);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==5) {
                        f.setColor(Color.MAGENTA);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==6) {
                        f.setColor(Color.PINK);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==7) {
                        f.setColor(Color.CYAN);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==8) {
                        f.setColor(Color.BLACK);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==9) {
                        f.setColor(Color.LIGHT_GRAY);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }

                }

                else{
                    f.clearRect((i*26)+1  ,(j *26) +1 ,  24, 24);
                }
            }
        }


        P2.PlateauFill(m_IA.getFlotte1());

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P2.getCase(i,j) >= 0){
                    if(P2.getCase(i,j)==0) {
                        g.setColor(Color.orange);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==1) {
                        g.setColor(Color.GREEN);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==2) {
                        g.setColor(Color.BLUE);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==3) {
                        g.setColor(Color.YELLOW);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==4) {
                        g.setColor(Color.RED);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==5) {
                        g.setColor(Color.MAGENTA);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==6) {
                        g.setColor(Color.PINK);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==7) {
                        g.setColor(Color.CYAN);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==8) {
                        g.setColor(Color.BLACK);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P2.getCase(i,j)==9) {
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }

                }
                else{
                    g.clearRect((i*26)+1 ,(j *26)+1,  24, 24);
                }
            }
        }

        jLabel1.setText("Que voulez-vous faire ?");
    }
    private boolean m_save;
    // si l'on veut quitter la partie ou non
    private boolean m_quit;

    // joueur et AI de la partie


    // variable pour les choix du joueur
    private int m_choixAction;
    private int m_choixNavire;

    // coordonnée de la case pour tirer (ou se déplacer ?)
    private Case m_coor;

    // condition de victoire : 0 pas de victoire, 1 victoire du joueur, 2 victoire de l'IA
    private int m_victoire;
    Partie newGame =new Partie();

    Joueur m_player=new Joueur();

    Joueur m_IA=new Joueur();

    Initialisation init = new Initialisation();
    protected int m_pv;
    protected int m_puissance;
    // 1 = horizontale
    // 2 = verticale
    protected int m_orientation;
    protected String m_type;
    protected Case[] m_cases;
    protected boolean m_fusee;
    protected boolean m_coule;
    protected int choir_dep;
    boolean Deplacement=true;
    Scanner in=null;
    String fileName;

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    TireOn =1;
        jLabel1.setText("Quel navire voulez vous Tirer");






    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {

        jLabel1.setText("Veuillez selectionner un bateau de votre plateau");

        if (m_player.getFlotte2(m_choixNavire).getFusee() == true) {
            ChoixCoordTir(m_coor);
            //P2.afficherFusee(m_player.getFlotte1()[m_choixNavire].tirFusee(m_coor, m_IA.getFlotte1()));
            TourIA();
            QuiAGagne();
            if (m_victoire == 1){
                System.out.println("Vous avez perdu");}
            if (m_victoire == 2){
                System.out.println("Vous avez perdu");}
            Actualisation();

        }

    }



    // choix du tir pour destroyer
    int choix_tir=0;

    int tour=0;


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {



        boolean Deplacement=true;
        P1.PlateauFill(m_player.getFlotte1());
        DeplacementOn = 1;
        jLabel1.setText("Quel navire voulez vous déplacer");



    }






    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 390));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 390, Short.MAX_VALUE)
        );



        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(390, 390));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 390, Short.MAX_VALUE)
        );


        jLabel1.setText("Initialisation de la partie ...");
        jButton1.setText("Sauvegarder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton2.setText("Tirer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Fusée");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Déplacer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                        .addGroup(layout.createSequentialGroup()

                                .addContainerGap()

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton4)
                                                        .addComponent(jButton3)
                                                        .addComponent(jButton2))

                                                .addGap(40, 40, 40)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))


                                        .addComponent(jButton1))
                                .addComponent(jLabel1 , javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)



                                .addGap(0, 54, Short.MAX_VALUE))

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addComponent(jLabel1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton4))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
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
    List<Number> Selection(int sx, int sy) {

        List<Number> coordinates = new ArrayList<>();

        coordinates.add(sx);
        coordinates.add(sy);

        return coordinates;
    }


    public void TourIA(){
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
                    // choix du navire
                    m_choixNavire = (int) (Math.random() * 9 + 1);
                } else;

            } while (!Deplacement);
        }
    }
    public void Actualisation(){

        P1.PlateauFill(m_player.getFlotte1());
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P1.getCase(i,j) >= 0){
                    if(P1.getCase(i,j)==0) {
                        f.setColor(Color.orange);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==1) {
                        f.setColor(Color.GREEN);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==2) {
                        f.setColor(Color.BLUE);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==3) {
                        f.setColor(Color.YELLOW);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==4) {
                        f.setColor(Color.RED);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==5) {
                        f.setColor(Color.MAGENTA);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==6) {
                        f.setColor(Color.PINK);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==7) {
                        f.setColor(Color.CYAN);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==8) {
                        f.setColor(Color.BLACK);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }
                    if(P1.getCase(i,j)==9) {
                        f.setColor(Color.LIGHT_GRAY);
                        f.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                    }

                }
                else{

                    f.clearRect((i*26)+1  ,(j *26) +1 ,  24, 24);
                }
            }
        }

        P2.PlateauFill(m_IA.getFlotte1());

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P2.getCase(i,j) >= 0){
                    if( P2.getCase(i,j) >= 0){
                        if(P2.getCase(i,j)==0) {
                            g.setColor(Color.orange);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==1) {
                            g.setColor(Color.GREEN);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==2) {
                            g.setColor(Color.BLUE);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==3) {
                            g.setColor(Color.YELLOW);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==4) {
                            g.setColor(Color.RED);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==5) {
                            g.setColor(Color.MAGENTA);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==6) {
                            g.setColor(Color.PINK);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==7) {
                            g.setColor(Color.CYAN);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==8) {
                            g.setColor(Color.BLACK);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }
                        if(P2.getCase(i,j)==9) {
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                        }

                    }
                }
                else{
                    g.clearRect((i*26)+1 ,(j *26)+1,  24, 24);
                }
            }
        }

    }











    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;




}


