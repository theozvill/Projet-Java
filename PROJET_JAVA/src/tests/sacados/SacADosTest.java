package tests.sacados;

import sacados.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class SacADosTest {

    private Objet obj1;
    private Objet obj2;
    private SacADos sac;

    @BeforeEach
    public void setUp(){

        // Objets valides (dim 3)
        obj1 = new Objet(10, new int[]{1, 2, 3});
        obj2 = new Objet(5, new int[]{2, 1, 1});

        // Budgets justes pour obj1 seulement
        sac = new SacADos(3, new int[]{2, 3, 3});
    }

    @Test
    public void testContraintesDeBase(){

        // Sac vide
        assertTrue(sac.respecteContraintes());

        // +1 objet
        sac.addObjet(obj1);
        assertTrue(sac.respecteContraintes());

        // +2eme objet
        sac.addObjet(obj2);
        assertFalse(sac.respecteContraintes());
    }

    @Test
    public void testSetters_Et_AddObjetAvecContraintes(){

        // setObjets
        sac.setObjets(List.of(obj1));
        assertTrue(sac.respecteContraintes());

        // addObjet
        sac.addObjet(obj2);
        assertFalse(sac.respecteContraintes());

        // setBudgets
        sac.setBudgets(new int[]{5, 5, 5});
        assertTrue(sac.respecteContraintes());

        // setDimension
        sac.setDimension(3);
        sac.setBudgets(new int[]{5, 5, 5});
        sac.setObjets(List.of(obj1, obj2));

        assertTrue(sac.respecteContraintes());
    }

    @Test
    public void testCoutsTotaux_Et_UtiliteTotale(){

        sac.addObjet(obj1);
        sac.addObjet(obj2);

        int[] coutsAttendus = new int[]{3, 3, 4}; // Doit etre changé avec les variables dans le setup
        assertArrayEquals(coutsAttendus, sac.coutsTotaux());

        assertEquals(15, sac.utiliteTotale()); // Doit etre changé avec les variables dans le setup
    }
}
