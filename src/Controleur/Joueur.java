package Controleur;
import Model.*;
import java.io.*;

public class Joueur {

    /**
     * Pseudo du joueur
     * flotte des navires du joueur
     */
    private String m_pseudo;
    private Navire[] m_flotte;

    /**
     * Constructeur
     */
    public Joueur()
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

    /**
     * methode pour charger la classe joueur a partir d'un fichier texte en paramètre
     * @param fileName nom du fichier
     * @throws IOException exception
     */
    public Joueur(String fileName)throws IOException
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
            boolean touche,fusee,coule;

            m_pseudo= reader.readLine();
            for(int i=0;i<10;i++)
            {
                type=reader.readLine();
                pv=Integer.parseInt(reader.readLine());
                puissance=Integer.parseInt(reader.readLine());
                orientation=Integer.parseInt(reader.readLine());
                fusee=Boolean.parseBoolean(reader.readLine());
                coule=Boolean.parseBoolean(reader.readLine());

                switch (type) {
                    case "Croiseur" -> this.m_flotte[i] = new Croiseur(orientation, coule);
                    case "SousMarins" -> this.m_flotte[i] = new SousMarins(orientation, coule);
                    case "Destroyer" -> this.m_flotte[i] = new Destroyer(orientation, fusee, coule);
                    case "Cuirasse" -> this.m_flotte[i] = new Cuirasse(orientation, coule);
                }
                for(int j=0;j<m_flotte[i].getPV();j++)
                {
                    px=Integer.parseInt(reader.readLine());
                    py=Integer.parseInt(reader.readLine());
                    navire=Integer.parseInt(reader.readLine());
                    touche=Boolean.parseBoolean(reader.readLine());
                    this.m_flotte[i].getCase()[j]=new Case(navire,px,py,touche);
                }
            }
        }
        catch (IOException e) {
                e.printStackTrace();
            }
        reader.close();
    }

    /**
     * methode pour sauvegarder la classe joueur a partir d'un fichier texte en paramètre
     * @param fileName nom du fichier
     * @throws IOException exception
     */
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
                sauvegarde=m_flotte[i].geType()+"\n";
                sauvegarde=sauvegarde+m_flotte[i].getPV()+"\n";
                sauvegarde=sauvegarde+m_flotte[i].getPuissance()+"\n";
                sauvegarde=sauvegarde+m_flotte[i].getOrientation()+"\n";
                sauvegarde=sauvegarde+m_flotte[i].getFusee()+"\n";
                sauvegarde=sauvegarde+m_flotte[i].getCoule()+"\n";
                for(int j=0;j<m_flotte[i].getPV();j++)
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


    /**
     * accesseurs des attributs
     */
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


}
