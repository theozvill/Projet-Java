package tests.solveur;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import sacados.Objet;
import sacados.SacADos;
import solveur.glouton.ComparatorMax;
import solveur.glouton.GloutonRetraitSolver;

class GloutonRetraitSolverTest {

    private int[] calculerCoutsTotaux(List<Objet> objets, int dimension) {
        int[] couts = new int[dimension];
        for (Objet o : objets) {
            int[] c = o.getCouts();
            for (int i = 0; i < dimension; i++) {
                couts[i] += c[i];
            }
        }
        return couts;
    }

    private void verifierRespectBudgets(List<Objet> solution, int[] budgets) {
        int[] couts = calculerCoutsTotaux(solution, budgets.length);
        for (int i = 0; i < budgets.length; i++) {
            assertTrue(couts[i] <= budgets[i],
                    "Le budget de la dimension " + i + " n'est pas respecté.");
        }
    }

    @Test
    public void testCycleGloutonRetrait() {
        // Initialisation (même logique que simulerCycle)
        int dimension = 3;
        int[] budgets = { 10, 10, 10 };

        Objet projet1 = new Objet(10, new int[] { 8, 8, 8 });
        Objet projet2 = new Objet(9,  new int[] { 5, 0, 0 });
        Objet projet3 = new Objet(8,  new int[] { 0, 5, 0 });
        Objet projet4 = new Objet(7,  new int[] { 0, 0, 5 });

        List<Objet> projets = new ArrayList<>();
        projets.add(projet1);
        projets.add(projet2);
        projets.add(projet3);
        projets.add(projet4);

        SacADos sac = new SacADos(dimension, budgets, projets);

        GloutonRetraitSolver solver = new GloutonRetraitSolver();
        Comparator<Objet> critereAjout = new ComparatorMax();

        // Exécution du cycle de résolution
        List<Objet> solution = solver.resoudre(sac, critereAjout);

        // Vérifications
        assertNotNull(solution);
        assertFalse(solution.isEmpty());

        verifierRespectBudgets(solution, budgets);

        // Le projet trop coûteux doit avoir été retiré
        assertFalse(solution.contains(projet1));
        assertTrue(solution.contains(projet2));
        assertTrue(solution.contains(projet3));
        assertTrue(solution.contains(projet4));

        // Le sac initial ne doit pas être modifié
        assertEquals(4, sac.getObjets().size());
    }
}