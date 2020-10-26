import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Array;
import java.util.*;

public class Reseau {
    private HashSet<Station> _Stations=new HashSet<>();

    public Reseau(HashSet<Station> _Stations) {
        this._Stations = _Stations;
    }
    public Reseau() {
        this._Stations = _Stations;
    }

    public HashSet<Station> get_Stations() {
        return _Stations;
    }

    public void set_Stations(HashSet<Station> _Stations) {
        this._Stations = _Stations;
    }

    public void ajoute_Station(Station st_){
        for (Station search:this._Stations) {
          if( search.transform(search.get_NomStation().trim().toLowerCase()).equalsIgnoreCase(st_.transform(st_.get_NomStation().trim().toLowerCase()))) {
              search.set_Lignes(st_.get_Lignes());
              search.ajouteVoisine((Station) st_.get_Voisines().toArray()[0]);
                return;

          }
        }
       this._Stations.add(st_);
    }



    public void CreeReseauAPartirDuFichier(InputStream nomDeFichier_) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(nomDeFichier_,"UTF-8"));
            String str;
            while ((str=bf.readLine()) != null) {
                Station station1=new Station(),station2 = new Station();
                StringTokenizer st = new StringTokenizer(str,"\"");
                ArrayList<String> Lignes=new ArrayList<>();
                while (st.hasMoreTokens()) {
                    String DATA_IN=st.nextToken();
                    if(DATA_IN.trim().length()!=0)
                        Lignes.add(DATA_IN);
                }
                if(Lignes.size()>0){
                    station1.set_NomStation(Lignes.get(1));
                    station1.ajouteLigne(new Ligne(Integer.parseInt(Lignes.get(0).trim())));

                    station2.set_NomStation(Lignes.get(2));
                    station2.ajouteLigne(new Ligne(Integer.parseInt(Lignes.get(0).trim())));


                    station1.ajouteVoisine(station2);
                    station2.ajouteVoisine(station1);

                    this.ajoute_Station(station1);
                    this.ajoute_Station(station2);
                }
                Lignes.clear();
            }
            bf.close();
            System.out.println("Le réseau est bien construit avec "+this._Stations.size()+" stations");
        }catch (IOException  e){
            System.out.println("******Fichier n'existe pas******");
        }
    }


    public Station exist_Station(String st_){
         for(Station station:this._Stations){
                if(station.get_NomStation().toString().trim().toLowerCase().equalsIgnoreCase(st_))
                    return station;
            }
        return null;
    }


    public String[] stationsVoisinesDe(String nomDeStation_){
        ArrayList<String> result=new ArrayList<>();
        Station st=exist_Station(nomDeStation_);
        if(st!=null){
            for (Station station : st.get_Voisines()){
                result.add(station.get_NomStation());
            }
        }
        return (String[]) result.toArray(new String[0]);
    }

    public String cheminDeVers(Station st1_, Station st2_){
        System.out.println("l'Opération est bien commencée");
         ArrayList<Station> result=cheminDeVersRec(new ArrayList<Station>(){{ add(st1_);}},st1_,st2_);
         return  Arrays.toString(checkResulPath(result).toArray());
    }

    private ArrayList<Station> checkResulPath(ArrayList<Station> result) {
        if(result==null){
            System.out.println("******Opération est fini sans de trouver un chemin avec moins de 10 stations******");
            result=new ArrayList<Station>();
        }
        return result;
    }

    public ArrayList<Station> cheminDeVersRec(ArrayList<Station> path_,Station start_,Station final_) {
            if(start_.get_Voisines().contains(final_)){
                return new ArrayList<Station>(){{{{ add(final_);}};}};
            }else {
                    if(path_.size()>10){
                        return null;
                    }

                        ArrayList<ArrayList<Station>> lisPossibilities=new ArrayList<>();
                        for (Station heursitic:getStationByName(start_.get_NomStation()).get_Voisines()) {
                            ArrayList<Station> newWay=new ArrayList<>();
                            if(!path_.contains(heursitic)){
                                addStationToPath(newWay,path_);
                                newWay.add(heursitic);
                                ArrayList<Station> result_heursitc=cheminDeVersRec(newWay,heursitic,final_);
                                if(result_heursitc!=null && result_heursitc.get(result_heursitc.size()-1)==final_){
                                    addStationToPath(newWay,result_heursitc);
                                    lisPossibilities.add(newWay);
                                }
                            }
                        }
                        if(lisPossibilities.size()>0){
                            ArrayList<Station> bestresult=new ArrayList<>();
                            bestresult=lisPossibilities.get(0);//La premiere posibilité
                            for (int i = 1; i <lisPossibilities.size() ; i++) {
                                if(lisPossibilities.get(i).size()<bestresult.size()){
                                    bestresult=lisPossibilities.get(i);
                                }
                            }
                            return bestresult;

                        }else {
                            return null;
                        }

                    }
    }
    public Station getStationByName(String n_){
        for (Station st:get_Stations()
             ) {
             if(st.transform(st.get_NomStation()).trim().toLowerCase().equalsIgnoreCase(st.transform(n_.trim().toLowerCase()))){
                return st;
            }
        }
        System.out.println("******Aucune Station avec le nom => "+n_+"******");
        return null;
    }

    public void addStationToPath(ArrayList<Station> stationBase_,ArrayList<Station> newPath_){

        for (Station inWay:newPath_
             ) {
            if(!stationBase_.contains(inWay)){
                stationBase_.add(inWay);
            }
        }
    }

    @Override
    public String toString() {
        return  Arrays.toString(this._Stations.toArray());
    }
}
