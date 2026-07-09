package model;

public class Tesi {

    private String idTesi;
    private String matricolaStudente;
    private String idDocenteRelatore;
    private String titolo;
    private String stato; // IN_ATTESA, APPROVATA, RIFIUTATA

    public Tesi(String idTesi, String matricolaStudente, String idDocenteRelatore, String titolo, String stato) {
        this.idTesi = idTesi;
        this.matricolaStudente = matricolaStudente;
        this.idDocenteRelatore = idDocenteRelatore;
        this.titolo = titolo;
        this.stato = stato;
    }

    public String getIdTesi() {
        return idTesi;
    }

    public void setIdTesi(String idTesi) {
        this.idTesi = idTesi;
    }

    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    public String getIdDocenteRelatore() {
        return idDocenteRelatore;
    }

    public void setIdDocenteRelatore(String idDocenteRelatore) {
        this.idDocenteRelatore = idDocenteRelatore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}