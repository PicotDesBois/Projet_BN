package org.Package;
import org.Package.Navire;

public class Croiseur extends Navire{
    public Croiseur(int px,int py, int orientation)
    {
        m_pv=5;
        m_type="Croiseur";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=4;
    }
}
