package equipe;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import utilitaires.Common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Classe représentant une équipe municipale composée d'un élu, 3 évaluateurs et plusieurs experts.
 */
public class EquipeMunicipale implements Serializable{
    private Elu elu;
    private Map<TypeCout, Evaluateur> evaluateurs;
    private List<Expert> experts;

    private List<Projet> projetsMunicipaux;

    private transient Random random = new Random();
    
    // Pour ne pas avoir la même seed pour chaque sauvegarde. Elle est éxécuté à la désérialisation.
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Désérialisation des attributs non transient (random vaut null)
        random = new Random();  
    }


    /**
     * Constructeur de la classe EquipeMunicipale.
     * 
     * @param elu l'élu de l'équipe municipale
     * @param evaluateurs la map des évaluateurs associés aux types de coûts
     * @param experts la liste des experts dans l'équipe municipale
     * @throws IllegalArgumentException si l'élu est null, si le nombre d'évaluateurs n'est pas égal à 3, ou si la liste des experts est vide
     */
    public EquipeMunicipale(Elu elu, Map<TypeCout, Evaluateur> evaluateurs, List<Expert> experts){
        if(elu == null){
            throw new IllegalArgumentException("Elu ne peut être null.");
        }
        if(evaluateurs.size() != 3){
            throw new IllegalArgumentException("Il doit y avoir exactement 3 évaluateurs, un pour chaque type de coût.");
        }
        if(experts == null || experts.isEmpty()){
            throw new IllegalArgumentException("L'équipe doit contenir au moins un expert.");
        }

        this.elu = elu;
        this.evaluateurs = new java.util.HashMap<>(evaluateurs);
        this.experts = new ArrayList<>(experts);
        this.projetsMunicipaux = new ArrayList<>();
    }

    /* ----------------------------------- Getters --------------------------------- */

    /**
     * retourne l'élu de l'équipe municipale.
     * 
     * @return l'élu de l'équipe municipale.
     */
    public Elu getElu(){
        return this.elu;
    }

    /**
     * retourne la map des évaluateurs associés aux types de coûts.
     * 
     * @return la map des évaluateurs associés aux types de coûts.
     */
    public Map<TypeCout, Evaluateur> getEvaluateurs(){
        return Map.copyOf(this.evaluateurs);
    }

    
    /**
     * retourne la liste des experts dans l'équipe municipale.
     * 
     * @return la liste des experts dans l'équipe municipale.
     */
    public List<Expert> getExperts(){
        return List.copyOf(this.experts);
    }

    /**
     * retourne la liste des projets municipaux.
     * 
     * @return la liste des projets municipaux.
     */
    public List<Projet> getProjetsMunicipaux(){
        return List.copyOf(this.projetsMunicipaux);
    }

    /* ----------------------------------- Setters --------------------------------- */

    /**
     * Modifie l'élu de l'équipe municipale.
     * 
     * @param elu le nouvel élu de l'équipe municipale
     * @throws IllegalArgumentException si l'élu est null
     */
    public void setElu(Elu elu){
        if(elu == null){
            throw new IllegalArgumentException("Elu ne peut être null.");
        }
        this.elu = elu;
    }

    /**
     * Modifie la map des évaluateurs associés aux types de coûts.
     * 
     * @param evaluateurs la nouvelle map des évaluateurs associés aux types de coûts
     * @throws IllegalArgumentException si le nombre d'évaluateurs n'est pas égal à 3
     */
    public void setEvaluateurs(Map<TypeCout, Evaluateur> evaluateurs){
        if(evaluateurs.size() != 3){
            throw new IllegalArgumentException("Il doit y avoir exactement 3 évaluateurs, un pour chaque type de coût.");
        }
        this.evaluateurs = new HashMap<>(evaluateurs);
    }

    /**
     * Modifie la liste des experts dans l'équipe municipale.
     * 
     * @param experts la nouvelle liste des experts dans l'équipe municipale
     * @throws IllegalArgumentException si la liste des experts est vide
     */
    public void setExperts(List<Expert> experts){
        if(experts == null || experts.isEmpty()){
            throw new IllegalArgumentException("L'équipe doit contenir au moins un expert.");
        }
        this.experts = new ArrayList<>(experts);
    }

    /* ----------------------------------- Méthodes --------------------------------- */

    /**
     * Ajoute un expert à l'équipe municipale.
     * 
     * @param expert l'expert à ajouter
     */
    public void addExpert(Expert expert){
        this.experts.add(expert);
    }

    /**
     * Supprime un expert de l'équipe municipale.
     * 
     * @param expert l'expert à supprimer
     * @throws IllegalArgumentException si la suppression de l'expert laisserait l'équipe sans experts
     */
    public void removeExpert(Expert expert){
        if(this.experts.size() <= 1){
            throw new IllegalArgumentException("L'équipe doit contenir au moins un expert.");
        }
        this.experts.remove(expert);
    }

    /**
     * Simule un cycle complet de proposition et d'évaluation de projets.
     * Chaque expert propose un projet dans un secteur aléatoire à ses expertises. Chaque évaluateur évalue le projet selon sa spécialisation,
     * puis l'élu évalue le projet en estimant son bénéfice.
     * 
     * @return la liste des projets approuvés à la fin du cycle
     */
    public List<Projet> simulerCycle(){
        List<Projet> projetsApprouves = new ArrayList<>();
        Projet p;

        // Chaque expert propose un projet
        for(Expert e : this.experts){

            List<Secteur> spe = new ArrayList<>(e.getSpecialisations());    // On transforme le Set en List pour un acces indexé
            Secteur secteur = spe.get(random.nextInt(spe.size()));
            String titre = genererTitre(secteur);
            String description = "Description du projet dans le secteur " + secteur;
            p = e.proposerProjet(titre, description, secteur);

            // Chaque évaluateur donne un cout au projet
            for(Map.Entry<TypeCout,Evaluateur> entry : evaluateurs.entrySet()){
                TypeCout tc = entry.getKey();
                Evaluateur eval = entry.getValue();

                int cout = genererCout(tc);
                eval.evaluerProjet(p, cout);
            }

            // L'élu évalue le projet en estimant son bénéfice
            int benefice = genererBenefice();
            elu.evaluerProjet(p, benefice);
            projetsApprouves.add(p);
        }

        projetsMunicipaux.addAll(projetsApprouves);
        return projetsApprouves;
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

    private int genererCout(TypeCout tc){
        switch(tc){
            case ECONOMIQUE:
                return 10000 + random.nextInt(190001);   // Cout du projet entre 10k et 200k
            case SOCIAL:
                return random.nextInt(101);
            case ENVIRONNEMENTAL:
                return random.nextInt(101);
            default:
                throw new IllegalArgumentException("Type de cout inattendu ou null : " + tc);
        }
    }

    private int genererBenefice(){
        return 5000 + random.nextInt(15001);   // Bénéfice entre 5k et 20k
    }

    @Override
    public String toString() {
        return """
                ┌────────── Équipe Municipale ──────────
                │ Élu : %s %s
                │ Nombre d'évaluateurs : %d
                │ Nombre d'experts : %d
                └────────────────────────────────────────
                """.formatted(
                    elu.getNom(),
                    elu.getPrenom(),
                    evaluateurs.size(),
                    experts.size()
                );
    }

    /**
     * Crée une équipe municipale en interagissant avec l'utilisateur via le scanner fourni.
     * 
     * @param scanner le scanner pour lire les entrées utilisateur
     * @return une nouvelle instance d'EquipeMunicipale
     */
    public static EquipeMunicipale creerEquipe(Scanner scanner){
        // Création de l'élu
        System.out.print("Entrez le nom de l'élu : ");
        String nomElu = scanner.nextLine();
        System.out.print("Entrez le prénom de l'élu : ");
        String prenomElu = scanner.nextLine();
        System.out.print("Entrez l'âge de l'élu : ");
        int ageElu = Common.lireEntierPositifStrict(scanner);
        Elu elu = new Elu(nomElu, prenomElu, ageElu);
        System.out.print("\033[0;0H");
        System.out.print("\033[2J");

        // Création des évaluateurs
        EnumMap<TypeCout, Evaluateur> evaluateurs = new EnumMap<>(TypeCout.class);
        for(TypeCout tc : TypeCout.values()){
            System.out.printf("Entrez le nom de l'évaluateur secteur %s : \n", tc);
            String nomEval = scanner.nextLine();
            System.out.printf("Entrez le prénom de l'évaluateur secteur %s : \n", tc);
            String prenomEval = scanner.nextLine();
            System.out.printf("Entrez l'âge de l'évaluateur secteur %s : \n", tc);
            int ageEval = Common.lireEntierPositifStrict(scanner);
            evaluateurs.put(tc, new Evaluateur(nomEval, prenomEval, ageEval, tc));
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");
        }
        
        // Création des experts
        System.out.print("Entrez le nombre d'experts dans l'équipe (supperieur à 0): ");
        int nbExperts = Common.lireEntierPositifStrict(scanner);

        List<Expert> experts = new ArrayList<>();
        for(int i = 0; i < nbExperts; i++){
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");

            System.out.printf("Expert %d:\n", i + 1);
            System.out.print("Entrez le nom : ");
            String nomExpert = scanner.nextLine();
            System.out.print("Entrez le prénom : ");
            String prenomExpert = scanner.nextLine();
            System.out.print("Entrez l'âge : ");
            Integer ageExpert = Common.lireEntierPositifStrict(scanner);

            
            Set<Secteur> secteurs = new HashSet<>();
            while(secteurs.isEmpty()){  // On veut au moins une spécialisation
                System.out.println("Entrez les secteurs de compétence (séparés par des espaces) parmi " + List.of(Secteur.values()));
                String[] secteursTab = scanner.nextLine().toUpperCase().split(" ");
                for(String s : secteursTab){
                    try{
                        secteurs.add(Secteur.valueOf(s));
                    } catch(IllegalArgumentException e){
                        System.out.printf("Secteur invalide ignoré : %s\n", s);
                    }
                }
            }
            experts.add(new Expert(nomExpert, prenomExpert, ageExpert, secteurs));
        }
        System.out.print("\033[0;0H");
        System.out.print("\033[2J");
        return new EquipeMunicipale(elu, evaluateurs, experts);
    }

}