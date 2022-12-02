package org.Package;
import org.Package.Navire;

public class Destroyer extends Navire {
    private int m_fusee;

    public Destroyer(int px, int py, int orientation)
    {
        System.out.println("Destroyer constructeur");
        m_pv=3;
        m_type="Destroyer";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=1;
        m_fusee=1;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case();
    }
    public void setFusee(int fusee) {
        this.m_fusee = fusee;
    }
}
