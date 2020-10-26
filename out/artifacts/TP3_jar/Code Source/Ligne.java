public class Ligne{
    private Integer _NUMERO_LIGNE;

    public Ligne(Integer _NUMERO_LIGNE) {
        this._NUMERO_LIGNE = _NUMERO_LIGNE;
    }

    public Integer get_NUMERO_LIGNE() {
        return _NUMERO_LIGNE;
    }

    public void set_NUMERO_LIGNE(Integer _NUMERO_LIGNE) {
        this._NUMERO_LIGNE = _NUMERO_LIGNE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if( ! (obj instanceof Ligne) ) return false;

        Ligne other = (Ligne) obj;

        return this._NUMERO_LIGNE !=null ? this._NUMERO_LIGNE.equals(other._NUMERO_LIGNE) : this._NUMERO_LIGNE == other._NUMERO_LIGNE;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this._NUMERO_LIGNE == null) ? 0 : this._NUMERO_LIGNE.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Ligne{" +
                "_NUMERO_LIGNE=" + _NUMERO_LIGNE +
                '}';
    }
}
