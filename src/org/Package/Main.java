package org.Package;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SousMarins s1 = new SousMarins(0,0,0);
        Destroyer d1 = new Destroyer(0,0,0);
        Croiseur cr1 = new Croiseur(0,0,0);
        Cuirasse cu1 = new Cuirasse(0,0,0);

        s1.Afficher();
        d1.Afficher();
        cr1.Afficher();
        cu1.Afficher();

        Initialisation init = new Initialisation();

        init.PositionAleaNavire();
    }
}