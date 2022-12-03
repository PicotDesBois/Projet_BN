package org.Package;

public class Croiseur extends Navire{
    public Croiseur()
    {
        m_pv=5;
        m_type="Croiseur";
        m_orientation=0;
        m_puissance=4;

        m_fusee=false;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case(0,0,0,false);
    }

    public Croiseur(int orientation)
    {
        m_pv=5;
        m_type="Croiseur";
        m_orientation=orientation;
        m_puissance=4;

        m_fusee=false;

        m_cases=new Case[m_pv];
    }

}
