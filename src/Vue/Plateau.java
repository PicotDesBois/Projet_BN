package Vue;
import Model.Case;
import Model.Navire;

public class Plateau extends Affichage {

    /**
     * nombre de lignes et de colonnes du plateau
     */
    private int nbLigne;
    private int nbColonne;

    /**
     * le plateau de int
     * -1 pas de bateau
     * autre, numero du bateau
     */
    private int[][] plateau;

    /**
     * actualisation du plateau avec les nouvelles coordonnées des bateaux
     * @param listeBateaux flotte du joueur
     */
    public void PlateauFill(Navire[] listeBateaux){

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

    /**
     * constructeur
     * @param n lignes
     * @param p colonnes
     */
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

    /**
     * afficher les bateaux en couleur en fonction de leur type
     * croix rouge, s'ils sont touchés sur cette case
     */
    public void afficherCouleur(Navire[] flotte, int ligne, int colonne)
    {
        int i=ligne;
        int j=colonne;
        boolean touche=false;

        for (int k=0; k<10;k++)
            for (int l = 0; l  < flotte[k].getPV() ;l++) {
                if (i == flotte[k].getCase()[l].getCoorY() && j == flotte[k].getCase()[l].getCoorX() && flotte[k].getCase()[l].getTouche())
                    touche = true;
            }
        if (plateau[i][j]==0 && touche)
            System.out.print(" | " + ANSI_RED + ANSI_CYAN_BG+ "X" + ANSI_RESET);
        else if  ((plateau[i][j]==1 || plateau[i][j]==2) && touche)
            System.out.print(" | " + ANSI_RED + ANSI_GREEN_BG + "X" + ANSI_RESET);
        else if  ((plateau[i][j]==3 || plateau[i][j]==4|| plateau[i][j]==5)&& touche)
            System.out.print(" | " + ANSI_YELLOW_BG + ANSI_RED + "X" + ANSI_RESET);
        else if  ((plateau[i][j]==6 || plateau[i][j]==7 || plateau[i][j]==8||plateau[i][j]==9)&& touche)
            System.out.print(" | " + ANSI_RED + ANSI_MAGENTA_BG + "X" + ANSI_RESET);
        else if (plateau[i][j]==0 && !touche)
            System.out.print(" | " + ANSI_BLACK + ANSI_CYAN_BG+ plateau[i][j] + ANSI_RESET);
        else if  ((plateau[i][j]==1 || plateau[i][j]==2) && !touche)
            System.out.print(" | " + ANSI_BLACK + ANSI_GREEN_BG + plateau[i][j] + ANSI_RESET);
        else if  ((plateau[i][j]==3 || plateau[i][j]==4|| plateau[i][j]==5)&& !touche)
            System.out.print(" | " + ANSI_YELLOW_BG + ANSI_BLACK + plateau[i][j] + ANSI_RESET);
        else if  ((plateau[i][j]==6 || plateau[i][j]==7 || plateau[i][j]==8||plateau[i][j]==9)&& !touche)
            System.out.print(" | " + ANSI_BLACK + ANSI_MAGENTA_BG + plateau[i][j] + ANSI_RESET);
        touche=false;
    }

    /**
     * afficher le plateau du joueur
     * @param flotte du joueur
     */
    public void afficher(Navire[] flotte) {
        nbLigne = 15;
        nbColonne = 15;
        char ascii;
        int temp=0;
        System.out.println();

        System.out.print("   ");
        for(int j=0;j<nbColonne;j++) {
            System.out.print("| " +j);
            if (j < 10)
                System.out.print(" ");
        }
        System.out.println("|");
        for (int i = 0; i < nbLigne; i++) {
            temp=i+65;
            ascii=(char) temp;
            System.out.print(ascii+" ");

            for (int j = 0; j < nbColonne;j++) {
                if(plateau[i][j]==-1)
                    System.out.print(" | " + " ");
                else
                    afficherCouleur(flotte,i,j);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }

    /**
     * afficher un plateau avec seulement le tir de la fusée éclairante
     * @param cordTir coordonnée du tir
     * @param flotte du joueur (adversaire)
     */
    public void afficherFusee(int[][]cordTir,Navire[] flotte)
    {
        nbLigne = 15;
        nbColonne = 15;
        int marqueur;
        boolean touche=false;
        char ascii;
        int temp=0;

        System.out.println();

        System.out.print("   ");
        for(int j=0;j<nbColonne;j++) {
            System.out.print("| " +j);
            if (j < 10)
                System.out.print(" ");
        }
        System.out.println("|");
        for (int i = 0; i < nbLigne; i++) {
            temp=i+65;
            ascii=(char) temp;
            System.out.print(ascii+" ");
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
                {
                    afficherCouleur(flotte,i,j);
                }
                else
                System.out.print(" | " + " ");
            }
            System.out.println(" | ");
        }
        System.out.println();
    }

    /**
     * afficher un plateau avec seulement le tir normal
     * @param cordTir coordonnée du tir
     * @param flotte du joueur (adversaire)
     * @param num_navire numéro du navire pour savoir son type
     */
    public void afficherTir(int[][]cordTir,Navire[] flotte,int num_navire) {
        nbLigne = 15;
        nbColonne = 15;
        int marqueur;
        boolean touche = false;
        char ascii;
        int temp = 0;

        System.out.println();

        System.out.print("   ");
        for (int j = 0; j < nbColonne; j++) {
            System.out.print("| " + j);
            if (j < 10)
                System.out.print(" ");
        }
        System.out.println("|");

        for (int i = 0; i < nbLigne; i++) {
            temp = i + 65;
            ascii = (char) temp;
            System.out.print(ascii + " ");
            for (int j = 0; j < nbColonne; j++) {
                marqueur = 0;
                for (int k = 0; k < cordTir.length; k++) {
                    if (j == cordTir[k][0] && i == cordTir[k][1]) {
                        marqueur=1;
                        // parcours de toute la flotte adverse
                        for (Navire navire : flotte) {   // parcours de toutes les cases d'un navire
                            for (int l = 0; l < navire.getPV(); l++) {   // parcours des cases touché
                                if (navire.geType().equals("SousMarins") && j == navire.getCase()[l].getCoorX() && i == navire.getCase()[l].getCoorY() && !flotte[num_navire].geType().equals("SousMarins"))
                                    marqueur = 2;
                            }
                            }
                        }
                    }
                    if (plateau[i][j] == -1 && marqueur == 1)
                        System.out.print(" | " + "-");
                    else if (marqueur == 2)
                        System.out.print(" | " + "-");
                    else if (marqueur == 1)
                        afficherCouleur(flotte, i, j);
                    else
                        System.out.print(" | " + " ");
                }
                System.out.println(" | ");
            }
            System.out.println();
    }

    /**
     * acsesseurs
     */
    public int getCase(int x, int y)
    {
        return plateau[y][x];
    }
}

