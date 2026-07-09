package model;

public class RichiestaTirocinio {

    private String idRichiesta;
    private String matricolaStudente;
    private String idTirocinio;
    private String stato; // IN_ATTESA, APPROVATA, RIFIUTATA

    public RichiestaTirocinio(String idRichiesta, String matricolaStudente, String idTirocinio, String stato) {
        this.idRichiesta = idRichiesta;
        this.matricolaStudente = matricolaStudente;
        this.idTirocinio = idTirocinio;
        this.stato = stato;
    }

    public String getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(String idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    public String getIdTirocinio() {
        return idTirocinio;
    }

    public void setIdTirocinio(String idTirocinio) {
        this.idTirocinio = idTirocinio;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}