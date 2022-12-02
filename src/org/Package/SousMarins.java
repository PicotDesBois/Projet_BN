package org.Package;
import java.io.*;

import org.Package.Navire;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SousMarins extends Navire implements Serializable{

    public SousMarins(int px,int py, int orientation)
    {
        m_pv=1;
        m_type="SousMarins";
        m_px=px;
        m_py=py;
        m_orientation=orientation;
        m_puissance=1;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case();
    }
    public void save(String fileName) throws IOException
    {
        FileOutputStream fos;
        ObjectOutputStream oos;
        fos = new FileOutputStream("Sauvegarde/"+fileName+".txt");
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
        fis = new FileInputStream("Sauvegarde/"+fileName+".txt");
        ois = new ObjectInputStream(fis);
        tempCustomer = (Navire) ois.readObject();
        ois.close();
        return tempCustomer;
    }
}
