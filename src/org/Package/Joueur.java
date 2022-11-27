package org.Package;

import java.util.Scanner;

public class Joueur {

    private String m_pseudo;
    private Navire[] m_flotte;

    public Joueur()
    {
        m_pseudo="";

        m_flotte=new Navire[10];
        m_flotte[0]=new Cuirasse(0,0,0);
        m_flotte[1]=new Croiseur(0,0,0);
        m_flotte[2]=new Croiseur(0,0,0);
        m_flotte[3]=new Destroyer(0,0,0);
        m_flotte[4]=new Destroyer(0,0,0);
        m_flotte[5]=new Destroyer(0,0,0);
        m_flotte[6]=new SousMarins(0,0,0);
        m_flotte[7]=new SousMarins(0,0,0);
        m_flotte[8]=new SousMarins(0,0,0);
        m_flotte[9]=new SousMarins(0,0,0);
    }

    public void ChoixPseudo()
    {
        Scanner in=null;
        String temp;
        System.out.println("Saisir le pseudo du joueur : ");
        in = new Scanner(System.in);
        temp = in.nextLine();
        m_pseudo=temp;
    }

    public void Afficher()
    {
        System.out.println("Pseudo : "+m_pseudo);
        System.out.println("Flotte : ");
        for (int i=0;i<10;i++)
        {
            m_flotte[i].Afficher();
        }
    }

    public String getPseudo()
    {
        return m_pseudo;
    }
    public void setPseudo(String temp)
    {
        m_pseudo=temp;
    }

    public Navire[] getFlotte1() { return m_flotte; }

    public Navire getFlotte2(int i) { return m_flotte[i]; }
    public void setFlotte(Navire temp, int i)
    {
        m_flotte[i]=temp;
    }

}
