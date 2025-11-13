package equipe;

/**
 * Représente un projet pour la municipalité.
 */
public class Projet{
    private final String titre;
    private String description;
    private final Secteur secteur;
    
    /**
     * Bénéfice éstimé par l'élu. Peut être null si non estimé.
     */
    private Integer benefice;
    
    /**
     * Coûts évalués (peuvent être null si non évalués).
     */
    private Integer coutEconomique;
    private Integer coutSocial;
    private Integer coutEnvironnemental;


    /**
     * Constructeur de la classe Projet.
     * 
     * @param titre Le titre du projet.
     * @param description La description du projet.
     * @param secteur Le secteur d'activité du projet.
     */
    public Projet(String titre, String description, Secteur secteur){
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }

    // Getters pour tous les attributs.

    public String getTitre(){
        return titre;
    }
    public String getDescription(){
        return description;
    }
    public Secteur getSecteur(){
        return secteur;
    }
    public Integer getBenefice(){
        return benefice;
    }
    public Integer getCoutEconomique(){
        return coutEconomique;
    }
    public Integer getCoutSocial(){
        return coutSocial;
    }
    public Integer getCoutEnvironnemental(){
        return coutEnvironnemental;
    }


    // Setters pour les attributs modifiables.  RAJOUTER LES EXCEPTIONS !!!!!!!
    public void setBenefice(int benefice){
        this.benefice = benefice;
    }

    public void setCoutEconomique(int coutEconomique){
        this.coutEconomique = coutEconomique;
    }

    public void setCoutSocial(int coutSocial){
        this.coutSocial = coutSocial;
    }

    public void setCoutEnvironnemental(int coutEnvironnemental){
        this.coutEnvironnemental = coutEnvironnemental;
    }
}