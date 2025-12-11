package equipe;

import java.util.Set;
import java.util.HashSet;

/**
 * Classe représentant un expert dans l'équipe.
 * Un expert hérite de {@link Personne} (classe mère) et peut proposer des projets en rapport avec ses spécialisations.
 */
public class Expert extends Personne{
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
        this.specialisations = new HashSet<>(specialisations);
    }

    /**
     * Renvoie les spécialisations de l'éxpert
     * 
     * @return un ensemble des spécialisations
     */
    public Set<Secteur> getSpecialisations(){
        return Set.copyOf(this.specialisations);
    }

    /**
     * Propose un projet si le secteur du projet correspond à une des spécialisations de l'expert.
     * 
     * @param titre le titre du projet
     * @param description la description du projet
     * @param secteur le secteur du projet
     * @return un projet proposé par l'expert
     */
    public Projet proposerProjet(String titre, String description, Secteur secteur){
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

    /**
     * Retire une spécialisation de l'expert.
     * 
     * @param secteur la spécialisation à retirer
     */
    public void removeSpecialisation(Secteur secteur){
        this.specialisations.remove(secteur);
    }

    @Override
    public String toString(){
        return "Expert: " + super.toString() + ", specialisations=" + specialisations;
    }
}