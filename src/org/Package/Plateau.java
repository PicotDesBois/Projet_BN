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
                        if (j == listeBateaux[k].getCase()[r].getCoorX() && i == listeBateaux[k].getCase()[r].getCoorY()){
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
                if(plateau[i][j]==-1)
                    System.out.print(" | " + " ");
                else
                    System.out.print(" | " + plateau[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }
    public void afficherFusee(int[][]cordTir)
    {
        nbLigne = 15;
        nbColonne = 15;
        int marqueur;
        System.out.println();
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne;j++) {
                marqueur=0;
                for(int k=0;k<cordTir.length;k++)
                {
                    if(i==cordTir[k][0]&&j==cordTir[k][1])
                    {
                        marqueur=1;
                    }
                }
                if(plateau[i][j]==-1&&marqueur==1)
                    System.out.print(" | " + "-");
                else if (marqueur==1)
                    System.out.print(" | " + plateau[i][j]);
                else
                System.out.print(" | " + " ");
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

