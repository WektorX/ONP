package onp;

import java.io.Serializable;

public class Rownanie implements Serializable {
    private static final long serialVersionUID = -7887612267521882048L;

    String rownanie;
    String rownanieONP;
    String wynik;

    public Rownanie(){
        this.rownanie = "";
        this.rownanieONP = "";
        this.wynik = "";
    }

    public Rownanie(String rownanie, String rownanieONP, String wynik){
        this.rownanie = rownanie;
        this.rownanieONP = rownanieONP;
        this.wynik = wynik;
    }

    public String getRownanie() {
        return rownanie;
    }

    public String getRownanieONP() {
        return rownanieONP;
    }

    public String getWynik() {
        return wynik;
    }

    public void setRownanie(String rownanie) {
        this.rownanie = rownanie;
    }

    public void setRownanieONP(String rownanieONP) {
        this.rownanieONP = rownanieONP;
    }

    public void setWynik(String wynik) {
        this.wynik = wynik;
    }

    @Override
    public String toString() {
        return "{" +
                "rownanie: '" + rownanie +
                ", rownanie w ONP: '" + rownanieONP +
                ", wynik: '" + wynik +
                '}';
    }
}
