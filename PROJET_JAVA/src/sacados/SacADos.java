package sacados;
import java.util.List;
import java.util.ArrayList;


/**
 * Classe représentant un sac à dos multidimensionnel.
 * Chaque sac à dos a une dimension, des budgets pour chaque dimension, et une liste d'objets pouvant être ajoutés au sac.
 */
public class SacADos{
    private int dimension;
    private int[] budgets;
    private List<Objet> objets;

    /**
     * Constructeur par défaut avec dimension 3, budgets nuls et liste d'objets vide.
     */
    public SacADos(){
        this.dimension = 3;
        this.budgets = new int[]{0, 0, 0};
        this.objets = new ArrayList<>();
    }

    /**
     * Constructeur avec dimension et budgets spécifiés.
     * @param dimension La dimension du sac à dos.
     * @param budgets Le tableau des budgets pour chaque dimension.
     * @throws IllegalArgumentException Si la dimension est inférieure à 3 ou si la taille du tableau des budgets ne correspond pas à la dimension.
     */
    public SacADos(int dimension, int[] budgets){
        this(dimension, budgets, new ArrayList<>());
    }

    /**
     * Constructeur avec dimension, budgets et liste d'objets spécifiés.
     * @param dimension La dimension du sac à dos.
     * @param budgets Le tableau des budgets pour chaque dimension.
     * @param objets La liste des objets pouvant être ajoutés au sac.
     * @throws IllegalArgumentException Si la dimension est inférieure à 3, si la taille du tableau des budgets ne correspond pas à la dimension, ou si les objets n'ont pas la bonne dimension.
     */
    public SacADos(int dimension, int[] budgets, List<Objet> objets){
        
        //Securité : aucune valeur null
        if(budgets == null || objets == null){
            throw new IllegalArgumentException("Aucune valeur ne doit être null !\n");
        }
        
        //Dim >= 3 
        if(dimension < 3){
            throw new IllegalArgumentException("La dimension doit être >= 3 !\n");
        }
        
        //Coherence :  dimension avec meme taille que budget, meme nombre cout des objets avec dim
        if(dimension != budgets.length){
            throw new IllegalArgumentException("On doit avoir longueur budget = dimension\n");
        }
        
        for (Objet obj : objets){
            if (obj.getCouts() == null || obj.getCouts().length != dimension){
                throw new IllegalArgumentException("On doit avoir longueur cout = dimension\n");
            }
        }

        this.dimension = dimension;
        this.budgets = budgets.clone();
        this.objets = new ArrayList<>(objets);
    }
    

    /**
     * Getter pour la dimension du sac à dos.
     * 
     * @return La dimension du sac à dos.
     */
    public int getDimension(){
        return dimension;
    }

    /**
     * Getter pour les budgets du sac à dos.
     * 
     * @return Le tableau des budgets du sac à dos.
     */
    public int[] getBudgets(){
        return budgets.clone();
    }

    /**
     * Getter pour la liste des objets du sac à dos.
     * 
     * @return La liste des objets du sac à dos.
     */
    public List<Objet> getObjets(){
        return List.copyOf(objets);
    }

    /**
     * Setter pour la dimension du sac à dos.
     * 
     * @param dimension La dimension à définir.
     * @throws IllegalArgumentException Si la dimension est inférieure ou égale à 0.
     */
    public void setDimension(int dimension){
        if(dimension <= 0){
            throw new IllegalArgumentException("La dimension doit être > 0\n");
        }else{
            this.dimension = dimension;
        }
    }

    /**
     * Setter pour les budgets du sac à dos.
     * 
     * @param budgets Le tableau des budgets à définir.
     * @throws IllegalArgumentException Si le tableau des budgets est null ou si sa taille ne correspond pas à la dimension du sac.
     */
    public void setBudgets(int[] budgets){
        if(budgets == null){
            throw new IllegalArgumentException("On doit avoir une valeur non null\n");
        }
        if(budgets.length != this.dimension){
            throw new IllegalArgumentException("On doit avoir longueur budget = dimension\n");

        }
        this.budgets = budgets.clone();
    }

    /**
     * Setter pour la liste des objets du sac à dos.
     * 
     * @param objets La liste des objets à définir.
     * @throws IllegalArgumentException Si la liste des objets est null ou si un objet dans la liste est null ou n'a pas la bonne dimension.
     */
    public void setObjets(List<Objet> objets){
        if(objets == null){
            throw new IllegalArgumentException("La liste d'objets ne peut pas être null\n");
        }

        for (Objet obj : objets) {
            if (obj == null) {
                throw new IllegalArgumentException("Un objet dans la liste est null\n");
            }
            if (obj.getCouts().length != this.dimension) {
                throw new IllegalArgumentException("Chaque objet doit avoir exactement 'dimension' couts\n");
            }
        }

        this.objets = new ArrayList<>(objets);
    }

    /**
     * Méthode pour ajouter un objet au sac à dos.
     * 
     * @param objet L'objet à ajouter.
     * @throws IllegalArgumentException Si l'objet est null ou n'a pas la bonne dimension.
     */
    public void addObjet(Objet objet){
        if(objet == null){
            throw new IllegalArgumentException("Un objets ne peut pas être null\n");
        }

        
        if (objet.getCouts().length != this.dimension) {
            throw new IllegalArgumentException("L'objet doit être de la bonne dimension\n");
        }

        this.objets.add(objet);
    }

    /**
     * Vérifie si le sac à dos respecte les contraintes de budget.
     * 
     * @return true si les contraintes sont respectées, false sinon.
     */
    public boolean respecteContraintes(){
        int[] sommes = new int[this.dimension];

        for (Objet o : this.objets){
            int[] c = o.getCouts();

            for(int i = 0; i < this.dimension; i++){
                sommes[i] += c[i];
                if (sommes[i] > this.budgets[i]) return false;
            }
        }
        return true;
    }

    /**
     * Calcule l'utilité totale des objets dans le sac à dos.
     * 
     * @return L'utilité totale.
     */
    public int utiliteTotale(){
        int tot = 0;
        for (Objet o : this.objets) tot += o.getUtilite();
        return tot;
    }

    @Override
    public String toString() {
        return """
                ┌────────── Sac à Dos ──────────
                │ Dimension : %d
                │ Budgets : %s
                │ Utilité totale : %d
                │ Nombre d'objets : %d
                └────────────────────────────────
                """.formatted(
                    dimension,
                    java.util.Arrays.toString(budgets),
                    utiliteTotale(),
                    objets.size()
                );
    }

}