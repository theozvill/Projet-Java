package equipe;

/**
 * Classe abstraite représentant une personne dans l'équipe.
 */
public abstract class Personne{
    private final String nom;
    private final String prenom;
    private int age;

    /**
     * Constructeur de la classe Personne.
     *
     * @param nom le nom de la personne
     * @param prenom le prénom de la personne
     * @param age l'âge de la personne
     */
    public Personne(String nom, String prenom, int age){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    // Getters pour tous les attributs.

    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public int getAge(){
        return age;
    }
}