package org.Package;
public class Cuirasse extends Navire{
    public Cuirasse()
    {
        m_pv=7;
        m_type="Cuirasse";
        m_orientation=0;
        m_puissance=9;

        m_fusee=false;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case(0,0,0,false);

        m_coule=false;
    }

    public Cuirasse(int orientation)
    {
        m_pv=7;
        m_type="Cuirasse";
        m_orientation=orientation;
        m_puissance=9;

        m_fusee=false;
        m_cases=new Case[m_pv];
    }

    @Override
    public int[][] tirFusee(Case m_coor, Navire[] flotte1) {
        System.out.println("Un "+m_type+" ne peut pas tirer de fusée écalirante");
        return null;
    }
}
