package dao;

import model.SedutaLaurea;
import model.Studente;
import java.util.List;

public interface SedutaLaureaDAO {

    // Aggiunge una nuova seduta di laurea
    boolean aggiungiSeduta(SedutaLaurea seduta);

    // Recupera una seduta tramite id_seduta
    SedutaLaurea getSedutaById(String idSeduta);

    // Restituisce tutte le sedute presenti nel sistema
    List<SedutaLaurea> getAllSedute();

    // Aggiorna completamente una seduta
    boolean aggiornaSeduta(SedutaLaurea seduta);

    // Elimina una seduta dal sistema
    boolean eliminaSeduta(String idSeduta);

    // Prenota uno studente a una seduta
    boolean prenotaStudente(String idSeduta, Studente studente);

    // Rimuove uno studente prenotato da una seduta
    boolean rimuoviStudente(String idSeduta, Studente studente);

    // Restituisce tutti gli studenti prenotati a una seduta
    List<Studente> getStudentiPrenotati(String idSeduta);
}