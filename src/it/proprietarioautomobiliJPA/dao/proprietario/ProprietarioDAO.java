package it.proprietarioautomobiliJPA.dao.proprietario;

import it.proprietarioautomobiliJPA.dao.IBaseDAO;
import it.proprietarioautomobiliJPA.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario>{
	
	public Proprietario getEagerAutomobili(Long id) throws Exception;

	public int countAllWithAutomobileImmatricolataAPartireDa(int annoConfronto) throws Exception;
}
