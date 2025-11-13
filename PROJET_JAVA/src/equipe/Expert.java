package equipe;

import java.util.Set;

/**
 * Classe représentant un expert dans l'équipe.
 * Un expert hérite de {@link Personne} (classe mère) et peut proposer des projets en rapport avec ses spécialisations.
 */
public class Expert extends Personne{
    /**
     * Les spécialisations de l'expert, déterminant les secteurs dans lesquels il peut proposer des projets.
     */
    private Set<Secteur> specialisations;

    /**
     * Constructeur de la classe Expert.
     *
     * @param nom le nom de l'expert
     * @param prenom le prénom de l'expert
     * @param age l'âge de l'expert
     * @param specialisations les spécialisations de l'expert
     */
    public Expert(String nom, String prenom, int age, Set<Secteur> specialisations){
        super(nom, prenom, age);
        this.specialisations = specialisations;
    }


    public Set<Secteur> getSpecialisations(){
        return this.specialisations;
    }

    /**
     * Propose un projet si le secteur du projet correspond à une des spécialisations de l'expert.
     * 
     * @param titre le titre du projet
     * @param description la description du projet
     * @param secteur le secteur d'activité du projet
     * @return le projet proposé
     * @throws IllegalArgumentException si le secteur du projet ne correspond pas à une des spécialisations de l'expert
     */
    public Projet proposerProjet(String titre, String description, Secteur secteur){
        if(!(this.specialisations.contains(secteur)))
            throw new IllegalArgumentException("Le secteur du projet ne correspond pas à une des spécialisations de l'expert.");

        return new Projet(titre, description, secteur);
    }

    /**
     * Ajoute une spécialisation à l'expert.
     * 
     * @param secteur la spécialisation à ajouter
     */    
    public void addSpecialisation(Secteur secteur){
        this.specialisations.add(secteur);
    }

    public void removeSpecialisation(Secteur secteur){ // RAJOUTER LES EXCEPTIONS SI INEXISTANT ?
        this.specialisations.remove(secteur);
    }
    
}