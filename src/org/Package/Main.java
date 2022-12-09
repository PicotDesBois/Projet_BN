package org.Package;
import java.io.IOException;
import java.util.Scanner;
public class Main {



    public static void main(String[] args) {

        Partie party = new Partie();
        party.Jouer();
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
