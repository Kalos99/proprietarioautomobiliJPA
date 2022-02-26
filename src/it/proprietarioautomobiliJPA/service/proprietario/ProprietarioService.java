package it.proprietarioautomobiliJPA.service.proprietario;

import java.util.List;

import it.proprietarioautomobiliJPA.dao.proprietario.ProprietarioDAO;
import it.proprietarioautomobiliJPA.model.Proprietario;

public interface ProprietarioService {
	
	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;
	
	public Proprietario caricaSingoloProprietarioConAutomobili(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	public int cercaTuttiIProprietariConAutomobiliImmatricolateAPartireDa(int annoConfronto) throws Exception;

	//per injection
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
}
