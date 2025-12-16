package solveur.glouton;

import java.util.Comparator;
import java.util.List;
import sacados.*;

/**
 * Interface pour les solveurs Gloutons.
 */
public interface GloutonSolver{
    /**
     * Résout le problème du sac à dos en utilisant une stratégie gloutonne.
     *
     * @param sac Le sac à dos à résoudre.
     * @param comparator Le comparateur utilisé pour trier les objets.
     * @return La liste des objets sélectionnés dans la solution.
     */
    List<Objet> resoudre(SacADos sac, Comparator<Objet> comparator);
}