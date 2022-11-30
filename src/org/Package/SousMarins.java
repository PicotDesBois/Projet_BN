package org.Package;

import org.Package.Navire;
public class SousMarins extends Navire{

    public SousMarins(int px,int py, int orientation)
    {
        m_pv=1;
        m_type="SousMarins";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=1;

        m_fusee=false;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case();
    }
}
