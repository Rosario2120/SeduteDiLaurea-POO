package dao;

import model.Tesi;
import java.util.List;

public interface TesiDAO {

    // Aggiunge una nuova tesi nel sistema
    boolean aggiungiTesi(Tesi tesi);

    // Recupera una tesi tramite id_tesi
    Tesi getTesiById(String idTesi);

    // Restituisce tutte le tesi presenti nel sistema
    List<Tesi> getAllTesi();

    // Restituisce la tesi caricata da uno specifico studente
    Tesi getTesiByStudente(String matricolaStudente);

    // Aggiorna completamente una tesi
    boolean aggiornaTesi(Tesi tesi);

    // Aggiorna lo stato della tesi (IN_ATTESA, APPROVATA, RIFIUTATA)
    boolean aggiornaStatoTesi(String idTesi, String nuovoStato);

    // Elimina una tesi dal sistema
    boolean eliminaTesi(String idTesi);
}