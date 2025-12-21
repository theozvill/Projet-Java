package equipe;

import java.io.Serializable;

/**
 * Représente un projet pour la municipalité.
 */
public class Projet implements Serializable{
    private String titre;
    private String description;
    private Secteur secteur;
    
    private Integer benefice;   // Integer pour pouvoir valoir null si pas encore définit
    
    private Integer coutEconomique;
    private Integer coutSocial;
    private Integer coutEnvironnemental;


    /**
     * Constructeur de la classe Projet.
     * 
     * @param titre Le titre du projet.
     * @param description La description du projet.
     * @param secteur Le secteur d'activité du projet.
     * @throws IllegalArgumentException si une valeur est nulle ou si le titre est vide.
     */
    public Projet(String titre, String description, Secteur secteur){
        if (titre == null || titre.isBlank())
            throw new IllegalArgumentException("Le titre ne peut pas être vide");

        if (description == null)
            throw new IllegalArgumentException("La description ne peut pas être nulle");

        if (secteur == null)
            throw new IllegalArgumentException("Le secteur est obligatoire");
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
    }

    /**
     * Construit un projet à partir de son secteur, de son bénéfice et de ses coûts.
     *
     * @param titre titre du projet
     * @param description description du projet
     * @param secteur secteur d'activité
     * @param benefice bénéfice du projet
     * @param coutEconomique coût économique
     * @param coutSocial coût social
     * @param coutEnvironnemental coût environnemental
     * @throws IllegalArgumentException si une valeur est négative ou nulle ou si le titre est vide
     */
    public Projet(String titre, String description, Secteur secteur, int benefice, int coutEconomique, int coutSocial, int coutEnvironnemental){

        if (titre == null || titre.isBlank())
            throw new IllegalArgumentException("Le titre ne peut pas être vide");

        if (description == null)
            throw new IllegalArgumentException("La description ne peut pas être nulle");

        if (secteur == null)
            throw new IllegalArgumentException("Le secteur est obligatoire");

        if (benefice < 0 || coutEconomique < 0 || coutSocial < 0 || coutEnvironnemental < 0)
            throw new IllegalArgumentException("Les coûts et le bénéfice doivent être positifs");

        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
        this.benefice = benefice;
        this.coutEconomique = coutEconomique;
        this.coutSocial = coutSocial;
        this.coutEnvironnemental = coutEnvironnemental;
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


    // Setters pour les attributs modifiables.
    
    /**
     * Met à jour la valeur du benefice
     * @param benefice benefice du projet >= 0
     * @throws IllegalArgumentException Si le bénéfice n'est pas positif.
     */
    public void setBenefice(int benefice){
        if(benefice < 0)
            throw new IllegalArgumentException("Le bénéfice doit être positif");
        this.benefice = benefice;
    }

    /**
     * Met à jour la valeur du cout économique
     * @param coutEconomique cout économique du projet >= 0
     * @throws IllegalArgumentException Si le cout n'est pas positif.
     */
    public void setCoutEconomique(int coutEconomique){
        if(coutEconomique < 0)
            throw new IllegalArgumentException("Le cout économique doit être positif");
        this.coutEconomique = coutEconomique;
    }

    /**
     * Met à jour la valeur du cout social
     * @param coutSocial cout social du projet >= 0
     * @throws IllegalArgumentException Si le cout n'est pas positif.
     */
    public void setCoutSocial(int coutSocial){
        if(coutSocial < 0)
            throw new IllegalArgumentException("Le cout social doit être positif");
        this.coutSocial = coutSocial;
    }

    /**
     * Met à jour la valeur du cout environmental
     * @param coutEnvironnemental cout Environmental du projet >= 0
     * @throws IllegalArgumentException Si le cout n'est pas positif.
     */
    public void setCoutEnvironnemental(int coutEnvironnemental){
        if(coutEnvironnemental < 0)
            throw new IllegalArgumentException("Le cout environmental doit être positif");
        this.coutEnvironnemental = coutEnvironnemental;
    }

    @Override
    public String toString() {
        return  "Projet\n" +
                "   Titre = '" + titre + "',\n" +
                "   Description = '" + description + "',\n" +
                "   Secteur = " + secteur + ",\n" +
                "   Bénéfice = " + benefice + ",\n" +
                "   Coûts = [Eco=" + coutEconomique +
                ", Social=" + coutSocial +
                ", Env=" + coutEnvironnemental + "]\n";
    }

    
}