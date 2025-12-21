package solveur.hillclimbing;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import sacados.*;

/**
 * Implémentation d'un solveur utilisant l'algorithme de Hill Climbing pour optimiser la sélection des objets dans un sac à dos.
 * Le solveur génère des voisins en retirant un objet et en en ajoutant un autre, et choisit le voisin avec la meilleure utilité.
 * La génération des voisins peut être complète ou aléatoire selon le paramètre nbRandom.
 */
public class HillClimbingSolver{
    private int nbRandom;   // Nombre de voisins aléatoires à générer. Si <=0, génération complète.

    /**
     * Constructeur de base. Permet de faire une résolution complète
     */
    public HillClimbingSolver(){
        this(0);
    }

    /**
     * Constructeur permettant de faire la génération du nombre de voisins passé en argument.
     * Une génération complète sera effectuée si la valeur passée en argument est inférieur à 0.
     * 
     * @param nb le nombre de voisins à générer
     */
    public HillClimbingSolver(int nb){
        this.nbRandom = nb;
    }

    private List<SacADos> genererVoisin(SacADos sac, List<Objet> objetsTot){
        List<SacADos> voisins = new ArrayList<>();

        List<Objet> dedans = sac.getObjets();
        List<Objet> dehors = new ArrayList<>(objetsTot);
        if(dedans == null || dehors == null || dehors.size() == 0 || dedans.size() == 0)    // On retourne une liste vide si le sac est vide ou possede tous les objets
            return voisins;
        dehors.removeAll(dedans);

        int dim = sac.getDimension();
        int[] budgets = sac.getBudgets();

        Random random = new Random();

        if(this.nbRandom <= 0)  //Générations complete des voisins
            for(Objet oRetrait : dedans){
                for(Objet oAjout : dehors){
                    List<Objet> newDedans = new ArrayList<>(dedans);
                    newDedans.remove(oRetrait);
                    newDedans.add(oAjout);
                    SacADos voisin = new SacADos(dim, budgets, newDedans);

                    if(voisin.respecteContraintes())
                        voisins.add(voisin);
                }
            }
        else
            for(int k = 0; k<this.nbRandom; k++){   // Génération aléatoire d'au plus nbRandom voisins
                Objet aRetirer = dedans.get(random.nextInt(dedans.size()));
                Objet aAjouter = dehors.get(random.nextInt(dehors.size()));

                List<Objet> newDedans = new ArrayList<>(dedans);
                newDedans.remove(aRetirer);
                newDedans.add(aAjouter);

                SacADos voisin = new SacADos(dim, budgets, newDedans);

                if(voisin.respecteContraintes())
                    voisins.add(voisin);
            }

        return voisins;
    }

    
    /**
     * Résout le problème du sac à dos en utilisant l'algorithme de Hill Climbing.
     * La selection des voisins se fait par génération complète ou aléatoire selon la valeur de nbRandom.
     * 
     * @param sac Le sac à dos satisfaisant les contraintes.
     * @param sacOrigine Le sac à dos d'origine contenant tous les objets possibles.
     * @return Le sac à dos optimisé après l'application de l'algorithme de Hill Climbing.
     */
    public SacADos resoudre(SacADos sac, SacADos sacOrigine){
        
        SacADos courant = sac;
        int utiliteCourant = courant.utiliteTotale();
        int utiliteVoisin;

        boolean changement = true;
        while(changement){
            changement = false;
            List<SacADos> voisins = genererVoisin(courant, sacOrigine.getObjets());

            for(SacADos s : voisins){
                utiliteVoisin = s.utiliteTotale();

                if(utiliteCourant <= utiliteVoisin){
                    courant = s;
                    utiliteCourant = utiliteVoisin;
                    changement = true;
                    break;
                }
            }
        }
        return courant;
    }
}