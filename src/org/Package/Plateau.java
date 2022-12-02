package org.Package;
import java.util.*;

public class Plateau{

    /* en attendant la version graphique j'ai fais une version console*/

    private int nbLigne;
    private int nbColonne;
    private int[][] plateau;

        public Plateau(int n, int p, Navire[] listeBateaux) {
            nbLigne = n;
            nbColonne = p;
            plateau = new int[nbLigne][nbColonne];

            for (int i = 0; i < nbLigne; i++) {
                for (int j = 0; j < nbColonne;j++) {
                    for (int k = 0; k<10; k++){
                        for (int r = 0; r   < listeBateaux[k].getPV() ;r++) {
                            if (i == listeBateaux[k].getCase()[r].getCoorX() && j == listeBateaux[k].getCase()[r].getCoorY()){
                                plateau[i][j] = k;
                            }
                        else{
                                plateau[i][j] = -1;
                            }
                        }
                    }
                }
            }
        }




        public void afficher() {
        System.out.println();
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne;j++) {
                plateau[i][j] = 'x';
                System.out.print(" | " + plateau[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }
}
