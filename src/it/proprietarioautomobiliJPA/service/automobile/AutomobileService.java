package it.proprietarioautomobiliJPA.service.automobile;

import java.util.List;

import it.proprietarioautomobiliJPA.dao.automobile.AutomobileDAO;
import it.proprietarioautomobiliJPA.model.Automobile;

public interface AutomobileService {

	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile caricaSingolaAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;
	
	public List<Automobile> cercaTutteLeAutomobiliConCodiceFiscaleProprietarioCheIniziaCon(String iniziale) throws Exception;
	
	//per injection
	public void setAutomobileDAO(AutomobileDAO automobileDAO);
}
