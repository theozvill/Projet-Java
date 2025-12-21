package tests.utilitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import equipe.Elu;
import equipe.EquipeMunicipale;
import equipe.Evaluateur;
import equipe.Expert;
import equipe.Secteur;
import equipe.TypeCout;
import utilitaires.Sauvegarde;

class SauvegardeTest {

    private static final String num = "1";
    
    @AfterEach
    void nettoyerFichier(){
        File f = new File("data/equipe/sauvegarde_equipe_" + num + ".ser");
        if (f.exists()){
            f.delete();
        }
    }

    @Test
    void testSauvegardeEtChargementEquipe(){
        // Création d'une équipe municipale
        Elu elu = new Elu("Dupont", "Jean", 55);

        EnumMap<TypeCout, Evaluateur> evaluateurs = new EnumMap<>(TypeCout.class);
        for (TypeCout tc : TypeCout.values()){
            evaluateurs.put(tc, new Evaluateur("Eval" + tc, "Test", 40, tc));
        }

        Set<Secteur> secteurs = new HashSet<>();
        secteurs.add(Secteur.SPORT);
        secteurs.add(Secteur.SANTE);

        Expert expert = new Expert("Martin", "Paul", 45, secteurs);

        EquipeMunicipale equipe = new EquipeMunicipale(elu, evaluateurs, List.of(expert));

        // Creation du fichier et sauvegarde
        File fichier = new File("data/equipe/sauvegarde_equipe_" + num + ".ser");
        Sauvegarde.sauvegarderEquipe(equipe, fichier);
        assertTrue(fichier.exists(), "Le fichier de sauvegarde devrait exister");

        // Chargement de la sauvegarde
        EquipeMunicipale equipeChargee = Sauvegarde.chargerEquipe("data/equipe/sauvegarde_equipe_" + num + ".ser");

        assertNotNull(equipeChargee);

        // Vérifications des valeurs
        assertEquals(equipe.getElu().getNom(), equipeChargee.getElu().getNom());
        assertEquals(equipe.getElu().getPrenom(), equipeChargee.getElu().getPrenom());
        assertEquals(equipe.getElu().getAge(), equipeChargee.getElu().getAge());

        assertEquals(equipe.getExperts().size(), equipeChargee.getExperts().size());

        assertEquals(equipe.getEvaluateurs().size(), equipeChargee.getEvaluateurs().size());
    }

    @Test
    void testChargementEquipeInexistante(){
        EquipeMunicipale equipe = Sauvegarde.chargerEquipe("inexistant");
        assertNull(equipe);
    }
}

