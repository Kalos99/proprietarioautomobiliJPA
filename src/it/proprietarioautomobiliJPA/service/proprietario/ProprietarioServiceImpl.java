package it.proprietarioautomobiliJPA.service.proprietario;

import java.util.List;

import it.proprietarioautomobiliJPA.dao.proprietario.ProprietarioDAO;
import it.proprietarioautomobiliJPA.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService{
	
	private ProprietarioDAO proprietarioDAO;
	
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		this.proprietarioDAO = proprietarioDAO;
	}

	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proprietario caricaSingoloProprietarioConAbitanti(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Proprietario proprietarioInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Proprietario> cercaTuttiIProprietariConAutomobiliImmatricolateAPartireDa(int annoConfronto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
