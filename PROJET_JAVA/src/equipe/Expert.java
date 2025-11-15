package equipe;

import java.util.Set;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe représentant un expert dans l'équipe.
 * Un expert hérite de {@link Personne} (classe mère) et peut proposer des projets en rapport avec ses spécialisations.
 */
public class Expert extends Personne{
    private Set<Secteur> specialisations;

    private Random random = new Random();

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

    /**
     * Renvoie les spécialisations de l'éxpert
     * 
     * @return un ensemble des spécialisations
     */
    public Set<Secteur> getSpecialisations(){
        return this.specialisations;
    }

    /**
     * Propose un projet si le secteur du projet correspond à une des spécialisations de l'expert.
     * 
     * @return un projet proposé par l'expert
     */
    public Projet proposerProjet(){
        List<Secteur> specialisations = new ArrayList<>(this.specialisations);  // Convertir l'ensemble en liste pour un accès indexé
        Secteur secteur = specialisations.get(random.nextInt(specialisations.size()));
        String titre = genererTitre(secteur);
        String description = "Description du projet dans le secteur " + secteur;

        return new Projet(titre, description, secteur);
    }

    private String genererTitre(Secteur secteur){
        switch(secteur){
            case SPORT:
                return "Construction d'un nouveau complexe sportif";
            case SANTE:
                return "Amélioration des infrastructures de santé locales";
            case EDUCATION:
                return "Mise à jour des équipements scolaires";
            case CULTURE:
                return "Organisation d'un festival culturel annuel";
            case ATTRACTIVITE_ECONOMIQUE:
                return "Création d'une zone d'activités économiques";
            default:
                return "Projet dans le secteur " + secteur;
        }
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
    
}