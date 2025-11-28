package solveur.glouton;

import java.util.Comparator;
import sacados.Objet;

/**
 * Comparateur qui trie les objets en fonction du ratio utilité/somme des coûts.
 * Les objets avec le ratio le plus élevé sont considérés comme les plus intéressants.
 */
public class ComparatorSomme implements Comparator<Objet> {

    private double fsigma(Objet o){
        int somme = 0;
        for(int c : o.getCouts()){
            somme+=c;
        }
        //on cosidere que l'object est le plus interessant s'il n'a pas de cout
        if(somme==0)
            return Double.POSITIVE_INFINITY; 
        return (double) o.getUtilite()/somme;
    }
    
    @Override
    public int compare(Objet o1, Objet o2){
        return Double.compare(fsigma(o2), fsigma(o1));
    }

}
