package Model;

public class Destroyer extends Navire {

    /**
     * constructeur
     */
    public Destroyer()
    {
        m_pv=3;
        m_type="Destroyer";
        m_orientation=0;
        m_puissance=1;
        m_fusee=true;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case(0,0,0,false);

        m_coule=false;
    }

    /**
     * consctructeur pour la reprise de partie
     * @param orientation orientation du navire
     * @param fusee état de la fusée : false plus de fusée, true fusée
     * @param coule navire coulé ou non
     */
    public Destroyer(int orientation,boolean fusee,boolean coule)
    {
        m_pv=3;
        m_type="Destroyer";
        m_orientation=orientation;
        m_puissance=1;
        m_fusee=fusee;
        m_coule=coule;
        m_cases=new Case[m_pv];
    }

    /**
     * méthode pour tirer la fusée éclairante : redefinition de la méthode depuis la classe mère
     * @param coordonnee coordonnée du tir
     * @param flotte flotte de l'adversaire
     * @return le tableau de cases touchées par la fusée
     */
    public int[][] tirFusee(Case coordonnee, Navire []flotte)
    {
        int [][]tir=new int[4][2];

            // Case 1
            tir[0][0]=coordonnee.getCoorX();
            tir[0][1]=coordonnee.getCoorY();
            // Case 2
            tir[1][0]=coordonnee.getCoorX()+1;
            tir[1][1]=coordonnee.getCoorY();
            // Case 3
            tir[2][0]=coordonnee.getCoorX();
            tir[2][1]=coordonnee.getCoorY()+1;
            // Case 4
            tir[3][0]=coordonnee.getCoorX()+1;
            tir[3][1]=coordonnee.getCoorY()+1;
            m_fusee=false;
       return tir;
    }

}
