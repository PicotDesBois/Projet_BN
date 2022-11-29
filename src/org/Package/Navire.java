package org.Package;
import java.io.*;
import org.Package.Navire;
abstract public class Navire implements Serializable {

    /***************** Attribut ******************/
    protected int m_pv;
    protected int m_px;
    protected int m_py;
    protected int m_puissance;
    protected int m_orientation;
    protected String m_type;

    protected Case[] m_cases;

    /*************** Méthode **********************/
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

    /************* Sauvegarde ****************************/
    public void save(String fileName) throws IOException
    {
        FileOutputStream fos;
        ObjectOutputStream oos;
        fos = new FileOutputStream(fileName);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static Navire load(String fileName) throws
            IOException, ClassNotFoundException
    {
        FileInputStream fis;
        ObjectInputStream ois;
        Navire tempCustomer = null;
        fis = new FileInputStream(fileName);
        ois = new ObjectInputStream(fis);
        tempCustomer = (Navire) ois.readObject();
        ois.close();
        return tempCustomer;
    }

}
