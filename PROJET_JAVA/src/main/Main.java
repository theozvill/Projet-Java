package main;
import java.util.Scanner;

import equipe.EquipeMunicipale;
import sacados.SacADos;
import utilitaires.Menu;

/**
 * Classe principale de l'application.
 * <p>
 * Cette classe contient le point d'entrée du programme. Elle est responsable de l'initialisation des ressources globales (scanner, équipe municipale, sac à dos)
 * et du lancement du menu interactif principal.
 * Les instances principales de l'application (équipe municipale et sac à dos courant) sont stockées sous forme d'attributs statiques
 * Cette classe ne peut être instanciée.
 */
public class Main{
    private static final Scanner scanner = new Scanner(System.in);
    private static EquipeMunicipale equipe;
    private static SacADos sac;

    private Main(){} // Class non instanciable


    /**
     * Point d'entrée principal de l'application.
     * Cette méthode lance la boucle principale du menu interactif.
     * Le programme s'exécute tant que l'utilisateur choisit de continuer.
     * 
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args){
        System.out.print("\033[0;0H");
        System.out.print("\033[2J");
        int continuer = 1;
        while(continuer == 1){
            continuer = Menu.menu(scanner);
        }
        scanner.close();
    }

    /**
     * Setter de l'équipe municipale courante.
     *
     * @param equipeMunicipale l'équipe municipale à enregistrer
     */
    public static void setEquipe(EquipeMunicipale equipeMunicipale){
        equipe = equipeMunicipale;
    }

    /**
     * Setter du sac à dos courant.
     *
     * @param newSac le sac à dos à enregistrer
     */
    public static void setSac(SacADos newSac){
        sac = newSac;
    }

    /**
     * Retourne l'équipe municipale actuellement chargée.
     *
     * @return l'équipe municipale courante
     */
    public static EquipeMunicipale getEquipe(){
        return equipe;
    }

    /**
     * Retourne le sac à dos actuellement chargé.
     *
     * @return le sac à dos courant
     */
    public static SacADos getSac(){
        return sac;
    }

}
