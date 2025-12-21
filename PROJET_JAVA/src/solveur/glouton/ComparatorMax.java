package solveur.glouton;

import java.util.Comparator;
import sacados.Objet;

/**
 * Comparateur qui trie les objets en fonction du ratio utilité/coût maximum.
 * Les objets avec le ratio le plus élevé sont considérés comme les plus intéressants.
 */
public class ComparatorMax implements Comparator<Objet> {
    
    private double fmax(Objet o){
        int[] couts = o.getCouts();
        int cmax = couts[0];
        for(int c : couts){
            if(c>cmax)
                cmax=c;
        }
        //on cosidere que l'object est le plus interessant s'il n'a pas de cout
        if(cmax==0)
            return Double.POSITIVE_INFINITY; 
        return (double) o.getUtilite()/cmax;
    }
    
    @Override
    public int compare(Objet o1, Objet o2){
        return Double.compare(fmax(o2), fmax(o1));
    }

}
