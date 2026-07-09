package model;

import java.util.ArrayList;
import java.util.List;

public class SedutaLaurea {

    private String idSeduta;
    private String dataSeduta;
    private String oraSeduta;
    private String luogo;
    private String idCommissione;

    private List<String> studentiPrenotati;

    public SedutaLaurea(String idSeduta, String dataSeduta, String oraSeduta, String luogo, String idCommissione) {
        this.idSeduta = idSeduta;
        this.dataSeduta = dataSeduta;
        this.oraSeduta = oraSeduta;
        this.luogo = luogo;
        this.idCommissione = idCommissione;
        this.studentiPrenotati = new ArrayList<>();
    }

    public String getIdSeduta() {
        return idSeduta;
    }

    public void setIdSeduta(String idSeduta) {
        this.idSeduta = idSeduta;
    }

    public String getDataSeduta() {
        return dataSeduta;
    }

    public void setDataSeduta(String dataSeduta) {
        this.dataSeduta = dataSeduta;
    }

    public String getOraSeduta() {
        return oraSeduta;
    }

    public void setOraSeduta(String oraSeduta) {
        this.oraSeduta = oraSeduta;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getIdCommissione() {
        return idCommissione;
    }

    public void setIdCommissione(String idCommissione) {
        this.idCommissione = idCommissione;
    }

    public List<String> getStudentiPrenotati() {
        return studentiPrenotati;
    }

    public void aggiungiStudente(String matricola) {
        studentiPrenotati.add(matricola);
    }

    public void rimuoviStudente(String matricola) {
        studentiPrenotati.remove(matricola);
    }
}