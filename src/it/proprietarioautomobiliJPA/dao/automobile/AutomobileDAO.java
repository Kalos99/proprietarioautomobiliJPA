package it.proprietarioautomobiliJPA.dao.automobile;

import java.util.List;

import it.proprietarioautomobiliJPA.dao.IBaseDAO;
import it.proprietarioautomobiliJPA.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile>{

	public List<Automobile> findAllByCodFisProprietarioIniziaCon(String iniziale) throws Exception;
	
}
