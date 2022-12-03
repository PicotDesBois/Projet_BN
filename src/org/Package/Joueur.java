package org.Package;
import java.io.*;
import java.util.Scanner;

public class Joueur {

    private String m_pseudo;
    private Navire[] m_flotte;

    public Joueur() // Constructeur par défaut
    {

        m_flotte=new Navire[10];
        m_flotte[0]=new Cuirasse();
        m_flotte[1]=new Croiseur();
        m_flotte[2]=new Croiseur();
        m_flotte[3]=new Destroyer();
        m_flotte[4]=new Destroyer();
        m_flotte[5]=new Destroyer();
        m_flotte[6]=new SousMarins();
        m_flotte[7]=new SousMarins();
        m_flotte[8]=new SousMarins();
        m_flotte[9]=new SousMarins();
    }
    public Joueur(String fileName)throws IOException // constructeur pour sauvegarde
    {
        // Création d’un fileReader pour lire le fichier
        FileReader fileReader = new FileReader("Sauvegarde/"+fileName+".txt");

        // Création d’un bufferedReader qui utilise le fileReader
        BufferedReader reader = new BufferedReader (fileReader);

        this.m_flotte=new Navire[10];
        try {

            // une fonction à essayer pouvant générer une erreur
            String type;
            int pv,px,py,navire,orientation,puissance;
            boolean touche,fusee;

            m_pseudo= reader.readLine();
            for(int i=0;i<10;i++)
            {
                type=reader.readLine();
                pv=Integer.parseInt(reader.readLine());
                puissance=Integer.parseInt(reader.readLine());
                orientation=Integer.parseInt(reader.readLine());
                fusee=Boolean.parseBoolean(reader.readLine());

                if(type.equals("Croiseur")==true)
                    this.m_flotte[i]=new Croiseur(orientation);
                else if(type.equals("SousMarins")==true)
                    this.m_flotte[i]=new SousMarins(orientation);
                else if(type.equals("Destroyer")==true)
                    this.m_flotte[i]=new Destroyer(orientation,fusee);
                else if(type.equals("Cuirasse")==true)
                    this.m_flotte[i]=new Cuirasse(orientation);
                for(int j=0;j<m_flotte[i].m_pv;j++)
                {
                    px=Integer.parseInt(reader.readLine());
                    py=Integer.parseInt(reader.readLine());
                    navire=Integer.parseInt(reader.readLine());
                    touche=Boolean.parseBoolean(reader.readLine());
                    this.m_flotte[i].getCase()[j]=new Case(navire,px,py,touche);
                }
                m_flotte[i].Afficher();
            }
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
    public int selectionNavire()
    {
        int choix;
        do {
            System.out.println("Saisir le numero du navire : ");
            choix=saisiInt();
        } while (choix<0||choix> m_flotte.length-1);
        return choix;
    }
    public int saisiInt()
    {
        Scanner in=null;
        in = new Scanner(System.in);
        return in.nextInt();
    }
    public void action(int m_choixAction)
    {
        int navireSelectionne;
        if (m_choixAction == 1)
        {
            System.out.println("Vous avez choisi de tirer");
            System.out.println("De quelle bateau voulez vous tirer ?");
            for(int i=0;i< m_flotte.length;i++)
            {
                System.out.println(i+") "+m_flotte[i].m_type);
            }
            navireSelectionne=selectionNavire();
            System.out.println("Vous avez choisi de tirer du bateau "+navireSelectionne);
            m_flotte[navireSelectionne].Tirer();
        }
        else
        {
            System.out.println("Vous avez choisi de vous déplacer");
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

    // permet de sauvegarder un joueur et son jeu
    public void sauvegarde(String fileName) throws IOException {
        String sauvegarde;
        // Création d’un fileWriter pour écrire dans un fichier
        FileWriter fileWriter = new FileWriter("Sauvegarde/"+fileName+".txt", false);

        // Création d’un bufferedWriter qui utilise le fileWriter
        BufferedWriter writer = new BufferedWriter (fileWriter);

        writer.write(m_pseudo+"\n");
        try {
            for(int i=0;i<10;i++)
            {
                sauvegarde=m_flotte[i].m_type+"\n";
                sauvegarde=sauvegarde+m_flotte[i].m_pv+"\n";
                sauvegarde=sauvegarde+m_flotte[i].m_puissance+"\n";
                sauvegarde=sauvegarde+m_flotte[i].m_orientation+"\n";
                sauvegarde=sauvegarde+m_flotte[i].m_fusee+"\n";
                for(int j=0;j<m_flotte[i].m_pv;j++)
                {
                    sauvegarde=sauvegarde+m_flotte[i].getCase()[j].getCoorX()+"\n";
                    sauvegarde=sauvegarde+m_flotte[i].getCase()[j].getCoorY()+"\n";
                    sauvegarde=sauvegarde+m_flotte[i].getCase()[j].getNavire()+"\n";
                    sauvegarde=sauvegarde+m_flotte[i].getCase()[j].getTouche()+"\n";
                }
                writer.write(sauvegarde);
            }
        }
            catch (IOException e) {

                e.printStackTrace();
            }
        writer.close(); // Fermeture du fichier
    }
}
