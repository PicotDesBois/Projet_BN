package org.Package;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SousMarins s1 = new SousMarins(0,0,0);
        Destroyer d1 = new Destroyer(0,0,0);
        Croiseur cr1 = new Croiseur(0,0,0);
        Cuirasse cu1 = new Cuirasse(0,0,0);

        Initialisation init = new Initialisation();
        /*Joueur j1 = new Joueur();


        try {
            j1.sauvegarde();
        }
        catch (IOException exc)
        {
            System.out.println("exc");
        }*/

        String fileName= Saisi();
        try
        {
            Joueur j2=new Joueur(fileName);
        }
        catch (IOException exc)
        {

        }
    }
    public static String  Saisi()
    {
        Scanner in=null;
        String temp;
        in = new Scanner(System.in);
        temp = in.nextLine();
        return temp;
    }
}
