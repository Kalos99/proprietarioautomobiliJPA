package it.proprietarioautomobiliJPA.service;

import it.proprietarioautomobiliJPA.dao.MyDaoFactory;
import it.proprietarioautomobiliJPA.service.automobile.AutomobileService;
import it.proprietarioautomobiliJPA.service.automobile.AutomobileServiceImpl;
import it.proprietarioautomobiliJPA.service.proprietario.ProprietarioService;
import it.proprietarioautomobiliJPA.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

}
