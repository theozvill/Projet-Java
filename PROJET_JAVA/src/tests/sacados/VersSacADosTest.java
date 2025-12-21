package tests.sacados;

import sacados.Objet;
import sacados.SacADos;
import sacados.VersSacADos;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import equipe.Projet;
import equipe.Secteur;

import java.nio.file.Path;
import java.util.*;

class VersSacADosTest {
    
    @Test
    void testDepuisProjet3Couts_OK(){
        Projet p1 = new Projet("P1", "Desc", Secteur.SPORT, 100, 10, 20, 30);
        Projet p2 = new Projet("P2", "Desc", Secteur.SANTE, 200, 40, 50, 60);

        List<Projet> projets = List.of(p1, p2);
        int[] budgets = {100, 100, 100};

        SacADos sac = VersSacADos.depuisProjet3couts(projets, budgets);

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertEquals(2, sac.getObjets().size());

        Objet o1 = sac.getObjets().get(0);
        Objet o2 = sac.getObjets().get(1);

        // Vérification des utilités
        assertEquals(p1.getBenefice(), o1.getUtilite());
        assertEquals(p2.getBenefice(), o2.getUtilite());

        // Vérification des coûts (ordre : éco, social, environnemental)
        assertArrayEquals(new int[]{p1.getCoutEconomique(), p1.getCoutSocial(), p1.getCoutEnvironnemental()}, o1.getCouts());
        assertArrayEquals(new int[]{p2.getCoutEconomique(), p2.getCoutSocial(), p2.getCoutEnvironnemental()}, o2.getCouts());
    }
    

    @Test
    void testDepuisProjetParSecteurs_OK() {
        List<Projet> projets = List.of(
                new Projet("P1", "Desc", Secteur.SPORT, 100, 50, 0, 0),
                new Projet("P2", "Desc", Secteur.CULTURE, 200, 70, 0, 0));

        Map<Secteur, Integer> budgets = new EnumMap<>(Secteur.class);
        for (Secteur s : Secteur.values()) {
            budgets.put(s, 500);
        }

        SacADos sac = VersSacADos.depuisProjetParSecteurs(projets, budgets);

        // On verifie qu'il y a une dimension égal au nombre de secteurs
        assertEquals(Secteur.values().length, sac.getDimension());
        
        assertEquals(2, sac.getObjets().size());
    }

    @Test
    void testDepuisFichier_OK(@TempDir Path tempDir) throws Exception {

        SacADos sac = VersSacADos.depuisFichier("src/tests/sacados/SacTest.txt");

        assertNotNull(sac);
        assertEquals(3, sac.getDimension());
        assertEquals(2, sac.getObjets().size());
        assertArrayEquals(new int[]{10, 10, 10}, sac.getBudgets());

        Objet o1 = sac.getObjets().get(0);
        Objet o2 = sac.getObjets().get(1);

        // Vérification des utilités
        assertEquals(10, o1.getUtilite());
        assertEquals(20, o2.getUtilite());

        // Vérification des coûts (ordre : éco, social, environnemental)
        assertArrayEquals(new int[]{1,3,5}, o1.getCouts());
        assertArrayEquals(new int[]{2,4,6}, o2.getCouts());

        assertFalse(sac.respecteContraintes());
    }
}
