package sacados;

/**
 * Représente un objet pouvant être placé dans le sac à dos.
 */
public class Objet{
    private int utilite;
    //On veut au moins 3 valuers pour les couts : economique, social, ecologique
    //D'autre peuvent être ajoutés en bonus (pos. ou neg.) : externalités, politique, culturel, reputationnel...
    private int[] couts;

    /**
     * Constructeur par défaut avec utilité 0 et coûts nuls.
     */
    public Objet(){
        utilite = 0;
        couts = new int[]{0, 0, 0};
    }

    /**
     * Constructeur avec utilité et coûts spécifiés.
     *
     * @param utilite L'utilité de l'objet.
     * @param couts Le tableau des coûts de l'objet.
     */
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

    
    /**
     * Getter pour l'utilité de l'objet.
     * 
     * @return L'utilité de l'objet.
     */
    public int getUtilite(){
        return utilite;
    }

    /**
     * Getter pour les coûts de l'objet.
     * 
     * @return Le tableau des coûts de l'objet.
     */
    public int[] getCouts(){
        return couts.clone();
    }

    /**
     * Setter pour l'utilité de l'objet.
     * 
     * @param utilite L'utilité à définir.
     */
    public void setUtilite(int utilite){
        if(utilite <= 0){
            throw new IllegalArgumentException("L'utilité doit être > 0\n");

        }else{
            this.utilite = utilite;
        }
    }

    /**
     * Setter pour les coûts de l'objet.
     * 
     * @param couts Le tableau des coûts à définir.
     */
    public void setCouts(int[] couts){
        if(couts == null || couts.length < 3){
            throw new IllegalArgumentException("Erreur :  Au moins 3 couts doivent être définis (economique, social, ecologique)\n");
        }else{
            this.couts = couts.clone();
        }
    }

    @Override
    public String toString() {
        return "Objet{" + "utilite=" + utilite + ", couts=" + java.util.Arrays.toString(couts) +'}';
    }

}
