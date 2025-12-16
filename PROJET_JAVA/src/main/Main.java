package main;
import java.util.Scanner;

import equipe.EquipeMunicipale;
import sacados.SacADos;
import utilitaires.Menu;

public class Main{
    private static final Scanner scanner = new Scanner(System.in);
    private static EquipeMunicipale equipe;
    private static SacADos sac;

    public static void main(String[] args){
        // System.out.println(System.getProperty("user.dir"));
        System.out.print("\033[0;0H");
        System.out.print("\033[2J");
        int continuer = 1;
        while(continuer == 1){
            continuer = Menu.menu(scanner);
        }
        scanner.close();
    }

    public static void setEquipe(EquipeMunicipale equipeMunicipale){
        equipe = equipeMunicipale;
    }

    public static void setSac(SacADos newSac){
        sac = newSac;
    }

    public static EquipeMunicipale getEquipe(){
        return equipe;
    }

    public static SacADos getSac(){
        return sac;
    }

}
