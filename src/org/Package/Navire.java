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

    protected Case[] m_cases;

    protected boolean m_fusee;

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

         if (m_fusee)
             System.out.println("Le navire peut tirer une fusee eclairante");
         else
             System.out.println("Le navire ne peut pas tirer de fusee eclairante");

         for (int i=0;i<m_pv;i++)
            System.out.println("Case : ( "+m_cases[i].getCoorX()+" ; "+m_cases[i].getCoorY()+" )");
     };

    /************ Setter & Getter **********************/

    public int getPV()
    {
        return m_pv;
    }
    public void setPV(int temp)
    {
        m_pv=temp;
    }

    public int getPX()
    {
        return m_px;
    }
    public void setPX(int temp)
    {
        m_px=temp;
    }

    public int getPY()
    {
        return m_py;
    }
    public void setPY(int temp)
    {
        m_py=temp;
    }

    public int getOrientation()
    {
        return m_orientation;
    }
    public void setOrientation(int temp)
    {
        m_orientation=temp;
    }

    public Case getCase(int i) { return m_cases[i]; }
    public void setCase(Case temp, int i)
    {
        m_cases[i]=temp;
    }

    public boolean getFusee()
    {
        return m_fusee;
    }
    public void setFusee(boolean temp)
    {
        m_fusee=temp;
    }

}
