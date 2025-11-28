package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacados.Objet;
import sacados.SacADos;

/**
 * Implémentation d'un solveur glouton qui ajoute des objets tant que les contraintes de budget sont respectées.
 * La sélection des objets est basée sur un comparateur fourni.
 */
public class GloutonAjoutSolver implements GloutonSolver{

    private boolean peutAjouter(int[] coutsCourants, int[] budgets, int[] coutsObjet){
        for(int d=0; d<coutsCourants.length; d++){
            if(coutsCourants[d] + coutsObjet[d] > budgets[d]){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public List<Objet> resoudre(SacADos sac, Comparator<Objet> comparator){
        int dimension = sac.getDimension();
        int[] budgets = sac.getBudgets();
        List<Objet> interessant = new ArrayList<>(sac.getObjets());
        interessant.sort(comparator);
        int[] coutsCourants = new int[dimension];

        List<Objet> solution = new ArrayList<>();

        for(Objet o : interessant){
            int[] couts = o.getCouts();
            if(peutAjouter(coutsCourants, budgets, couts)){
                solution.add(o);
                for(int d=0; d<dimension; d++)
                    coutsCourants[d] += couts[d];
            }

        }
        return solution;
    }


}
