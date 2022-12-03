package org.Package;

public class Destroyer extends Navire {
    public Destroyer()//Consctruteur par défaut
    {
        m_pv=3;
        m_type="Destroyer";
        m_orientation=0;
        m_puissance=1;
        m_fusee=true;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case(0,0,0,false);
    }

    public Destroyer(int orientation,boolean fusee) // Constructeur pour sauvegarde
    {
        m_pv=3;
        m_type="Destroyer";
        m_orientation=orientation;
        m_puissance=1;
        m_fusee=fusee;

        m_cases=new Case[m_pv];
    }
}
