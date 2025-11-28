import equipe.*;
import sacados.*;
import solveur.glouton.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Test équipe municipale ===");

        // 1. Création de l'élu
        Elu elu = new Elu("Maude", "ZARELLA", 45);

        // 2. Création des évaluateurs
        Map<TypeCout, Evaluateur> evaluateurs = new EnumMap<>(TypeCout.class);
        evaluateurs.put(TypeCout.ECONOMIQUE, new Evaluateur("Jean", "NAIMAR", 25, TypeCout.ECONOMIQUE));
        evaluateurs.put(TypeCout.SOCIAL, new Evaluateur("Jean", "BOMBEUR", 25, TypeCout.SOCIAL));
        evaluateurs.put(TypeCout.ENVIRONNEMENTAL, new Evaluateur("Jean", "POCHETOUT", 25, TypeCout.ENVIRONNEMENTAL));

        // 3. Création des experts
        List<Expert> experts = List.of(
                new Expert("Theo", "RAIME", 25, Set.of(Secteur.SPORT, Secteur.EDUCATION)),
                new Expert("Theo", "LOGIE", 25, Set.of(Secteur.SANTE, Secteur.CULTURE)),
                new Expert("Theo", "RIBLE", 25, Set.of(Secteur.ATTRACTIVITE_ECONOMIQUE, Secteur.SPORT)),
                new Expert("Theo", "LAIT", 25, Set.of(Secteur.ATTRACTIVITE_ECONOMIQUE, Secteur.EDUCATION)),
                new Expert("Theo", "XIDENTAL", 25, Set.of(Secteur.SANTE, Secteur.CULTURE))
        );

        // 4. Création de l'équipe
        EquipeMunicipale equipe = new EquipeMunicipale(elu, evaluateurs, experts);

        System.out.println(equipe);

        // 5. Simulation d'un cycle
        List<Projet> projets = equipe.simulerCycle();

        System.out.println("Nombre de projets générés : " + projets.size());
        for (Projet p : projets) {
            System.out.println(" → " + p);
        }


        /* ----------------------------------- Sac à dos --------------------------------- */

        System.out.println("\n=== Test sac à dos multidimensionnel ===");

        // Exemple simple : convertir les projets complets en objets
        // Ici on suppose 3 coûts (éco, social, environnemental)
        int dimension = 3;
        int[] budgets = {300000, 150, 150};  // budgets fictifs

        SacADos sac = new SacADos(dimension, budgets);

        for (Projet p : projets) {
            int[] couts = {
                    p.getCoutEconomique(),
                    p.getCoutSocial(),
                    p.getCoutEnvironnemental()
            };
            Objet obj = new Objet(p.getBenefice(), couts);
            sac.addObjet(obj);
        }

        System.out.println(sac);
        System.out.println("Contrainte respectée ? " + sac.respecteContraintes());

        /* ----------------------------------- Glouton Ajout --------------------------------- */

        System.out.println("\n=== Test Glouton : Méthode par Ajout ===");

        GloutonAjoutSolver gloutonAjout = new GloutonAjoutSolver();
        Comparator<Objet> comparatorSomme = new ComparatorSomme();

        List<Objet> solutionAjout = gloutonAjout.resoudre(sac, comparatorSomme);

        SacADos sacTestAjout = new SacADos(dimension, budgets, solutionAjout);
        System.out.println(sacTestAjout);

        System.out.println("Objets choisis (Ajout) :");
        for(Objet o : solutionAjout){
            System.out.println(o);
        }
        System.out.println("Contrainte respectée ? " + sacTestAjout.respecteContraintes());



        /* ----------------------------------- Glouton Retrait --------------------------------- */

        System.out.println("\n=== Test Glouton : Méthode par Retrait ===");

        GloutonRetraitSolver gloutonRetrait = new GloutonRetraitSolver();

        List<Objet> solutionRetrait = gloutonRetrait.resoudre(sac, comparatorSomme);

        SacADos sacTestRetrait = new SacADos(dimension, budgets, solutionRetrait);
        System.out.println(sacTestRetrait);

        System.out.println("Objets choisis (Retrait) :");
        for(Objet o : solutionRetrait){
            System.out.println(o);
        }
        System.out.println("Contrainte respectée ? " + sacTestRetrait.respecteContraintes());


        /* ---------------------------------------------------------------------- */

        System.out.println("\n=== Fin des tests ===");
    }
}