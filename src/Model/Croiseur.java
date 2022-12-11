package Model;

public class Croiseur extends Navire{

    /**
     * constructeur
     */
    public Croiseur()
    {
        m_pv=5;
        m_type="Croiseur";
        m_orientation=0;
        m_puissance=4;

        m_fusee=false;

        m_cases=new Case[m_pv];
        for(int k=0;k<m_pv;k++)
            m_cases[k]=new Case(0,0,0,false);

        m_coule=false;
    }

    /**
     * consctructeur pour la reprise de partie
     * @param orientation orientation du navire
     * @param coule navire coulé ou non
     */
    public Croiseur(int orientation,boolean coule)
    {
        m_pv=5;
        m_type="Croiseur";
        m_orientation=orientation;
        m_puissance=4;
        m_coule=coule;
        m_fusee=false;

        m_cases=new Case[m_pv];
    }

    /**
     * méthode pour tirer la fusée éclairante : redefinition de la méthode depuis la classe mère
     * @param m_coor coordonnées du tir
     * @param flotte1 flotte de l'adversaire
     * @return un tableau vide car seul un destroyer peut tirer une fusée
     */
    @Override
    public int[][] tirFusee(Case m_coor, Navire[] flotte1){
        return null;}

}
