package org.Package;

public class Initialisation {

    // mettre en parametre la liste des bateaux du joueur
    public void PositionAleaNavire()
    {
        // nb de case pour le test => a remplacer par les pv du bateau
        int pv=5;

        int coorX,coorY,direction;

        // pour chaque navire du joueur
        for (int i=0;i<10;i++)
        {
            System.out.println("Navire "+i);

            Case[] tab=new Case[pv];
            for(int k=0;k<pv;k++)
                tab[k]=new Case();

            // choisir la direction du bateau aléatoirement
            // 1 = horizontale
            // 2 = verticale
            direction = (int) ((Math.random() * ((2 - (-1)) + 1)) + (-1));

            // si le bateau est à l'horizontale
            if (direction==1)
            {
                do {
                        // choisir la coordonnée x de la premiere case du bateau
                        coorX = (int) ((Math.random() * (14 +1)));

                        // choisir la coordonnée x de la premiere case du bateau
                        coorY = (int) ((Math.random() * (14 +1)));
                }while (coorX>=(14-pv));
            }
            // si le bateau est à la verticale
            else
            {
                do {
                    // choisir la coordonnée x de la premiere case du bateau
                    coorX = (int) ((Math.random() * (14 +1)));

                    // choisir la coordonnée x de la premiere case du bateau
                    coorY = (int) ((Math.random() * (14 +1)));
                }while (coorY>=(14-pv));
            }

            // trouver les cases suivantes du bateau
            for (int j=0;j<pv;j++)
            {
                // si le bateau est à l'horizonale
                if (direction==1)
                {
                    tab[j].setCoorX(coorX+j);
                    tab[j].setCoorY(coorY);

                    // a changer avec le numéro du bateau
                    tab[j].setNavire(1);

                    // mettre le tableau dans la classe navire
                }
                // si le bateau est à la verticale
                else
                {
                    tab[j].setCoorX(coorX);
                    tab[j].setCoorY(coorY+j);

                    // a changer avec le numéro du bateau
                    tab[j].setNavire(1);

                    // mettre le tableau dans la classe navire
                }

                tab[j].Afficher();
            }
            System.out.println();
        }
    }
}
