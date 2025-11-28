package tests;

import equipe.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class EquipeMunicipaleTest {

	private EquipeMunicipale equipe;

    @BeforeEach
    public void setUp(){

        Elu elu = new Elu("Sandro", "UNSWORTH", 45);

        Map<TypeCout, Evaluateur> evaluateurs = new HashMap<>();
        evaluateurs.put(TypeCout.ECONOMIQUE, new Evaluateur("Theo", "EL", 25, TypeCout.ECONOMIQUE));
        evaluateurs.put(TypeCout.SOCIAL, new Evaluateur("Theo", "ZOGHBI", 25, TypeCout.SOCIAL));
        evaluateurs.put(TypeCout.ENVIRONNEMENTAL, new Evaluateur("Theo", "VILLETTE", 25, TypeCout.ENVIRONNEMENTAL));

        List<Expert> experts = new ArrayList<>();
        experts.add(new Expert("Iliam", "SOUAMI", 25, Set.of(Secteur.SPORT, Secteur.EDUCATION)));
        experts.add(new Expert("Iliam", "BILLARANT", 25, Set.of(Secteur.SANTE, Secteur.CULTURE)));

        equipe = new EquipeMunicipale(elu, evaluateurs, experts);

    }

    @Test
    public void simulerCycleTest_equipe_ProjetsValides(){
        List<Projet> projets = equipe.simulerCycle();

        assertEquals(2, projets.size(), "Le nombre de projet doit être 2 (2 experts)");

        for(Projet p : projets){
            assertNotNull(p, "Le projet ne doit pas être null");

             // Tous les coûts doivent être présents
            assertNotNull(p.getCoutEconomique(), "Coût économique manquant");
            assertNotNull(p.getCoutSocial(), "Coût social manquant");
            assertNotNull(p.getCoutEnvironnemental(), "Coût environnemental manquant");

            // Le bénéfice doit être renseigné
            assertNotNull(p.getBenefice(), "Bénéfice manquant");

            // Vérification sur les ranges aléatoires
            assertTrue(p.getBenefice() >= 5000 && p.getBenefice() <= 20000, "Bénéfice hors des bornes");
        }
    }


}
