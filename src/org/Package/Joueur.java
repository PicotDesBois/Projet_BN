package org.Package;
import java.io.*;
import java.util.Scanner;

public class Joueur {

    private String m_pseudo;
    private Navire[] m_flotte;

    public Joueur()
    {
        System.out.println("Saisir le pseudo du joueur : ");
        m_pseudo=Saisi();

        m_flotte=new Navire[10];
        m_flotte[0]=new Cuirasse(0,0,0);
        m_flotte[1]=new Croiseur(0,0,0);
        m_flotte[2]=new Croiseur(0,0,0);
        m_flotte[3]=new Destroyer(0,0,0);
        m_flotte[4]=new Destroyer(0,0,0);
        m_flotte[5]=new Destroyer(0,0,0);
        m_flotte[6]=new SousMarins(0,0,0);
        m_flotte[7]=new SousMarins(0,0,0);
        m_flotte[8]=new SousMarins(0,0,0);
        m_flotte[9]=new SousMarins(0,0,0);
    }
    public Joueur(String fileName)throws IOException
    {
        // Création d’un fileReader pour lire le fichier
        FileReader fileReader = new FileReader("Sauvegarde/"+fileName+".txt");

        // Création d’un bufferedReader qui utilise le fileReader
        BufferedReader reader = new BufferedReader (fileReader);


        try {

            // une fonction à essayer pouvant générer une erreur
            String line;
            int test;

            do{
                // lecture de la prochaine ligne
                line = reader.readLine();
                //test=reader.read();
                // affichage de la ligne
                System.out.println(line);
            }while(line!=null);
        }
        catch (IOException e) {
                e.printStackTrace();
            }
        reader.close();
    }

    public String  Saisi()
    {
        Scanner in=null;
        String temp;
        in = new Scanner(System.in);
        temp = in.nextLine();
        return temp;
    }

    public void Afficher()
    {
        System.out.println("Pseudo : "+m_pseudo);
        System.out.println("Flotte : ");
        for (int i=0;i<10;i++)
        {
            m_flotte[i].Afficher();
        }
    }

    public String getPseudo()
    {
        return m_pseudo;
    }
    public void setPseudo(String temp)
    {
        m_pseudo=temp;
    }

    public Navire[] getFlotte1() { return m_flotte; }

    public Navire getFlotte2(int i) { return m_flotte[i]; }
    public void setFlotte(Navire temp, int i)
    {
        m_flotte[i]=temp;
    }

    public void sauvegarde() throws IOException {

        // Création d’un fileWriter pour écrire dans un fichier
        FileWriter fileWriter = new FileWriter("Sauvegarde/Sauvegarde2.txt", false);

        // Création d’un bufferedWriter qui utilise le fileWriter
        BufferedWriter writer = new BufferedWriter (fileWriter);


        try {
            writer.write(m_pseudo);
            writer.newLine();
            for(int i=0;i<9;i++)
            {
                writer.write(m_flotte[i].m_type);
                writer.newLine();
                writer.write("PV : "+m_flotte[i].m_pv);
                writer.newLine();
                writer.write(m_flotte[i].m_orientation);
                writer.newLine();
                writer.write(m_flotte[i].m_px);
                writer.newLine();
                writer.write(m_flotte[i].m_py);
                writer.newLine();
            }
        }
            catch (IOException e) {

                e.printStackTrace();
            }
        writer.close(); // Fermeture du fichier
    }
}
