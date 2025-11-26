package sacados;
import java.util.List;
import java.util.ArrayList;


public class SacADos{
    private int dimension;
    private int[] budgets;
    private List<Objet> objets;

    //Constructeurs par defaut
    public SacADos(){
        this.dimension = 3;
        this.budgets = new int[]{0, 0, 0};
        this.objets = new ArrayList<>();
    }

    //Constructeurs complet
    public SacADos(int dimension, int[] budgets, List<Objet> objets){
        
        //Securité : aucune valeur null
        if(budgets == null){
            throw new IllegalArgumentException("Aucune valeur ne doit être null !\n");
        }
       
        //Dim >= 3 
        if(dimension < 3){
            throw new IllegalArgumentException("La dimension doit être >= 0 !\n");
        }
        
        //Coherence :  dimension avec meme taille que budget, meme nombre cout des objets avec dim
        if(dimension != budgets.length){
            throw new IllegalArgumentException("On doit avoir longueur budget = dimension\n");
        }
        for (Objet obj : objets){
            if (dimension != obj.getCouts().length || obj.getCouts() == null){
                throw new IllegalArgumentException("On doit avoir longueur cout = dimension\n");
            }
        }

        this.dimension = dimension;
        this.budgets = budgets.clone();
        this.objets = new ArrayList<>(objets);
    }
    

    //Les getters
    public int getDimension(){
        return dimension;
    }

    public int[] getBudgets(){
        return budgets.clone();
    }

    public List<Objet> getObjets(){
        return objets;
    }

    //Les setters
    public void setDimension(int dimension){
        if(dimension <= 0){
            throw new IllegalArgumentException("La dimension doit être > 0\n");
        }else{
            this.dimension = dimension;
        }
    }

    public void setBudgets(int[] budgets){
        if(budgets == null){
            throw new IllegalArgumentException("On doit avoir une valeur non null\n");
        }
        if(budgets.length != this.dimension){
            throw new IllegalArgumentException("On doit avoir longueur budget = dimension\n");

        }
        this.budgets = budgets.clone();
    }

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

    //Methode d'affichage
    public void afficherSacADos(){
        System.out.println("La dimention est : " + dimension);

        System.out.println("Les budgets sont");
        for (int val : budgets){
            System.out.println(val + " ");
        }

        System.out.print("Les objets sont");
        for (Objet obj : objets){
            obj.afficherObjet();
        }
        System.out.println("");
    }

    //Ajouter un objet
    public void addObjet(Objet objet){
        if(objets == null){
            throw new IllegalArgumentException("Un objets ne peut pas être null\n");
        }

        
        if (objet.getCouts().length != this.dimension) {
            throw new IllegalArgumentException("L'objet doit être de la bonne dimension\n");
        }

        this.objets.add(objet);
    }

}