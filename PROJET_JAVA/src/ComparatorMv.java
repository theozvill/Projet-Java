package solveurGlouton;

import java.util.Comparator;

import sacados.Objet;

public class ComparatorMv implements Comparator<Objet> {
    
    //tableau car il peut y avoir egalite pour le argmax
    private int[] dimensionsCritiques;

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
        double f1= fmv(o1);
        double f2= fmv(o2);
        if (f1>f2)
            //car on veut du moins interessant au plus interessant
            return 1;
        else if (f1==f2)
            return 0;
        else
            return -1;
    }
}
