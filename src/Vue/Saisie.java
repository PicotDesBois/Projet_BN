package Vue;

import java.util.Scanner;

public class Saisie {

    private String m_string;
    private int m_int;
    private Scanner m_saisie;

    public Saisie()
    {
        m_saisie=null;
        m_string=null;
        m_int=0;
    }
    public String saisirChaine()
    {
        m_saisie = new Scanner(System.in);
        m_string = m_saisie.nextLine();
        return m_string;
    }
    public int saisirEntier(int Vmin,int Vmax)
    {
        do {
            m_saisie = new Scanner(System.in);
            m_int = m_saisie.nextInt();
            if (m_int <Vmin || m_int>Vmax)
                System.out.println("Mauvaise saisie, veuillez ressayer");
        } while (m_int <Vmin || m_int>Vmax);
        return m_int;
    }
}