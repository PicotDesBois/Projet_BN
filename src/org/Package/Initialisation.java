package org.Package;

public class Initialisation {

    public boolean VerifNavire (Navire flotte[], int numNavire)
    {
        boolean Chevauchement=false;
        // pour tous les bateaux pour lesquels les cases ont deja été choisies

        if (numNavire==0)
        {
            return false;
        }
        else {
            // pour chaque navire
            for (int i=0; i<numNavire;i++)
            {
                // pour chaque case de ce bateau
                for (int j=0;j<flotte[numNavire].getPV();j++)
                {
                    for (int k=0;k<flotte[i].getPV();k++)
                    {
                        if (flotte[numNavire].getCase()[j].getCoorX()==flotte[i].getCase()[k].getCoorX() && flotte[numNavire].getCase()[j].getCoorY()==flotte[i].getCase()[k].getCoorY())
                            Chevauchement=true;
                    }
                }
            }
            return Chevauchement;
        }
    }

    public void PositionAleaNavire(Navire flotte[])
    {
        // nb de case pour le test => a remplacer par les pv du bateau
        //int pv=5;

        int coorX,coorY,direction;
        boolean Chevauchement=false;

        // pour chaque navire du joueur
        for (int i=0;i<10;i++)
        {
            // création du tableau de cases temporaire
            Case[] tab=new Case[flotte[i].getPV()];
            for(int k=0;k<flotte[i].getPV();k++)
                tab[k]=new Case();

            // choisir la direction du bateau aléatoirement
            // 1 = horizontale
            // 2 = verticale

            do {
            direction = (int) ((Math.random() * ((2 - (-1)) + 1)) + (-1));

            // si le bateau est à l'horizontale
            if (direction==1)
            {
                do {
                        // choisir la coordonnée x de la premiere case du bateau
                        coorX = (int) ((Math.random() * (14 +1)));

                        // choisir la coordonnée x de la premiere case du bateau
                        coorY = (int) ((Math.random() * (14 +1)));
                }while (coorX>=(14-flotte[i].getPV()));
            }
            // si le bateau est à la verticale
            else
            {
                do {
                    // choisir la coordonnée x de la premiere case du bateau
                    coorX = (int) ((Math.random() * (14 +1)));

                    // choisir la coordonnée x de la premiere case du bateau
                    coorY = (int) ((Math.random() * (14 +1)));
                }while (coorY>=(14-flotte[i].getPV()));
            }

            // trouver les cases suivantes du bateau
            for (int j=0;j<flotte[i].getPV();j++) {
                // si le bateau est à l'horizonale
                if (direction == 1) {
                    tab[j].setCoorX(coorX + j);
                    tab[j].setCoorY(coorY);

                    // a changer avec le numéro du bateau
                    tab[j].setNavire(1);

                    // mettre le tableau dans la classe navire
                }
                // si le bateau est à la verticale
                else {
                    tab[j].setCoorX(coorX);
                    tab[j].setCoorY(coorY + j);

                    // a changer avec le numéro du bateau
                    tab[j].setNavire(i);

                    // mettre le tableau dans la classe navire
                }


            }

            // on met le tableau de cases dans les navires
            for(int k=0;k<flotte[i].getPV();k++)
                flotte[i].setCase(tab[k],k);
            flotte[i].setOrientation(direction);

            Chevauchement =VerifNavire(flotte, i);

            } while (Chevauchement ==true);
        }
    }
}
