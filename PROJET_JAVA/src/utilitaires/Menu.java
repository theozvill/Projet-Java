package utilitaires;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import equipe.EquipeMunicipale;
import equipe.Evaluateur;
import equipe.Expert;
import equipe.Projet;
import equipe.Secteur;
import equipe.TypeCout;
import main.Main;
import sacados.Objet;
import sacados.SacADos;
import sacados.VersSacADos;
import solveur.glouton.ComparatorMax;
import solveur.glouton.ComparatorSomme;
import solveur.glouton.GloutonAjoutSolver;
import solveur.glouton.GloutonRetraitSolver;
import solveur.glouton.GloutonSolver;
import solveur.hillclimbing.HillClimbingSolver;

/**
 * Classe utilitaire responsable de la gestion des menus interactifs.
 * Cette classe centralise l'affichage des menus dans la console ainsi que la gestion des interactions utilisateur (choix, navigation, validations).
 * Cette classe ne contient que des méthodes statiques et n'a pas vocation à être instanciée.
 */
public class Menu {

    private Menu(){} // Class non instanciable
    
    /**
     * Menu principal permettant d'ouvrir les autres menus.
     * 
     * @param scanner le Scanner à utiliser pour la lecture
     * @return 1 pour réouvrir ce menu dans le main et 0 pour arreter le programme
     */
    public static int menu(Scanner scanner){
        System.out.println(".---------------------------------------.");
        System.out.println("|                  Menu                 |");
        System.out.println("|---------------------------------------|");
        System.out.println("| 1. Gestion de l'équipe municipale     |");
        System.out.println("|---------------------------------------|");
        System.out.println("| 2. Gestion des projets                |");
        System.out.println("|---------------------------------------|");
        System.out.println("| 3. Sac à dos                          |");
        System.out.println("|---------------------------------------|");
        System.out.println("| 4. Solveurs                           |");
        System.out.println("|---------------------------------------|");
        System.out.println("| 0. Quitter                            |");
        System.out.println("|_______________________________________|");

        int choix;
        do{
            System.out.println("Entrez 1, 2, 3, 4 ou 0 !");
            try{
                choix = Integer.parseInt(scanner.nextLine());   // Utilisation de nextLine pour éviter les problèmes de saut de ligne
            } catch(Exception e){
                choix = -1;
            }
        } while(choix < 0 || choix > 4);
        System.out.print("\033[0;0H");
        System.out.print("\033[2J");

        switch(choix){
            case 1:
                menuEquipe(scanner);
                return 1;
            case 2:
                if(Main.getEquipe() == null)
                    System.out.println("Veuillez instancier une équipe pour accéder au menu des projets");
                else
                    menuProjet(scanner);
                return 1;
            case 3:
                menuSac(scanner);
                return 1;
            case 4:
                if(Main.getSac() == null)
                    System.out.println("Veuillez instancier un sac pour accéder au menu des solveurs");
                else
                    menuSolveur(scanner);
                return 1;
            case 0:
                System.out.println("Au revoir !");
                return 0;
            default:
                return 1;

        }
    }

    /**
     * Menu de gestion d'équipe permettant d'intéragir avec l'équipe municipale.
     * 
     * @param scanner le Scanner à utiliser pour la lecture
     */
    public static void menuEquipe(Scanner scanner){
        int choix;
        EquipeMunicipale equipe;
        while(true){
            System.out.println(".-------------------------------.");
            System.out.println("|        Menu Équipe            |");
            System.out.println("|-------------------------------|");
            System.out.println("| 1. Créer une équipe           |");
            System.out.println("|-------------------------------|");
            System.out.println("| 2. Charger une équipe         |");
            System.out.println("|-------------------------------|");
            System.out.println("| 3. Sauvegarder l'équipe       |");
            System.out.println("|-------------------------------|");
            System.out.println("| 4. Afficher l'équipe          |");
            System.out.println("|-------------------------------|");
            System.out.println("| 5. Afficher les personnes     |");
            System.out.println("|-------------------------------|");
            System.out.println("| 0. Retour au menu principal   |");
            System.out.println("|_______________________________|");
            System.out.println("Entrez 1, 2, 3, 4, 5 ou 0 !");
            choix = Common.lireEntierEntre(0, 5, scanner);
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");

            switch(choix){
                case 1:
                    equipe = EquipeMunicipale.creerEquipe(scanner);
                    Main.setEquipe(equipe);
                    System.out.println("Équipe créée avec succès !");
                    break;
                case 2:
                    String chemin = Sauvegarde.choixSauvegarde("data/equipe", scanner);
                    if(chemin == null)
                        break;
                    equipe = Sauvegarde.chargerEquipe(chemin);
                    if(equipe != null){
                        System.out.println("Equipe chargée !");
                        Main.setEquipe(equipe);
                    }
                    break;
                case 3:
                    equipe = Main.getEquipe();
                    if(equipe == null){
                        System.out.println("Aucune équipe municipale chargée.");
                    } else {
                        File f = Sauvegarde.fichierEnregistrementSauvegarde("data/equipe", scanner, "ser");
                        Sauvegarde.sauvegarderEquipe(Main.getEquipe(), f);
                    }
                    break;
                case 4:
                    equipe = Main.getEquipe();
                    if(equipe == null){
                        System.out.println("Aucune équipe municipale chargée.");
                    } else {
                        System.out.println(equipe);
                    }
                    break;
                case 5:
                    equipe = Main.getEquipe();
                    if(equipe == null){
                        System.out.println("Aucune équipe municipale chargée.");
                    } else {
                        System.out.println(equipe.getElu());
                        for(Evaluateur eva : equipe.getEvaluateurs().values())
                            System.out.println(eva);
                        for(Expert exp : equipe.getExperts())
                            System.out.println(exp);
                    }
                    break;
                case 0:
                    return;
                default:
                    break;

            }
        }
    }

    /**
     * Menu de gestion des projets permettant d'intéragir avec les projets de la municipalité.
     * 
     * @param scanner le Scanner à utiliser pour la lecture
     */
    public static void menuProjet(Scanner scanner){
        int choix;
        List<Projet> projets;
        while(true){
            System.out.println(".---------------------------------.");
            System.out.println("|          Menu Projet            |");
            System.out.println("|---------------------------------|");
            System.out.println("| 1. Simuler un cycle             |");
            System.out.println("|---------------------------------|");
            System.out.println("| 2. Afficher les projets         |");
            System.out.println("|---------------------------------|");
            System.out.println("| 0. Retour au menu principal     |");
            System.out.println("|_________________________________|");
            System.out.println("Entrez 1, 2 ou 0 !");
            choix = Common.lireEntierEntre(0, 2, scanner);
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");

            switch (choix) {
                case 1:
                    projets = Main.getEquipe().simulerCycle();
                    System.out.println("Nombre de projets générés : " + projets.size());
                    for (Projet p : projets) {
                        System.out.println(" → " + p);
                    }
                    break;
                case 2:
                    projets = Main.getEquipe().getProjetsMunicipaux();
                    System.out.println("Nombre de projets au sein de l'équipe : " + projets.size());
                    for(Projet p : projets){
                        System.out.println(" → " + p);
                    }
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }


    /**
     * Menu de gestion du sac à dos.
     * 
     * @param scanner le Scanner à utiliser pour la lecture
     */
    public static void menuSac(Scanner scanner){
        int choix;
        int[] budget;
        int dim = 0;
        List<Projet> projets;
        SacADos sac;
        while(true){
            System.out.println(".-------------------------------------.");
            System.out.println("|          Menu Sac à dos             |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 1. Sac à dos (3 coûts)              |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 2. Sac à dos (par secteurs)         |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 3. Charger une instance MKP         |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 4. Enregistrer une instance MKP     |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 5. Afficher le sac à dos            |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 6. Afficher les Objets              |");
            System.out.println("|-------------------------------------|");
            System.out.println("| 0. Retour au menu principal         |");
            System.out.println("|_____________________________________|");

            System.out.println("Entrez 1, 2, 3, 4, 5, 6 ou 0 !");
            choix = Common.lireEntierEntre(0, 6, scanner);
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");

            switch(choix){
                case 1:
                    if(Main.getEquipe() == null){
                        System.out.println("Aucune équipe de chargée");
                        break;
                    }
                    projets = new ArrayList<>(Main.getEquipe().getProjetsMunicipaux());
                    if(projets.isEmpty())
                        System.out.println("Impossible de créer un sac à partir d'aucun projet dans la municipalité");
                    else{
                        dim = TypeCout.values().length;
                        budget = new int[dim];
                        for(int i = 0; i < dim; i++){
                            System.out.println("Entrez le budget pour " + TypeCout.values()[i]);
                            budget[i] = Common.lireEntierPositif(scanner);
                        }
                        Main.setSac(VersSacADos.depuisProjet3couts(projets, budget));
                        System.out.println("Sac à dos crée avec succes !");
                    }
                    break;
                case 2:
                    if(Main.getEquipe() == null){
                        System.out.println("Aucune équipe de chargée");
                        break;
                    }
                    projets = new ArrayList<>(Main.getEquipe().getProjetsMunicipaux());
                    if(projets.isEmpty())
                        System.out.println("Impossible de créer un sac à partir d'aucun projet dans la municipalité");
                    else{
                        dim = Secteur.values().length;
                        Map<Secteur, Integer> budgetMap = new EnumMap<>(Secteur.class);
                        for(Secteur s : Secteur.values()){
                            System.out.println("Entrez le budget pour " + s);
                            budgetMap.put(s, Common.lireEntierPositif(scanner));
                        }
                        Main.setSac(VersSacADos.depuisProjetParSecteurs(projets, budgetMap));
                        System.out.println("Sac à dos crée avec succes !");
                    }
                    break;
                case 3:
                    String chemin = Sauvegarde.choixSauvegarde("data/sacSave", scanner);
                    if(chemin == null)
                        break;
                    sac = VersSacADos.depuisFichier(chemin);
                    if(sac != null){
                        System.out.println("Sac à dos créé avec succes !");
                        Main.setSac(sac);
                    }
                    break;
                case 4:
                    sac = Main.getSac();
                    if(sac == null)
                        System.out.println("Aucun sac à sauvgarder");
                    else{
                        File f = Sauvegarde.fichierEnregistrementSauvegarde("data/sacSave", scanner, "dat");
                        Sauvegarde.enregistrerDansFichier(sac, f);
                    }
                    break;
                case 5:
                    sac = Main.getSac();
                    if(sac == null)
                        System.out.println("Aucun sac à dos chargé.");
                    else 
                        System.out.println(sac);
                    break;
                case 6:
                    sac = Main.getSac();
                    if(sac == null)
                        System.out.println("Aucun sac à dos chargé.");
                    else
                        for(Objet o : sac.getObjets())
                            System.out.println(o);
                    break;
                case 0:
                    return;

            }
        }
    }
    

    /**
     * Menu des solveurs. Permet de créer des solutions locales gloutonnes, et des solutions locales HillClimbing
     * 
     * @param scanner le Scanner à utiliser pour la lecture
     */
    public static void menuSolveur(Scanner scanner){
        SacADos sol = null;
        SacADos solClimbing = null;
        int choix = 0;
        while(true){
            System.out.println(".---------------------------------.");
            System.out.println("|          Menu Solveur           |");
            System.out.println("|---------------------------------|");
            System.out.println("| 1. Glouton                      |");
            System.out.println("|---------------------------------|");
            System.out.println("| 2. Hill Climbing                |");
            System.out.println("|---------------------------------|");
            System.out.println("| 3. Afficher Solutions           |");
            System.out.println("|---------------------------------|");
            System.out.println("| 0. Retour au menu principal     |");
            System.out.println("|_________________________________|");
            
            System.out.println("Entrez 1, 2, 3 ou 0 !");
            choix = Common.lireEntierEntre(0, 3, scanner);
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");

            switch (choix) {
                case 1:
                    sol = lancerGlouton(Main.getSac(), scanner);
                    break;
                case 2:
                    if(sol == null)
                        System.out.println("Veuillez générer une solution initiale à l'aide d'une methode gloutonne");
                    else{
                        solClimbing = lancerHillClimbing(sol, Main.getSac(), scanner);
                    }
                    break;
                case 3:
                    if(sol == null){
                        System.out.println("Aucune solutions à afficher");
                        break;
                    }
                    System.out.println("Solution Gloutonne :");
                    System.out.println(sol);
                    for(Objet o : sol.getObjets())
                        System.out.println(o);
                    if(solClimbing == null){
                        System.out.println("Aucune solutions de HillClimbing à afficher");
                        break;
                    }
                    System.out.println("\nSolution HillClimbing :");
                    System.out.println(solClimbing);
                    for(Objet o : solClimbing.getObjets())
                        System.out.println(o);
                    
                    break;
                case 0:
                    return;
            
                default:
                    break;
            }


        }
    }

    private static SacADos lancerGlouton(SacADos sac, Scanner scanner){
        int choix;
        List<Objet> obj;
        System.out.println("Choisissez votre methode de comparaison pour l'ajout d'objet dans le sac :");
        System.out.println("1. ComparatorSomme");
        System.out.println("2. ComparatorMax");
        choix = Common.lireEntierEntre(1, 2, scanner);
        Comparator<Objet> comp;
        if(choix == 1)  comp = new ComparatorSomme();
        else            comp = new ComparatorMax();

        System.out.println("Choisissez votre methode gloutonne pour l'ajout d'objet dans le sac :");
        System.out.println("1. Glouton ajout");
        System.out.println("2. Glouton retrait");
        choix = Common.lireEntierEntre(1, 2, scanner);
        GloutonSolver glouton;

        if (choix == 1) glouton = new GloutonAjoutSolver();
        else            glouton = new GloutonRetraitSolver();
        obj = glouton.resoudre(Main.getSac(), comp);

        SacADos sacSol = new SacADos(sac.getDimension(), sac.getBudgets(), obj);
        System.out.println("Solution générée :");
        System.out.println(sacSol);

        System.out.println("Objets choisis :");
        for(Objet o : obj){
            System.out.println(o);
        }
        return sacSol;
    }

    private static SacADos lancerHillClimbing(SacADos sol, SacADos sacOrigine, Scanner scanner){
        System.out.println("Combien de voisins voulez vous generer ? (pour une génération complète, entrez 0)");
        int choix = Common.lireEntierPositif(scanner);
        HillClimbingSolver hill = new HillClimbingSolver(choix);
        SacADos sacClimber = hill.resoudre(sol, sacOrigine);

        System.out.println("Solution générée :");
        System.out.println(sacClimber);

        System.out.println("Objets choisis :");
        for(Objet o : sacClimber.getObjets()){
            System.out.println(o);
        }
        return sacClimber;
    }
}
