package utilitaires;

import java.util.Scanner;

/**
 * Classe utilitaire regroupant des méthodes de lecture sécurisée depuis l'entrée standard.
 * Cette classe fournit des méthodes statiques permettant de lire des entiers depuis un {@link Scanner}.
 */
public class Common {

    private Common(){} // Class non instanciable
    
    /**
     * Lit un entier strictement positif depuis l'entrée standard.
     *
     * @param scanner le Scanner à utiliser pour la lecture
     * @return un entier strictement positif
     */
    public static int lireEntierPositifStrict(Scanner scanner){
        while(true){
            try{
                int val = Integer.parseInt(scanner.nextLine());
                if (val > 0) return val;
            } catch (NumberFormatException e){}
            System.out.print("Veuillez entrer un nombre valide > 0 : ");
        }
    }

    /**
     * Lit un entier positif depuis l'entrée standard.
     *
     * @param scanner le Scanner à utiliser pour la lecture
     * @return un entier strictement positif
     */
    public static int lireEntierPositif(Scanner scanner){
        while(true){
            try{
                int val = Integer.parseInt(scanner.nextLine());
                if (val >= 0) return val;
            } catch (NumberFormatException e){}
            System.out.print("Veuillez entrer un nombre valide >= 0 : ");
        }
    }

    /**
     * Lit un entier positif dans un intervalle.
     *
     * @param a borne inférieure
     * @param b borne supérieur
     * @param scanner le Scanner à utiliser pour la lecture
     * @return un entier strictement positif
     */
    public static int lireEntierEntre(int a, int b, Scanner scanner){
        while(true){
            try{
                int val = Integer.parseInt(scanner.nextLine());
                if (val >= a && val <= b) return val;
            } catch (NumberFormatException e){}
            System.out.printf("Veuillez entrer un nombre valide entre %d et %d :\n", a, b);
        }
    }


}
