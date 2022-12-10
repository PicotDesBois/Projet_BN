package Model;

public class SousMarins extends Navire{

    public SousMarins()
    {
        m_pv=1;
        m_type="SousMarins";
        m_orientation=0;
        m_puissance=1;

        m_fusee=false;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case(0,0,0,false);

        m_coule=false;
    }
    public SousMarins(int orientation,boolean coule)
    {
        m_pv=1;
        m_type="SousMarins";
        m_orientation=orientation;
        m_puissance=1;
        m_coule=coule;
        m_fusee=false;

        m_cases=new Case[m_pv];
    }
    @Override
    public int[][] tirFusee(Case m_coor, Navire[] flotte1){
        //System.out.println("Un "+m_type+" ne peut pas tirer de fusée éclairante");
        return null;
    }
    public void Tirer(Case coordonnee,Navire []flotte)
    {
        //System.out.println("Vous tirez sur les cord("+coordonnee.getCoorX()+";"+coordonnee.getCoorY()+")");
        int [][]tir=new int[m_puissance][2];
        if(m_puissance==1||m_puissance==4||m_puissance==9)
        {
            tir[0][0]=coordonnee.getCoorX();
            tir[0][1]=coordonnee.getCoorY();
        }
        if(m_puissance==4||m_puissance==9)
        {
            // Case 2
            tir[1][0]=coordonnee.getCoorX()+1;
            tir[1][1]=coordonnee.getCoorY();
            // Case 3
            tir[2][0]=coordonnee.getCoorX();
            tir[2][1]=coordonnee.getCoorY()+1;
            // Case 4
            tir[3][0]=coordonnee.getCoorX()+1;
            tir[3][1]=coordonnee.getCoorY()+1;
        }
        if(m_puissance==9)
        {
            // Case 5
            tir[4][0]=coordonnee.getCoorX()-1;
            tir[4][1]=coordonnee.getCoorY()-1;
            // Case 6
            tir[5][0]=coordonnee.getCoorX()-1;
            tir[5][1]=coordonnee.getCoorY();
            // Case 7
            tir[6][0]=coordonnee.getCoorX()-1;
            tir[6][1]=coordonnee.getCoorY()+1;
            // Case 8
            tir[7][0]=coordonnee.getCoorX();
            tir[7][1]=coordonnee.getCoorY()-1;
            // Case 9
            tir[4][0]=coordonnee.getCoorX()+1;
            tir[4][1]=coordonnee.getCoorY()-1;
        }
        // parcours de toute la flotte adverse
        for (Navire navire : flotte) {   // parcours de toutes les cases d'un navire
            for (int j = 0; j < navire.m_pv; j++) {   // parcours des cases touché
                for (int k = 0; k < m_puissance; k++) {
                    if (navire.m_cases[j].getCoorX() == tir[k][0] && navire.m_cases[j].getCoorY() == tir[k][1]) {
                        navire.m_cases[j].setTouche(true);
                    }
                }
            }
        }
    }
}