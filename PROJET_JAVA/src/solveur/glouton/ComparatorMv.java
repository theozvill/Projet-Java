package solveur.glouton;

import java.util.Comparator;

import sacados.Objet;

/**
 * Comparateur qui trie les objets en fonction du ratio utilité/coût maximum sur les dimensions critiques.
 * Les objets avec le ratio le plus élevé sont considérés comme les plus intéressants.
 */
public class ComparatorMv implements Comparator<Objet> {
    
    //tableau car il peut y avoir egalite pour le argmax
    private int[] dimensionsCritiques;

    /**
     * Constructeur de ComparatorMv.
     * 
     * @param dimensionsCritiques Les dimensions critiques à considérer pour le calcul du ratio.
     */
    public ComparatorMv(int[] dimensionsCritiques) {
        this.dimensionsCritiques = dimensionsCritiques;
    }

    
    private double fmv(Objet o) {
        int[] couts = o.getCouts();
        int cmax = 0;

        // max des coûts sur les dimensions critiques
        for (int d : dimensionsCritiques) {
            if (d >= 0 && d < couts.length && couts[d] > cmax) 
                cmax = couts[d];
        }
         //on cosidere que l'object est le plus interessant s'il n'a pas de cout
         if(cmax==0)
            return Double.POSITIVE_INFINITY; 
        return (double) o.getUtilite()/cmax;
    }

    @Override
    public int compare(Objet o1, Objet o2){
        return Double.compare(fmv(o1), fmv(o2));    // ordre croissant
    }
}
