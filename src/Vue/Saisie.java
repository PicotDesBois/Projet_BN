package Vue;

import java.io.IOException;
import java.util.Scanner;

public class Saisie {

    /**
     * pour la saisie d'un string
     */
    private String m_string;

    /**
     * pour la saisie d'un int
     */
    private int m_int;

    /**
     * scanner pour pouvoir faire la saisie
     */
    private Scanner m_saisie;
    private Exception IOException;

    /**
     * constructeur
     */
    public Saisie()
    {
        m_saisie=null;
        m_string=null;
        m_int=0;
    }

    /**
     * saisie d'une chaine de caractère
     * @return la chaine saisie
     */
    public String saisirChaine()
    {
        m_saisie = new Scanner(System.in);
        m_string = m_saisie.nextLine();
        return m_string;
    }

    /**
     * saisie d'un entier à partir d'un charactère pour éviter les sorties de code
     * @param Vmin valeur minimale possible
     * @param Vmax valeur maximale possible
     * @return l'entier
     */
    public int saisirEntier(int Vmin,int Vmax)
    {
        m_int=-1;
        m_saisie = new Scanner(System.in);
        do {
            m_string = m_saisie.nextLine();
            try {
                m_int = Integer.parseInt(m_string);
                if (m_int <Vmin || m_int>Vmax)
                    System.out.println("Mauvaise saisie, veuillez ressayer");
            } catch (Exception test) {
                System.out.println("ERREUR saisie");
            }
        }while(m_int<Vmin||m_int>Vmax);
        return m_int;
    }
    public void ValiderTour()
    {
        m_saisie = new Scanner(System.in);
        m_string = m_saisie.nextLine();
    }

    /**
     * saisie d'un caractère pour la coordonnée
     * @return le caractère
     */
    public int saisirOrdonne ()
    {
        char ch;
        do {
            m_saisie = new Scanner(System.in);
            ch=m_saisie.next().charAt(0);
            m_int=ch;
            if (m_int <65 || m_int>79)
                System.out.println("Mauvaise saisie, veuillez ressayer");
        } while (m_int <65 || m_int>79);
        return m_int-65;
    }
}
