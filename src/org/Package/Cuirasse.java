package org.Package;
import org.Package.Navire;
public class Cuirasse extends Navire{
    public Cuirasse(int px,int py, int orientation)
    {
        m_pv=7;
        m_type="Cuirasse";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=9;
    }
}
