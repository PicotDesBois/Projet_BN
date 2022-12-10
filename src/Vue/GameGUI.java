package Vue;
import Controleur.Initialisation;
import Controleur.Joueur;

import java.util.*;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                int sx = 0;
                int sy =0;
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
                System.out.println("coordonnée en x et y " + Selection(sx,sy));
                for (int i = 0; i < 15; i++) {
                    if (x >= 563 && x <= 953 && y >= 62 && y <= 452) {

                        if ((x - 563)/26 >= i && (x - 563)/26 < i + 1) {
                            System.out.println("coordonnée en x " + (i+1));
                        }
                        if ((y - 62)/26 >= i && (y - 62)/26 < i + 1) {
                            System.out.println("coordonnée en y " + (i+1));

                        }
                    }


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

        P1.PlateauFill(m_player.getFlotte1());
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P1.getCase(i,j) >= 0){
                    f.fillRect((i*26) +1 ,(j *26) +1,  24, 24);
                }
                else{
                    f.clearRect((i*26)+1  ,(j *26) +1 ,  24, 24);
                }
            }
        }

        init.PositionAleaNavire(m_IA.getFlotte1());
        P2.PlateauFill(m_IA.getFlotte1());

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P2.getCase(i,j) >= 0){
                    g.fillRect((i*26)+1 ,(j *26)+1,  24, 24);
                }
                else{
                    g.clearRect((i*26)+1 ,(j *26)+1,  24, 24);
                }
            }
        }


    }

    Joueur m_player=new Joueur();

    Joueur m_IA=new Joueur();

    Initialisation init = new Initialisation();


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        jLabel1.setText("Veuillez selectionner un bateau de votre plateau");

        Actualisation();
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        jLabel1.setText("Veuillez selectionner un bateau de votre plateau");


        Actualisation();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        jLabel1.setText("Veuillez selectionner un bateau de votre plateau");

        Direction dir = new Direction();
        dir.setVisible(true);
        Actualisation();
    }

/*private static void Affichage2(Plateau P1){

    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15;j++) {
            if( P1.getCase(i,j) >= 0){
                f.fillRect(i*26 ,j *26,  26, 26);
            }
            else{
                f.clearRect(i*26 ,j *26,  26, 26);
            }
        }
    }

}
    private static void Affichage1(Plateau P2){

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P2.getCase(i,j) >= 0){
                    g.fillRect(i*26 ,j *26,  26, 26);
                }
                else{
                    g.clearRect(i*26 ,j *26,  26, 26);
                }
            }
        }

    }
*/





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


    List<Number> Selection(int sx, int sy) {

        List<Number> coordinates = new ArrayList<>();

        coordinates.add(sx);
        coordinates.add(sy);

        return coordinates;
    }

    public void Actualisation(){
        //init.PositionAleaNavire(m_player.getFlotte1());

        P1.PlateauFill(m_player.getFlotte1());
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P1.getCase(i,j) >= 0){
                    f.fillRect((i*26) +1 ,(j *26) +1,  24, 24);
                }
                else{
                    f.clearRect((i*26)+1  ,(j *26) +1 ,  24, 24);
                }
            }
        }

        //init.PositionAleaNavire(m_IA.getFlotte1());
        P2.PlateauFill( m_IA.getFlotte1());

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15;j++) {
                if( P2.getCase(i,j) >= 0){
                    g.fillRect((i*26)+1 ,(j *26)+1,  24, 24);
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
