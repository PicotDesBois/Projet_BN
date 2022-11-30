package org.Package;
import org.Package.Navire;

public class Destroyer extends Navire {
    public Destroyer(int px, int py, int orientation)
    {
        m_pv=3;
        m_type="Destroyer";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=1;
        m_fusee=true;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case();
    }
}
