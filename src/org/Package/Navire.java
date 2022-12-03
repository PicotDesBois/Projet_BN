package org.Package;

abstract public class Navire {

    /***************** Attribut ******************/
    protected int m_pv;
    protected int m_puissance;
    // 1 = horizontale
    // 2 = verticale
    protected int m_orientation;
    protected String m_type;

    protected Case[] m_cases;

    protected boolean m_fusee;

    /************ Méthode **********************/
    public void Tirer(Case coordonnee,Navire []flotte)
    {
        System.out.println("Vous tirez sur les cord("+coordonnee.getCoorX()+";"+coordonnee.getCoorY()+")");
        int [][]tir=new int[m_puissance][2];
        if(m_puissance==1||m_puissance==4||m_puissance==9)
        {
            tir[0][0]=coordonnee.getCoorX();
            tir[0][1]=coordonnee.getCoorY();
        }
        if(m_puissance==4||m_puissance==9)
        {
            // Case 2
            tir[1][0]=coordonnee.getCoorX()+1;
            tir[1][1]=coordonnee.getCoorY();
            // Case 3
            tir[2][0]=coordonnee.getCoorX();
            tir[2][1]=coordonnee.getCoorY()+1;
            // Case 4
            tir[3][0]=coordonnee.getCoorX()+1;
            tir[3][1]=coordonnee.getCoorY()+1;
        }
        if(m_puissance==9)
        {
            // Case 5
            tir[4][0]=coordonnee.getCoorX()-1;
            tir[4][1]=coordonnee.getCoorY()-1;
            // Case 6
            tir[5][0]=coordonnee.getCoorX()-1;
            tir[5][1]=coordonnee.getCoorY();
            // Case 7
            tir[6][0]=coordonnee.getCoorX()-1;
            tir[6][1]=coordonnee.getCoorY()+1;
            // Case 8
            tir[7][0]=coordonnee.getCoorX();
            tir[7][1]=coordonnee.getCoorY()-1;
            // Case 9
            tir[4][0]=coordonnee.getCoorX()+1;
            tir[4][1]=coordonnee.getCoorY()-1;
        }
        // parcours de toute la flotte adverse
        for(int i=0;i< flotte.length;i++)
        {   // parcours de toutes les cases d'un navire
            for(int j=0;j<flotte[i].m_pv;j++)
            {   // parcours des cases touché
                for(int k=0;k<m_puissance;k++)
                {
                    if(flotte[i].m_cases[j].getCoorX()==tir[k][0]&&flotte[i].m_cases[j].getCoorY()==tir[k][1])
                    {
                        flotte[i].m_cases[j].setTouche(true);
                        System.out.println("Touche en ("+flotte[i].m_cases[j].getCoorX()+";"+flotte[i].m_cases[j].getCoorY()+")");
                    }
                }
            }
        }
        //flotte[0].Afficher();
    }

     public void RecevoirTir(int degats)
     {
         m_pv=m_pv-degats;
     }

     public void Deplacer(int choix, Navire[] flotte,int numNavire)
     {
         int temp;
         boolean OnPeutDeplacer=true;

         // déplacer le navire : haut 1 , bas 2, droite 3, gauche 4
         if (choix == 1)
         {
             // haut : -1 en y
             // vérifier si c'est possible
             if (m_cases[0].getCoorY()==0)
             {
                 OnPeutDeplacer=false;
             }
             // si le bateau est à l'horizontale
             if (m_orientation==1)
             {
                 // pour chaque "nouvelle" case du bateau vérifier si il y a un bateau sur le bateau
             }
             // si le bateau est à la verticale
             else if (m_orientation==2)
             {
                 // si la nouvelle premiere case du bateau a deja un bateau dessus
                 // oui, impossible
             }

             if (OnPeutDeplacer==true)
             {
                 // possible, changer les coordonnées du bateau
                 for (int i=0;i<m_pv;i++)
                 {
                     temp=m_cases[i].getCoorY();
                     m_cases[i].setCoorY(temp-1);
                 }
             }

         }
         else if (choix == 2)
         {
             // bas : +1 en y
             // vérifier si c'est possible
             if (m_cases[m_pv].getCoorY() == 14)
             {
                 OnPeutDeplacer=false;
             }
             // si le bateau est à l'horizontale
             if (m_orientation==1)
             {
                 // pour chaque "nouvelle" case du bateau vérifier si il y a un bateau sur le bateau
             }
             // si le bateau est à la verticale
             else if (m_orientation==2)
             {
                 // si la nouvelle derniere case du bateau a deja un bateau dessus
                 // oui, impossible
             }

             if (OnPeutDeplacer==true)
             {
                 // possible, changer les coordonnées du bateau
                 for (int i=0;i<m_pv;i++)
                 {
                     temp=m_cases[i].getCoorY();
                     m_cases[i].setCoorY(temp+1);
                 }
             }
         }
         else if (choix == 3)
         {
             // droite : +1 en x
             // vérifier si c'est possible
             if (m_cases[m_pv].getCoorX() == 14)
             {
                 OnPeutDeplacer=false;
             }
             // si le bateau est à l'horizontale
             if (m_orientation==1)
             {
                 // si la nouvelle derniere case du bateau a deja un bateau dessus
                 // oui, impossible
             }
             // si le bateau est à la verticale
             else if (m_orientation==2)
             {
                 // pour chaque "nouvelle" case du bateau vérifier si il y a un bateau sur le bateau
             }
             if (OnPeutDeplacer==true)
             {
                 // possible, changer les coordonnées du bateau
                 for (int i=0;i<m_pv;i++)
                 {
                     temp=m_cases[i].getCoorX();
                     m_cases[i].setCoorX(temp+1);
                 }
             }
         }
         else
         {
             // droite : -1 en x
             // vérifier si c'est possible
             if (m_cases[0].getCoorX() == 0)
             {
                 OnPeutDeplacer=false;
             }
             // si le bateau est à l'horizontale
             if (m_orientation==1)
             {
                 // si la nouvelle premiere case du bateau a deja un bateau dessus
                 // oui, impossible
             }
             // si le bateau est à la verticale
             else if (m_orientation==2)
             {
                 // pour chaque "nouvelle" case du bateau vérifier si il y a un bateau sur le bateau
             }
             else
             {
                 // possible, changer les coordonnées du bateau
                 for (int i=0;i<m_pv;i++)
                 {
                     temp=m_cases[i].getCoorX();
                     m_cases[i].setCoorX(temp-1);
                 }
             }
         }
     };

     public void Afficher()
     {
         System.out.println(m_type);
         System.out.print("Puissance "+m_puissance+" PV restant "+m_pv);

         if (m_fusee)
             System.out.println("Le navire peut tirer une fusee eclairante");
         else
             System.out.println("Le navire ne peut pas tirer de fusee eclairante");

         for (int i=0;i<m_pv;i++)
            System.out.println("Case : ( "+m_cases[i].getCoorX()+" ; "+m_cases[i].getCoorY()+" )"+m_cases[i].getTouche());
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

    public int getOrientation()
    {
        return m_orientation;
    }
    public void setOrientation(int temp)
    {
        m_orientation=temp;
    }

    public Case[] getCase() { return m_cases; }
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
