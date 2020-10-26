import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.text.Normalizer;

public class Station implements Iterable<Ligne> {
      private HashSet<Ligne> _Lignes=new HashSet<>();
      private HashSet<Station> _Voisines=new HashSet<>();
      private String _NomStation;

    public Station() {
    }


    public HashSet<Station> get_Voisines() {
        return _Voisines;
    }

    public void set_Voisines(HashSet<Station> _Voisines) {
        this._Voisines = _Voisines;
    }

    public Station(String nom) {
        this._NomStation=nom;
    }

    public HashSet<Ligne> get_Lignes() {
        return _Lignes;
    }

    public void set_Lignes(HashSet<Ligne> _Lignes) {
        this._Lignes.addAll(_Lignes);
    }

    public String get_NomStation() {
        return _NomStation;
    }

    public void set_NomStation(String _NomStation) {
        this._NomStation = _NomStation;
    }

    public void ajouteLigne(Ligne ligne_) {
        if(!this._Lignes.contains(ligne_))
            this._Lignes.add(ligne_);
    }

    public void ajouteVoisine(Station station) {
        if(!this._Voisines.contains(station))
            this._Voisines.add(station);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if( ! (obj instanceof Station) ) return false;

        Station other = (Station) obj;

       return transform(this._NomStation.trim().toLowerCase())   !=null ?  transform(this._NomStation).trim().toLowerCase().equals(transform(other._NomStation).trim().toLowerCase().toString()) : transform(this._NomStation).trim().toLowerCase() ==  transform(other._NomStation).trim().toLowerCase() ;
     }


    public Boolean exist_Ligne(Ligne l){
        return this.get_Lignes().contains(l);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((transform( this._NomStation.trim().toLowerCase()) == null) ? 0 : transform(this._NomStation.trim().toLowerCase()).hashCode());
        return result;
    }


    public String transform(String s) {
        if(s == null || s.trim().length() == 0)
            return "";
        //Double Verification
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "").replaceAll("\\s","").replace("", "-").replace("-","").toLowerCase().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s","").replace("", "-").replace("-","").toLowerCase();
    }

    public Station getElementExcept(ArrayList<Station> stations,ArrayList<Station> notIn){
       // System.out.println("I am '"+get_NomStation()+"' my neighbours are =>"+Arrays.toString(get_Voisines().toArray()));
         for(Station search:get_Voisines()){
             if(!stations.contains(search) && !notIn.contains(search) ){
                 System.out.println("I found "+search);
                  return  search;
             }
        }
        return null;
    }

    @Override
    public String toString() {
        //+" "+Arrays.toString(this._Lignes.toArray()) \n
        return "{"+this.get_NomStation()+"}";
    }

    @Override
    public Iterator<Ligne> iterator() {
        return this.get_Lignes().iterator();
    }

}
