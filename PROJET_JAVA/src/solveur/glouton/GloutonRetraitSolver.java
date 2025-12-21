package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacados.Objet;
import sacados.SacADos;

/**
 * Implémentation d'un solveur glouton qui retire des objets tant que les contraintes de budget ne sont pas respectées.
 * La sélection des objets à retirer est basée sur un comparateur MV.
 * Une fois que les contraintes sont respectées, le solveur tente de réajouter des objets en utilisant un comparateur fourni.
 */
public class GloutonRetraitSolver implements GloutonSolver{

    private boolean respecteBudgets(int[] coutsCourants, int[] budgets){
        for(int d = 0; d < budgets.length; d++){
            if(coutsCourants[d] > budgets[d]){
                return false;
            }
        }
        return true;
    }

    private boolean peutAjouter(int[] coutsCourants, int[] budgets, int[] coutsObjet){
        for(int d = 0; d < coutsCourants.length; d++){
            if(coutsCourants[d] + coutsObjet[d] > budgets[d]){
                return false;
            }
        }
        return true;
    }

    // calcule L ici car on a besoin pour le comparator mv
    private int[] calculerDimensionsCritiques(List<Objet> S, int[] budgets){
        int dimension = budgets.length;
        int[] sommes = new int[dimension];

        for(Objet o : S){
            int[] couts = o.getCouts();
            for(int d = 0; d < dimension; d++){
                sommes[d] += couts[d];
            }
        }

        int maxDepassement = 0;
        for(int d = 0; d < dimension; d++){
            int depassement = sommes[d] - budgets[d];
            if(depassement > maxDepassement){
                maxDepassement = depassement;
            }
        }

        List<Integer> dims = new ArrayList<>();
        for(int d = 0; d < dimension; d++){
            int depassement = sommes[d] - budgets[d];
            if(depassement == maxDepassement && depassement > 0){
                dims.add(d);
            }
        }

        int[] res = new int[dims.size()];
        for(int i = 0; i < dims.size(); i++){
            res[i] = dims.get(i);
        }
        return res;
    }

    @Override
    public List<Objet> resoudre(SacADos sac, Comparator<Objet> comparatorAjout){
        int dimension = sac.getDimension();
        int[] budgets = sac.getBudgets();

        List<Objet> tous = new ArrayList<>(sac.getObjets());
        List<Objet> S = new ArrayList<>(tous);

        int[] coutsCourants = new int[dimension];
        for(Objet o : S){
            int[] couts = o.getCouts();
            for(int d = 0; d < dimension; d++){
                coutsCourants[d] += couts[d];
            }
        }

        int[] dimsCritiques = calculerDimensionsCritiques(S, budgets);
        Comparator<Objet> comparatorRetrait = new ComparatorMv(dimsCritiques);

        List<Objet> ordreRetrait = new ArrayList<>(S);
        ordreRetrait.sort(comparatorRetrait);
        List<Objet> restants = new ArrayList<>(); // liste des objets hors S

        for(Objet courant : ordreRetrait){
            if(!respecteBudgets(coutsCourants, budgets)){
                if(S.remove(courant)){
                    restants.add(courant);
                    int[] couts = courant.getCouts();
                    for(int d = 0; d < dimension; d++){
                        coutsCourants[d] -= couts[d];
                    }
                }
            }
            else{

                restants.sort(comparatorAjout);

                for(Objet o : restants){
                    int[] couts = o.getCouts();
                    if(peutAjouter(coutsCourants, budgets, couts)){
                        S.add(o);
                        for(int d = 0; d < dimension; d++){
                            coutsCourants[d] += couts[d];
                        }
                    }
                }

                break;
            }
        }

        return S;
    }

}
