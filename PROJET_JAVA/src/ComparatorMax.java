package solveurGlouton;

import java.util.Comparator;
import sacados.Objet;

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
        double f1= fmax(o1);
        double f2= fmax(o2);
        if (f1>f2)
            //o1 est plus interessant
            return -1;
        else if (f1==f2)
            return 0;
        else
            return 1;
    }

}
