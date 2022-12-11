package Vue;
import Model.Case;
import Model.Navire;

public class Plateau extends Affichage {


    private int nbLigne;
    private int nbColonne;
    private int[][] plateau;

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

    public int getCase(int x, int y)
    {
        return plateau[y][x];
    }
    public void setCase(int x, int y, int value)
    {
        plateau[y][x]=value;
    }
}

