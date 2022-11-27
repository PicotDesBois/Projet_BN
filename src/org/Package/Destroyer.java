package org.Package;
import org.Package.Navire;

public class Destroyer extends Navire {
    private int fusee;
    public Destroyer(int px,int py, int orientation)
    {
        m_pv=3;
        m_type="Destroyer";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=1;
        fusee=1;
    }
}
