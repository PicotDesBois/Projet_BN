package org.Package;
import org.Package.Navire;

public class Croiseur extends Navire{
    public Croiseur(int px,int py, int orientation)
    {
        System.out.println("Croiseur constructeur");
        m_pv=5;
        m_type="Croiseur";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=4;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case();
    }

}
