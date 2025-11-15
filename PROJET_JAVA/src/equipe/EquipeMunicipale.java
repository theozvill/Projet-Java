package equipe;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

public class EquipeMunicipale{
    private Elu elu;
    private Map<TypeCout, Evaluateur> evaluateurs;
    private List<Expert> experts;

    private List<Projet> projetsMunicipaux;

    private Random random = new Random();


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
        this.evaluateurs = evaluateurs;
        this.experts = experts;
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
        return this.evaluateurs;
    }

    /**
     * retourne la liste des experts dans l'équipe municipale.
     * 
     * @return la liste des experts dans l'équipe municipale.
     */
    public List<Expert> getExperts(){
        return this.experts;
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
        this.evaluateurs = evaluateurs;
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
        this.experts = experts;
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
     * Chaque expert propose un projet, chaque évaluateur évalue le projet selon sa spécialisation,
     * puis l'élu évalue le projet en estimant son bénéfice.
     * 
     * @return la liste des projets approuvés à la fin du cycle
     */
    public List<Projet> simulerCycle(){
        ArrayList<Projet> projetsApprouves = new ArrayList<>();
        Projet p;

        // Chaque expert propose un projet
        for(Expert e : this.experts){
            p = e.proposerProjet();

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



    private int genererCout(TypeCout tc){
        switch(tc){
            case ECONOMIQUE:
                return 10000 + random.nextInt(150001);   // Cout du projet entre 10k et 200k
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

}