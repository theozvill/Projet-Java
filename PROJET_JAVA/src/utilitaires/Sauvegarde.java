package utilitaires;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import equipe.EquipeMunicipale;
import sacados.Objet;
import sacados.SacADos;

public class Sauvegarde {
    
    /**
     * Sauvegarde l'équipe municipale dans un fichier.
     *
     * @param equipe l'équipe municipale à sauvegarder
     * @param cheminDossier chemin du dossier où la sauvegarde doit être enregistée
     * @param scanner le Scanner à utiliser pour la lecture
     */
    public static void sauvegarderEquipe(EquipeMunicipale equipe, String cheminDossier, Scanner scanner) {
        if(equipe == null){
            System.out.println("Aucune équipe municipale à sauvegarder.");
            return;
        }

        System.out.print("Entrez un nom pour le fichier (sans extension) : ");
        String nom = scanner.nextLine().trim();
        if(nom.isEmpty() || !nom.matches("[a-zA-Z0-9_-]+")){
            System.out.println("Nom invalide. Lettres, chiffres, _ et - uniquement.");
            return;
        }

        File fichier = new File(cheminDossier + "/" + nom + ".ser");
        if (fichier.exists()) {
            System.out.print("Le fichier existe déjà. Écraser ? (o/n) : ");
            String rep = scanner.nextLine();
            if (!rep.equalsIgnoreCase("o")) {
                System.out.println("Sauvegarde annulée.");
                return;
            }
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))){
            oos.writeObject(equipe);
            System.out.println("Equipe municipale sauvegardée !");
        } catch(Exception e){
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Charge l'équipe municipale depuis un fichier.
     *
     * @param chemin chemin du fichier à charger
     * @return l'équipe municipale chargée, ou null si une erreur survient
     */
    public static EquipeMunicipale chargerEquipe(String chemin){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chemin))){
            return (EquipeMunicipale) ois.readObject();
        } catch(FileNotFoundException e){
            System.out.println("Aucune sauvegarde trouvée : " + chemin);
            return null;
        } catch(Exception e){
            System.out.println("Erreur lors du chargement de l'équipe municipale : " + e.getMessage());
            return null;
        }
    }


    /**
     * Ecrit dans un fichier un sac à dos à partir des données.
     * Le format du fichier sera :
     * - Première ligne : nombre d'objets, dimension, (optionnellement solution optimale)
     * - Deuxième ligne : utilités des objets
     * - Lignes suivantes : matrice des coûts (une ligne par dimension)
     * - Dernière ligne : budgets pour chaque dimension
     * 
     * @param sac le sac à enregistrer
     * @param cheminDossier le numéro associé à la sauvegarde
     * @param scanner le Scanner à utiliser pour la lecture
     */
    public static void enregistrerDansFichier(SacADos sac, String cheminDossier, Scanner scanner){
        if(sac == null){
            System.out.println("Aucun sac à sauvgarder");
            return;
        }
        
        System.out.print("Entrez un nom pour le fichier (sans extension) : ");
        String nom = scanner.nextLine().trim();
        if(nom.isEmpty() || !nom.matches("[a-zA-Z0-9_-]+")){
            System.out.println("Nom invalide. Lettres, chiffres, _ et - uniquement.");
            return;
        }

        File fichier = new File(cheminDossier + "/" + nom + ".dat");
        if (fichier.exists()) {
            System.out.print("Le fichier existe déjà. Écraser ? (o/n) : ");
            String rep = scanner.nextLine();
            if (!rep.equalsIgnoreCase("o")) {
                System.out.println("Sauvegarde annulée.");
                return;
            }
        }

        // Debut de l'enregistrement
        List<Objet> objets = sac.getObjets();
        int n = sac.getObjets().size();
        int k = sac.getDimension();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))){

            // Ecriture n k optimale (ici = 0)
            bw.write(String.valueOf(n) + " " + String.valueOf(k) + " " + String.valueOf(0));
            bw.newLine();

            // Ecriture de l'utilité
            int[][] coutsTot = new int[n][k];
            int c = 0;
            for(Objet o : objets){
                bw.write(String.valueOf(o.getUtilite()) + " ");
                coutsTot[c++] = o.getCouts();
            }
            bw.newLine();

            // Ecriture des couts
            for(int i = 0; i < k; i++){
                for(int j = 0; j<n; j++){
                    bw.write(String.valueOf(coutsTot[j][i]) + " ");
                }
                bw.newLine();
            }

            // Ecriture du budget
            for(int b : sac.getBudgets()){
                bw.write(String.valueOf(b) + " ");
            }
            System.out.println("Equipe municipale sauvegardée !");

        }catch(IOException e){
            System.out.println("Erreur lors de l'enregistrement du sac" + e.getMessage());
        }
    }

    /**
     * Demande à l'utilisateur le fichier de sauvegarde à charger dans le dossier de son choix
     * 
     * @param cheminDossier chemin relatif du dossier
     * @param scanner le Scanner à utiliser pour la lecture
     * @return chemin du fichier à charger
     */
    public static String choixSauvegarde(String cheminDossier, Scanner scanner){
        File dossier = new File(cheminDossier);
        if(!dossier.exists() || !dossier.isDirectory()){
            System.out.println("Dossier introuvable" + cheminDossier);
            return null;
        }

        File[] fichiers = dossier.listFiles();

        if(fichiers == null || fichiers.length == 0){
            System.out.println("Aucun fichier trouvé dans " + cheminDossier);
            return null;
        }

        System.out.println("Choisissez le numero de sauvegarde :");
        for(int i = 0; i < fichiers.length; i++){
            System.out.printf("%d. %s\n", i+1, fichiers[i].getName());
        }
        int choix = -1;
        System.out.print("Choisissez un fichier (1-" + fichiers.length + ") : ");
        choix = Common.lireEntierEntre(1,fichiers.length, scanner);

        File fichierChoisi = fichiers[choix-1];
        String chemin = fichierChoisi.getPath();

        
        return chemin;
    }
}
