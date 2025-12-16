package equipe;


/**
 * Classe représentant un évaluateur dans l'équipe.
 * Un évaluateur hérite de {@link Personne} (classe mère) et possède une spécialisation
 * qui détermine le type de coût qu'il évalue : ECONOMIQUE, SOCIAL, ENVIRONNEMENTAL.
 */

public class Evaluateur extends Personne{
    private TypeCout specialisation;


    /**
     * Constructeur de la classe Evaluateur.
     *
     * @param nom le nom de l'évaluateur
     * @param prenom le prénom de l'évaluateur
     * @param age l'âge de l'évaluateur
     * @param specialisation la spécialisation de l'évaluateur (ECONOMIQUE, SOCIAL, ENVIRONNEMENTAL)
     */
    public Evaluateur(String nom, String prenom, int age, TypeCout specialisation){
        super(nom, prenom, age);
        this.specialisation = specialisation;
    }

    // Getter et Setter pour la spécialisation.

    /**
     * retourne la spécialisation de l'évaluateur.
     * 
     * @return la spécialisation de l'évaluateur.
     */
    public TypeCout getSpecialisation(){
        return specialisation;
    }

    /**
     * définit la spécialisation de l'évaluateur.
     * 
     * @param specialisation la nouvelle spécialisation de l'évaluateur.
     */
    public void setSpecialisation(TypeCout specialisation){
        this.specialisation = specialisation;
    }



    /**
     * Evalue un projet en estimant son coût en fonction de la spécialisation de l'évaluateur.
     * 
     * @param projet le projet à évaluer
     * @param cout le coût estimé du projet
     * @throws IllegalArgumentException si la spécialisation de l'évaluateur est inattendue ou nulle
     */
    public void evaluerProjet(Projet projet, int cout){
        switch(this.specialisation){
            case ECONOMIQUE:
                projet.setCoutEconomique(cout);
                break;
            case SOCIAL:
                projet.setCoutSocial(cout);
                break;
            case ENVIRONNEMENTAL:
                projet.setCoutEnvironnemental(cout);
                break;
            default:
                throw new IllegalArgumentException("Spécialisation null");
        }
    }

    @Override
    public String toString(){
        return "Evaluateur : " + super.toString() + " Specialisation=" + specialisation;
    }
    

}