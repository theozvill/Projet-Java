package equipe;

/**
 * Classe représentant un élu dans l'équipe.
 * Un élu hérite de {@link Personne} (classe mère) et peut évaluer les projets en estimant leur bénéfice.
 */
public class Elu extends Personne{
    
    /**
     * Constructeur de la classe Elu.
     *
     * @param nom le nom de l'élu
     * @param prenom le prénom de l'élu
     * @param age l'âge de l'élu
     */
    public Elu(String nom, String prenom, int age){
        super(nom, prenom, age);
    }

    /**
     * Évalue un projet en estimant son bénéfice.
     * 
     * @param projet le projet à évaluer
     * @param benefice le bénéfice estimé du projet
     */
    public void evaluerProjet(Projet projet, int benefice){
        projet.setBenefice(benefice);
    }
}