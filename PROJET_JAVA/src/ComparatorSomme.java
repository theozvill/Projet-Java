package solveurGlouton;

import java.util.Comparator;
import sacados.Objet;

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
        double f1= fsigma(o1);
        double f2= fsigma(o2);
        if (f1>f2)
            //o1 est plus interessant
            return -1;
        else if (f1==f2)
            return 0;
        else
            return 1;
    }

}
