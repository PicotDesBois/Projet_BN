package org.Package;

public class Case { // Constructeur par défaut

    // coordonnees de la case
    private int m_coorX;
    private int m_coorY;

    // le bateau sur la case est touché ou pas
    private boolean m_touche;

    // 0 pas de bateau
    // sinon numero du bateau du joueur
    private int m_navire;

    public Case(int navire,int px, int py, boolean touche) // constructeur pour sauvegarde
    {
        m_navire=navire;
        m_coorX=px;
        m_coorY=py;
        m_touche=touche;
    }

    public int getCoorX()
    {
        return m_coorX;
    }
    public void setCoorX(int temp)
    {
        m_coorX=temp;
    }

    public int getCoorY()
    {
        return m_coorY;
    }
    public void setCoorY(int temp)
    {
        m_coorY=temp;
    }

    public int getNavire()
    {
        return m_navire;
    }
    public void setNavire(int temp)
    {
        m_navire=temp;
    }

    public boolean getTouche()
    {
        return m_touche;
    }
    public void setTouche(boolean temp)
    {
        m_touche=temp;
    }

    public void Afficher()
    {
            System.out.println("Case : ( "+m_coorX+" ; "+m_coorY+" )");
            System.out.println("Touche : "+m_touche);
            System.out.println("Navire : "+m_navire);
    }
}
