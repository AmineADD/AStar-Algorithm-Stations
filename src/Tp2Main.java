import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class Tp2Main {
    public static void main(String[] args) {

            if(args.length>0){
                if(args[0].equalsIgnoreCase("-h")){
                    System.out.println("Message d'aide");
                    System.out.println("Lancez le programme avec java -jar Nom.jar lienFichierStation.txt");
                }
            }else{


                Reseau rs = new Reseau();
                Scanner scanner = new Scanner( System.in );
                Tp2Main Main=new Tp2Main();

                rs.CreeReseauAPartirDuFichier(Main.getClass().getResourceAsStream("metro.txt"));
                Main.affichageMenu();
                int choix=Integer.valueOf(scanner.nextLine());
                while (choix!=3){
                    switch (choix){
                        case 1:
                            System.out.println("Veuillez saisir le nom de la station");
                            String NOM_STATION=scanner.nextLine();
                            System.out.println("stations voisines de la station "+NOM_STATION+" => "+Arrays.toString(rs.stationsVoisinesDe(NOM_STATION)));
                            Main.affichageMenu();
                            choix=Integer.valueOf(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("la recherche applique 'A* Algortihm' ");
                            System.out.println("Veuillez saisir le nom de la station 1");
                            String NOM_STATION_1=scanner.nextLine();
                            Station start_=rs.getStationByName(NOM_STATION_1);
                            System.out.println("Veuillez saisir le nom de la station 2");
                            String NOM_STATION_2=scanner.nextLine();
                            Station final_=rs.getStationByName(NOM_STATION_2);
                            System.out.println("Chemin De '"+start_.get_NomStation()+"' vers '"+final_.get_NomStation()+"'\t"+rs.cheminDeVers(start_,final_));
                            Main.affichageMenu();
                            choix=Integer.valueOf(scanner.nextLine());
                            break;
                        default:Main.affichageMenu();
                            choix=Integer.valueOf(scanner.nextLine());
                            break;
                    }
                }
                System.out.println("Au revoir !");

            }
    }
    public void affichageMenu(){
        System.out.println("\t Programmation multi-paradigme : Metro stations");
        System.out.println("\t***Pour chercher  Voisines d'une Station : Tapez 1");
        System.out.println("\t***Pour chercher le chemin le plus court entre deux stations : Tapez 2");
        System.out.println("\t***Pour Quitter : Tapez 3");
    }
}