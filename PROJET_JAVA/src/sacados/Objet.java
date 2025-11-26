package sacados;

public class Objet{
    private int utilite;
    //On veut au moins 3 valuers pour les couts : economique, social, ecologique
    //D'autre peuvent être ajoutés en bonus (pos. ou neg.) : externalités, politique, culturel, reputationnel...
    private int[] couts;

    //Constructeurs par defaut
    public Objet(){
        utilite = 0;
        couts = new int[]{0, 0, 0};
    }

    //Constructeurs complet
    public Objet(int utilite, int[] couts){
        if(utilite <= 0){
            throw new IllegalArgumentException("L'utilité doit être > 0\n");

        }else{
            this.utilite = utilite;
        }

        if(couts == null || couts.length < 3){
            throw new IllegalArgumentException("Erreur :  Au moins 3 couts doivent être définis (economique, social, ecologique)\n");
        }else{
            this.couts = couts.clone();
        }
        
    }

    
    //Les getters
    public int getUtilite(){
        return utilite;
    }

    public int[] getCouts(){
        return couts.clone();
    }

    //Les setters
    public void setUtilite(int utilite){
        if(utilite <= 0){
            throw new IllegalArgumentException("L'utilité doit être > 0\n");

        }else{
            this.utilite = utilite;
        }
    }

    public void setCouts(int[] couts){
        if(couts == null || couts.length < 3){
            throw new IllegalArgumentException("Erreur :  Au moins 3 couts doivent être définis (economique, social, ecologique)\n");
        }else{
            this.couts = couts.clone();
        }
    }


    //Methode d'affichage
    public void afficherObjet(){
        System.out.println("L'utilité est : " + utilite);

        System.out.println("Les couts sont :");
        for (int val : couts){
            System.out.print(val + " ");
        }

        System.out.println("");

    }


}
