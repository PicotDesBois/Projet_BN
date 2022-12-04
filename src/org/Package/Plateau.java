package org.Package;
import java.util.ArrayList;

public class Plateau{

    /* en attendant la version graphique j'ai fais une version console*/

    private int nbLigne;
    private int nbColonne;
    private int[][] plateau;

        public void PlateauFill( Plateau plateau1, Navire[] listeBateaux){


            for (int i = 0; i < nbLigne; i++) {
                for (int j = 0; j < nbColonne;j++) {
                    plateau[i][j] = -1;
                    for (int k = 0; k<10; k++){
                        for (int r = 0; r   < listeBateaux[k].getPV() ;r++) {
                            if (i == listeBateaux[k].getCase()[r].getCoorX() && j == listeBateaux[k].getCase()[r].getCoorY()){
                                plateau[i][j] = k;
                            }
                        }
                    }
                }
            }

        }

        public Plateau(int n, int p) {
            nbLigne = n;
            nbColonne = p;
            plateau = new int[nbLigne][nbColonne];

            for (int i = 0; i < nbLigne; i++) {
                for (int j = 0; j < nbColonne;j++) {
                                plateau[i][j] = -1;
                            }
                        }
                    }

        public void afficher() {
            nbLigne = 15;
            nbColonne = 15;
        System.out.println();
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne;j++) {
                    System.out.print(" | " + plateau[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }
    public int getCase(int x, int y)
    {
        return plateau[x][y];
    }
    public void setCase(int x, int y, int value)
    {
        plateau[x][y]=value;
    }
}

