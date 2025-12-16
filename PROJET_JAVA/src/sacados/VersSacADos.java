package sacados;

import equipe.Projet;
import equipe.Secteur;
import equipe.TypeCout;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Classe utilitaire pour convertir des projets en sacs à dos.
 * Elle fournit des méthodes pour créer des sacs à dos à partir de listes de projets,
 * en utilisant soit les trois coûts standards (économique, social, environnemental),
 * soit en fonction des secteurs d'activité des projets.
 * Elle peut également lire un sac à dos à partir d'un certain type de fichier.
 */
public class VersSacADos {
    
    /**
     * Convertit une liste de projets en un sac à dos à 3 coûts (économique, social, environnemental).
     * Chaque projet est transformé en un objet avec son bénéfice comme valeur et ses coûts comme coûts dans le sac à dos.
     * 
     * @param projets La liste des projets à convertir.
     * @param budgets Le tableau des budgets pour les 3 coûts.
     * @return Un sac à dos représentant les projets.
     * @throws IllegalArgumentException Si la liste de projets est vide ou si les budgets sont invalides.
     */
    public static SacADos depuisProjet3couts(List<Projet> projets, int[] budgets){
        int n = TypeCout.values().length;
        if(projets.isEmpty() || budgets == null || budgets.length != n)
            throw new IllegalArgumentException("Projets ou budgets invalides pour conversion en sac à dos 3 coûts.\n");

        List<Objet> obj = new ArrayList<>();
        for(Projet p : projets){
            int[] couts = {p.getCoutEconomique(), p.getCoutSocial(), p.getCoutEnvironnemental()};
            obj.add(new Objet(p.getBenefice(), couts));
        }

        return new SacADos(n, budgets, obj);
    }

    /**
     * Convertit une liste de projets en un sac à dos multidimensionnel basé sur les secteurs d'activité.
     * Chaque secteur d'activité correspond à une dimension dans le sac à dos.
     * Chaque projet est transformé en un objet avec son bénéfice comme valeur et son coût économique dans la dimension correspondant à son secteur.
     * 
     * @param projets La liste des projets à convertir.
     * @param budgetsMap Une map associant chaque secteur d'activité à son budget.
     * @return Un sac à dos représentant les projets par secteur.
     * @throws IllegalArgumentException Si la liste de projets est vide ou si les budgets sont invalides.
     */
    public static SacADos depuisProjetParSecteurs(List<Projet> projets, Map<Secteur, Integer> budgetsMap){
        if(projets.isEmpty() || budgetsMap.isEmpty() || budgetsMap.size() != Secteur.values().length)
            throw new IllegalArgumentException("Projets ou budgets invalides pour conversion en sac à dos\n");
            
        int n = Secteur.values().length;
        List<Objet> obj = new ArrayList<>();
        int[] budgets = new int[n];
        for(Secteur s : Secteur.values())
            budgets[s.ordinal()] = budgetsMap.get(s);
        
        for(Projet p : projets){
            int[] couts = new int[n];
            couts[p.getSecteur().ordinal()] = p.getCoutEconomique();
            obj.add(new Objet(p.getBenefice(), couts));
        }
        return new SacADos(n, budgets, obj);
    }

    /**
     * Lit un fichier et crée un sac à dos à partir des données.
     * Le format du fichier doit être conforme aux spécifications attendues :
     * - Première ligne : nombre d'objets, dimension, (optionnellement solution optimale)
     * - Deuxième ligne : utilités des objets
     * - Lignes suivantes : matrice des coûts (une ligne par dimension)
     * - Dernière ligne : budgets pour chaque dimension
     * 
     * @param chemin Le chemin du fichier à lire.
     * @return Un sac à dos créé à partir des données du fichier. Ou null en cas d'erreur de lecture.
     */
    public static SacADos depuisFichier(String chemin){
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(chemin));
            StringTokenizer st = new StringTokenizer(br.readLine());

            // Lecture du nombre d'objets et de la dimension (on ignore la solution optimale)
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // Deuxième ligne
            int[] utilite = lireNEntiers(br, n);

            // Lecture de la matrice des couts
            int[][] coutsTot = new int[k][n];   // la ieme ligne correspond au ieme cout de tous les objets
            for(int i = 0; i<k; i++){
                int[] c = lireNEntiers(br, n);
                coutsTot[i] = c;
            }

            // Dernière ligne
            int[] budgets = lireNEntiers(br, k);
            
            // Création des objets
            List<Objet> objets = new ArrayList<>();
            for(int i = 0; i < n; i++){
                int[] coutsObj = new int[k];
                for(int j = 0; j<k; j++){
                    coutsObj[j] = coutsTot[j][i];
                }
                objets.add(new Objet(utilite[i], coutsObj));
            }
            
            return new SacADos(k, budgets, objets);
        
        } catch(FileNotFoundException e){
            System.out.println("Fichier non trouvé : " + e.getMessage());
            return null;
        } catch(IOException e){
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
            return null;
        } finally{
            if(br != null){
                try{
                    br.close();
                } catch(IOException e){
                    System.err.println("Erreur lors de la fermeture du fichier");
                }
            }
        }
    }

    private static int[] lireNEntiers(BufferedReader br, int n) throws IOException{
        int[] tab = new int[n];
        int i = 0;
        while(i<n){
            String line = br.readLine();
            if(line == null)
                throw new IOException("Fichier incomplet ou mal formatté\n");

            StringTokenizer st = new StringTokenizer(line);
            while(st.hasMoreTokens() && i<n)
                tab[i++] = Integer.parseInt(st.nextToken());
            
        }
        return tab;
    }

    


}
