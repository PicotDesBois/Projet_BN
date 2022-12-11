package Vue;
import Controleur.*;
import Model.*;
import Model.Case;
import Model.Navire;


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

    int PV0 =0;
    int PV1 =0;
    int PV2 =0;
    int PV3 =0;
    int PV4 =0;
    int PV5 =0;
    int PV6 =0;
    int PV7 =0;
    int PV8 =0;
    int PV9 =0;






    int sx;
    int sy;
    int sx2;
    int sy2 =-1;;
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
                //System.out.println("coordonnée en x " + x);
                //System.out.println("coordonnée en y " + y);

                for (int i = 0; i < 15; i++) {
                    if (x >= 176 && x <= 566 && y >= 78 && y <= 468) {

                        if ((x - 176)/26 >= i && (x - 176)/26 < i + 1) {

                            sx = i;
                        }
                        if ((y - 78)/26 >= i && (y - 78)/26 < i + 1) {


                            sy = i;

                        }
                    }


                }
                if (x >= 176 && x <= 556 && y >= 78 && y <= 468) {
                    System.out.println("coordonnée du plateau 1 : x et y " + Selection(sx, sy));
                }
                for (int i = 0; i < 15; i++) {
                    if (x >= 594 && x <= 984 && y >= 78 && y <= 468) {

                        if ((x - 594)/26 >= i && (x - 594)/26 < i + 1) {

                            sx2 = i;
                        }
                        if ((y - 78)/26 >= i && (y - 78)/26 < i + 1) {

                            sy2 = i;

                        }
                    }

                }
                if (x >= 594 && x <= 984 && y >= 78 && y <= 468) {
                    System.out.println("coordonnée du plateau 2: x et y " + Selection(sx2, sy2));
                }
                if(DeplacementOn ==1) {
                    m_choixNavire = P1.getCase(sx, sy);
                    do {
                        // 1 = horizontale
                        if (m_player.getFlotte2(m_choixNavire).getOrientation() == 1) {
                            // demander au joueur ou déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
                            // choix entre fusée et tir normal
                            System.out.println("Dans quelle direction voulez-vous vous déplacer ?");
                            System.out.println("1- vers la gauche");
                            System.out.println("2- vers la droite");
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
                        new victoire().setVisible(true);
                        setVisible( false);
                        System.out.println("Vous avez gagné");
                    }
                    if (m_victoire == 2) {
                        new defaite().setVisible(true);
                        setVisible( false);
                        System.out.println("Vous avez perdu");
                    }
                    Actualisation();
                    DeplacementOn = 0;
                    jLabel1.setText("Que voulez-vous faire ?");
                }


                if (TireOn ==1 && sy2 != -1){
                    m_choixNavire = P2.getCase(sx2,sy2);

                    //ChoixCoordTir(m_coor);
                    m_coor = new Case(m_choixNavire,sx2,sy2,true);
                    int [][] cordTir =m_player.getFlotte1()[m_choixNavire].Tirer(m_coor, m_IA.getFlotte1());
                    /*g.setColor(Color.BLACK);
                    g.drawLine(sx2*26,sy2*26,(sx2+1)*26,(sy2+1)*26);
                    g.setColor(Color.BLACK);
                    g.drawLine(sx2*26,(sy2+1)*26,(sx2+1)*26,(sy2*26));*/


                    TourIA();
                    QuiAGagne();
                    if (m_victoire == 1){
                        new victoire().setVisible(true);
                        setVisible( false);
                        System.out.println("Vous avez perdu");}
                    if (m_victoire == 2){
                        new defaite().setVisible(true);
                        setVisible( false);
                        System.out.println("Vous avez gagné");}
                    Actualisation();
                    jLabel1.setText("Que voulez-vous faire ?");
                    sy2 =-1;
                    TireOn =0;
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
    private Case m_coor = new Case(0,0,0,false);

    // condition de victoire : 0 pas de victoire, 1 victoire du joueur, 2 victoire de l'IA
    private int m_victoire;
    Partie newGame =new Partie();

    Joueur m_player=new Joueur();

    Joueur m_IA=new Joueur();


    // variable pour les choix du joueur


    Initialisation init = new Initialisation();
    protected int m_pv;
    protected int m_puissance;
    // 1 = horizontale
    // 2 = verticale
    protected int m_orientation;
    protected String m_type;
    protected Case m_cases;
    protected boolean m_fusee;
    protected boolean m_coule;
    protected int choir_dep;
    boolean Deplacement=true;
    Scanner in=null;
    String fileName;

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    TireOn =1;
        jLabel1.setText("Selectionner votre navire en premier puis une case où tirer");






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
    Boolean chargement = false;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {

        fileName = in.nextLine();
        try {
            m_player = new Joueur(fileName);
            m_IA = new Joueur(fileName + "_IA");

            chargement=true;
        } catch (IOException exc) {
            jLabel1.setText("Erreur");
            chargement=false;
        }
        Actualisation();



    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {

        jLabel1.setText("Quel nom voulez vous donner à votre sauvegarde ?");
        fileName= in.nextLine();


        try {
            m_player.sauvegarde(fileName);
            m_IA.sauvegarde(fileName + "_IA");
            jLabel1.setText("Vous avez bien sauvegardé la partie : "+fileName);
        }
        catch (IOException exc)
        {
            System.out.println("exc");
        }



    }





    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
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
        jButton1.setText("Nouvelle partie");
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
        jButton5.setText("Charger partie");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton6.setText("Sauvegarde");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                                                        .addComponent(jButton2)
                                                        .addComponent(jButton5)
                                                        .addComponent(jButton6))

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
                                                .addComponent(jButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton6))
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
    List<Number> Selection(int sx, int sy) {

        List<Number> coordinates = new ArrayList<>();

        coordinates.add(sx);
        coordinates.add(sy);

        return coordinates;
    }


    public void TourIA(){
        System.out.println("Tour de l'IA");

        m_choixAction = (int) (Math.random() * (2 - 1 + 1) + 1);

        // choix du navire
        m_choixNavire = (int) (Math.random() * 9 + 1);

        // Réalisation de l'action
        // choix = tirer
        if (m_choixAction == 1) {
            // si le bateau peut tirer une fusée éclairante
            if (m_IA.getFlotte2(m_choixNavire).getFusee()) {
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

                    int[][]cordTir = m_IA.getFlotte1()[m_choixNavire].Tirer(m_coor, m_player.getFlotte1());


                }
            }
            // tir normal
            else {
                // choix des coordonnées aléatoires
                m_coor.setCoorX((int) (Math.random() * 14 + 1));
                m_coor.setCoorY((int) (Math.random() * 14 + 1));

                int[][]cordTir = m_IA.getFlotte1()[m_choixNavire].Tirer(m_coor, m_player.getFlotte1());

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
                        f.setColor(Color.darkGray);
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
                            if (m_coor.getTouche() && m_coor.getNavire() == 0) {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV0 = PV0+1;
                            } else if(PV0 ==0) {
                                g.setColor(Color.orange);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==1) {
                            if (m_coor.getTouche() && m_coor.getNavire() == 1) {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV1 = PV1+1;
                            } else if(PV1 ==0) {
                                g.setColor(Color.GREEN);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==2) {
                            if (m_coor.getTouche() && m_coor.getNavire() == 2) {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV2 = PV2+1;
                            } else if(PV2 ==0) {
                                g.setColor(Color.BLUE);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==3) {
                            System.out.println(m_coor.getTouche());
                            if (m_coor.getTouche() && m_coor.getNavire() == 3) {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV3 = PV3+1;
                            } else if(PV3 ==0) {
                                g.setColor(Color.YELLOW);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==4) {
                            if(m_coor.getTouche() && m_coor.getNavire()==4){
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV4 = PV4+1;
                            }
                            else if(PV4 ==0) {
                                g.setColor(Color.RED);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==5) {
                            if (m_coor.getTouche() && m_coor.getNavire() == 5) {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV5 = PV5+1;
                            } else if(PV5 ==0) {
                                g.setColor(Color.MAGENTA);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==6) {
                            if (m_coor.getTouche() && m_coor.getNavire() == 6) {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV6 = PV6+1;
                            } else if(PV6 ==0) {
                                g.setColor(Color.PINK);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                    if(P2.getCase(i,j)==7) {
                            if (m_coor.getTouche() && m_coor.getNavire() == 7)
                            {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV7 = PV7+1;
                            }
                            else if(PV7 ==0)
                            {
                                g.setColor(Color.CYAN);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);
                            }
                        }
                        if(P2.getCase(i,j)==8) {

                            if(m_coor.getTouche() && m_coor.getNavire()==8){
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV8 = PV8+1;
                            }
                            else if(PV8 ==0)
                            {
                                g.setColor(Color.DARK_GRAY);
                                g.fillRect((i * 26) + 1, (j * 26) + 1, 24, 24);

                            }
                        }
                        if(P2.getCase(i,j)==9) {
                            if(m_coor.getTouche() && m_coor.getNavire()==9)
                            {
                                g.setColor(Color.BLACK);
                                g.fillRect((sx2 * 26) + 1, (sy2 * 26) + 1, 24, 24);
                                PV9 = PV9+1;

                            }
                            else if(PV9 ==0)
                            {
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;




}


