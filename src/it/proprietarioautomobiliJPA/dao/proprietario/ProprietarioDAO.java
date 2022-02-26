package it.proprietarioautomobiliJPA.dao.proprietario;

import it.proprietarioautomobiliJPA.dao.IBaseDAO;
import it.proprietarioautomobiliJPA.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario>{

	public int countAllWithAutomobileImmatricolataAPartireDa(int annoConfronto) throws Exception;
}
