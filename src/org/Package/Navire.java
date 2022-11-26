package org.Package;

import org.Package.Navire;
abstract public class Navire {

    // Attribut
    protected int m_pv;
    protected int m_px;
    protected int m_py;
    protected int m_puissance;
    protected int m_orientation;
    protected String m_type;

    // Méthode
     public void Tirer(){};
     public void Deplacer(int new_x,int new_y)
     {
         int m_px=new_x;
         int m_py=new_y;
     };

     public void Afficher()
     {
         System.out.println(m_type);
         System.out.print("Puissance "+m_puissance+" PV restant "+m_pv);
         System.out.println(" Coordonnées "+m_px+";"+ m_py);
     };
}
