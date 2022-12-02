package org.Package;
import java.util.*;

public class Plateau {

    /* en attendant la version graphique j'ai fais une version console*/

    private int nbLigne;
    private int nbColonne;
    private char[][] plateau;

        public Plateau(int n, int p) {
            nbLigne = n;
            nbColonne = p;
            plateau = new char[nbLigne][nbColonne];

            for (int i = 0; i < nbLigne; i++) {
                for (int j = 0; j < nbColonne;j++) {
                    for (int k = 0; k<10; k++){
                        if (i == Case.(k) && j == (k)){
                            plateau[i][j] = 1;
                        }
                        else {
                            plateau[i][j] = 0;
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
