package equipe;

/**
 * Représente un projet pour la municipalité.
 */
public class Projet{
    private String titre;
    private String description;
    private Secteur secteur;
    
    private Integer benefice;
    
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

    /**
     * retourne le titre du projet.
     * @return le titre du projet.
     */
    public String getTitre(){
        return titre;
    }
    
    /** retourne la description du projet.
     * @return la description du projet.
     */
    public String getDescription(){
        return description;
    }

    /**
     * retourne le secteur d'activité du projet.
     * @return le secteur d'activité du projet.
     */
    public Secteur getSecteur(){
        return secteur;
    }

    /** retourne le bénéfice estimé du projet.
     * @return le bénéfice estimé du projet.
     */
    public Integer getBenefice(){
        return benefice;
    }

    /** retourne le coût économique du projet.
     * @return le coût économique du projet.
     */
    public Integer getCoutEconomique(){
        return coutEconomique;
    }

    /** retourne le coût social du projet.
     * @return le coût social du projet.
     */
    public Integer getCoutSocial(){
        return coutSocial;
    }

    /** retourne le coût environnemental du projet.
     * @return le coût environnemental du projet.
     */
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