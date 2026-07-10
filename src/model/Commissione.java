package model;

import java.util.ArrayList;
import java.util.List;

public class Commissione {

    private String idCommissione;
    private String idPresidente;
    private List<String> membri; // lista di id_docente

    public Commissione(String idCommissione, String idPresidente) {
        this.idCommissione = idCommissione;
        this.idPresidente = idPresidente;
        this.membri = new ArrayList<>();
    }

    public String getIdCommissione() {
        return idCommissione;
    }

    public String getIdPresidente() {
        return idPresidente;
    }

    public void aggiungiMembro(String idDocente) {
        membri.add(idDocente);
    }

    public void aggiungiMembro(Docente idDocente) {
    }
}