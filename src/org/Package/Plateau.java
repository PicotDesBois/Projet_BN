package org.Package;
import Model.Navire;

public class Plateau{

    /* en attendant la version graphique j'ai fais une version console*/

    //font color
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //background color
    public static final String ANSI_RED_BG = "\033[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";
    public static final String ANSI_BLUE_BG = "\u001B[44m";
    public static final String ANSI_MAGENTA_BG = "\u001B[45m";
    public static final String ANSI_CYAN_BG = "\u001B[46m";
    public static final String ANSI_WHITE_BG = "\u001B[47m";

    private int nbLigne;
    private int nbColonne;
    private int[][] plateau;

    public void PlateauFill( Plateau plateau1, Navire[] listeBateaux){


        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne;j++) {
                plateau[i][j] = -1;
                for (int k = 0; k<10; k++){
                    for (int r = 0; r   < listeBateaux[k].getPV() ;r++) {
                        if (i == listeBateaux[k].getCase()[r].getCoorY() && j == listeBateaux[k].getCase()[r].getCoorX()){
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
                else if (plateau[i][j]==0)
                    System.out.print(" | " + ANSI_RED + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==1)
                    System.out.print(" | " + ANSI_GREEN + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==2)
                    System.out.print(" | " + ANSI_CYAN  + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==3)
                    System.out.print(" | " + ANSI_YELLOW  + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==4)
                    System.out.print(" | " + ANSI_BLUE + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==5)
                    System.out.print(" | " + ANSI_PURPLE +  plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==6)
                    System.out.print(" | " + ANSI_BLACK + ANSI_YELLOW_BG + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==7)
                    System.out.print(" | " + ANSI_BLACK + ANSI_MAGENTA_BG + plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==8)
                    System.out.print(" | " + ANSI_BLACK + ANSI_GREEN_BG +plateau[i][j] + ANSI_RESET);
                else if  (plateau[i][j]==9)
                    System.out.print(" | " + ANSI_BLACK + ANSI_BLUE_BG +plateau[i][j] + ANSI_RESET);
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
                    if(j==cordTir[k][0]&&i==cordTir[k][1])
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
        return plateau[y][x];
    }
    public void setCase(int x, int y, int value)
    {
        plateau[y][x]=value;
    }
}

