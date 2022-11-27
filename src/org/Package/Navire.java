package org.Package;

import org.Package.Navire;
abstract public class Navire {

    /***************** Attribut ******************/
    protected int m_pv;
    protected int m_px;
    protected int m_py;
    protected int m_puissance;
    protected int m_orientation;
    protected String m_type;

    /************ Méthode **********************/
     public int Tirer()
     {
        return m_puissance;
     };

     public void RecevoirTir(int degats)
     {
         m_pv=m_pv-degats;
     }
     public void Deplacer(int dx,int dy)
     {
         // Déplacement en diago interdit à blinder
         int m_px=this.m_px+dx;
         int m_py=this.m_py+dy;
     };

     public void Afficher()
     {
         System.out.println(m_type);
         System.out.print("Puissance "+m_puissance+" PV restant "+m_pv);
         System.out.println(" Coordonnées "+m_px+";"+ m_py);
     };

}
