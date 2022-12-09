package Vue;

import org.Package.Case;
import org.Package.Joueur;
import org.Package.Navire;

public class Affichage {

    public Affichage()
    {

    }

    public void Afficher(Navire nav)
    {
        System.out.println(nav.geType());
        System.out.print("Puissance "+nav.getPuissance()+" PV restant "+nav.getPV());

        if (nav.getFusee())
            System.out.println(" Le navire peut tirer une fusee eclairante");
        else
            System.out.println(" Le navire ne peut pas tirer de fusee eclairante");
        if (!nav.getCoule())
            System.out.println("Le navire est a flot");
        else
            System.out.println("Le navire a été envoyé par le fond");

        if (nav.getOrientation()==1)
        {
            System.out.println("le navire est horizontal "+nav.getOrientation());
        }
        else
        {
            System.out.println("le navire est vertical "+nav.getOrientation());
        }

        for (int i=0;i<nav.getPV();i++)
            System.out.println("Case : ( "+nav.getCase()[i].getCoorX()+" ; "+nav.getCase()[i].getCoorY()+" )"+nav.getCase()[i].getTouche());
    }

    public void Afficher(Joueur player)
    {
        System.out.println("Pseudo : "+player.getPseudo());
        System.out.println("Flotte : ");
        for (int i=0;i<10;i++)
        {
            Afficher(player.getFlotte1()[i]);
        }
    }

    public void Afficher(Case cas)
    {
        System.out.println("Case : ( "+cas.getCoorX()+" ; "+cas.getCoorY()+" )");
        System.out.println("Touche : "+cas.getTouche());
        System.out.println("Navire : "+cas.getNavire());
    }

    public void AfficherSaisir(String text)
    {
        System.out.println("Saisir "+text);
    }

    public void AfficherTexte(String text)
    {
        System.out.println(text);
    }

}
