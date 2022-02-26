package it.proprietarioautomobiliJPA.dao;

import it.proprietarioautomobiliJPA.dao.automobile.AutomobileDAO;
import it.proprietarioautomobiliJPA.dao.automobile.AutomobileDAOImpl;
import it.proprietarioautomobiliJPA.dao.proprietario.ProprietarioDAO;
import it.proprietarioautomobiliJPA.dao.proprietario.ProprietarioDAOImpl;

public class MyDaoFactory {

	// rendiamo questo factory SINGLETON
		private static AutomobileDAO automobileDAOInstance = null;
		private static ProprietarioDAO proprietarioDAOInstance = null;

		public static AutomobileDAO getAutomobileDAOInstance() {
			if (automobileDAOInstance == null)
				automobileDAOInstance = new AutomobileDAOImpl();
			return automobileDAOInstance;
		}

		public static ProprietarioDAO getProprietarioDAOInstance(){
			if(proprietarioDAOInstance == null)
				proprietarioDAOInstance= new ProprietarioDAOImpl();
			return proprietarioDAOInstance;
		}
}
