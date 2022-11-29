package org.Package;
import java.io.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        SousMarins s1 = new SousMarins(0,0,0);
        Destroyer d1 = new Destroyer(0,0,0);
        Croiseur cr1 = new Croiseur(0,0,0);
        Cuirasse cu1 = new Cuirasse(0,0,0);

        s1.Afficher();
        d1.Afficher();
        cr1.Afficher();
        cu1.Afficher();

        SousMarins s2;
        String fileName="Sauvegarde";
        try {
            s1.save("Sauvegarde1");
            System.out.println("s1");
            System.out.println(s1);

            /*s2 = null;
            s2 = SousMarins.load(fileName);
            System.out.println("Customer B");
            System.out.println(s2);*/
            }
            catch (IOException exc)
            {
                System.out.println("exc");
            }
            /*catch (ClassNotFoundException exc)
            {
                System.out.println("exc");
            }*/

        /*Initialisation init = new Initialisation();



        Joueur j1 = new Joueur();

        j1.ChoixPseudo();
        init.PositionAleaNavire(j1.getFlotte1());

        j1.Afficher();*/
    }
}