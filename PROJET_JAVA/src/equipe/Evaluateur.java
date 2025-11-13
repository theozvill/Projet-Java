package equipe;

/**
 * Classe représentant un évaluateur dans l'équipe.
 * Un évaluateur hérite de {@link Personne} (classe mère) et possède une spécialisation
 * qui détermine le type de coût qu'il évalue : ECONOMIQUE, SOCIAL, ENVIRONNEMENTAL.
 */

public class Evaluateur extends Personne{
    /**
     * La spécialisation de l'évaluateur, détermine le type de coût qu'il évalue.
     * Les valeurs possibles sont : ECONOMIQUE, SOCIAL, ENVIRONNEMENTAL.
     */
    private Cout specialisation;

    /**
     * Constructeur de la classe Evaluateur.
     *
     * @param nom le nom de l'évaluateur
     * @param prenom le prénom de l'évaluateur
     * @param age l'âge de l'évaluateur
     * @param specialisation la spécialisation de l'évaluateur (ECONOMIQUE, SOCIAL, ENVIRONNEMENTAL)
     */
    public Evaluateur(String nom, String prenom, int age, Cout specialisation){
        super(nom, prenom, age);
        this.specialisation = specialisation;
    }

    // Getter et Setter pour la spécialisation.

    public Cout getSpecialisation(){
        return specialisation;
    }

    public void setSpecialisation(Cout specialisation){
        this.specialisation = specialisation;
    }



    /**
     * Évalue un projet en fonction de la spécialisation de l'évaluateur.
     * 
     * @param projet le projet à évaluer
     * @param note la note attribuée au projet pour le type de coût correspondant
     * @throws IllegalArgumentException si la spécialisation de l'évaluateur est inattendue ou nulle
     */
    public void evaluerProjet(Projet projet, int note){
        switch(this.specialisation){
            case ECONOMIQUE:
                projet.setCoutEconomique(note);
                break;
            case SOCIAL:
                projet.setCoutSocial(note);
                break;
            case ENVIRONNEMENTAL:
                projet.setCoutEnvironnemental(note);
                break;
            default:
                throw new IllegalArgumentException("Spécialisation inattendue ou nulle : " + this.specialisation);
        }
    }
}