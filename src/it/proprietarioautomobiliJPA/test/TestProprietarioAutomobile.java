package it.proprietarioautomobiliJPA.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.LazyInitializationException;

import it.proprietarioautomobiliJPA.dao.EntityManagerUtil;
import it.proprietarioautomobiliJPA.model.Automobile;
import it.proprietarioautomobiliJPA.model.Proprietario;
import it.proprietarioautomobiliJPA.service.MyServiceFactory;
import it.proprietarioautomobiliJPA.service.automobile.AutomobileService;
import it.proprietarioautomobiliJPA.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");
			System.out.println("");

			testInserisciProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");
			System.out.println("");

			testInserisciAutomobile(proprietarioService, automobileService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");
			System.out.println("");
			
			testAggiornaProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");
			System.out.println("");

			testAggiornaAutomobile(proprietarioService, automobileService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");
			System.out.println("");
			
			testRimozioneProprietario(proprietarioService);
			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");
			System.out.println("");

			testRimozioneAutomobile(proprietarioService, automobileService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");
			System.out.println("");

			testLazyInitExc(proprietarioService, automobileService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
		System.out.println("");
		// creo nuovo proprietario
		Date dataPerInserimento = new SimpleDateFormat("dd-MM-yyyy").parse("21-04-1971");
		Proprietario nuovoProprietario = new Proprietario("Antonio", "Carosello", "CRSNTN71D21B602S", dataPerInserimento);
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id

		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
		System.out.println("");
	}
	
	private static void testInserisciAutomobile(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");
		System.out.println("");

		// creo nuovo automobile ma prima mi serve un proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovoAutomobile = new Automobile("Fiat", "Multipla", "AX034DE", 2010);
		// lo lego al primo proprietario che trovo
		nuovoAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo automobile
		automobileService.inserisciNuovo(nuovoAutomobile);

		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id

		if (nuovoAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		// il test fallisce anche se non è riuscito a legare i due oggetti
		if (nuovoAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il proprietario ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
		System.out.println("");
	}
	
	private static void testAggiornaProprietario(ProprietarioService proprietarioService)throws Exception{
		System.out.println(".......testAggiornaProprietario inizio.............");
		System.out.println("");
		// creo nuovo proprietario
		Date dataPerInserimento = new SimpleDateFormat("dd-MM-yyyy").parse("21-04-1971");
		Proprietario nuovoProprietario = new Proprietario("Antonio", "Carosello", "CRSNTN71D21B602S", dataPerInserimento);
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testAggiornaProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id

		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testAggiornaProprietario fallito ");

		// recupero col findbyexample e mi aspetto di trovarla
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();

		// mi metto da parte l'id su cui lavorare per il test
		Long idProprietarioDaAggiornare = listaProprietariPresenti.get(listaProprietariPresenti.size()-1).getId();

		// modifico un campo
		String nuovoCognome = "Corsello";
		Proprietario toBeUpdated = proprietarioService.caricaSingoloProprietario(idProprietarioDaAggiornare);
		toBeUpdated.setCognome(nuovoCognome);
		proprietarioService.aggiorna(toBeUpdated);
		if (!toBeUpdated.getCognome().equals(nuovoCognome))
			throw new RuntimeException("testAggiornaPropriet)ario FAILED ");

		System.out.println(".......testAggiornaProprietario PASSED.............");
		System.out.println("");
	}

	private static void testAggiornaAutomobile(ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception{
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovoAutomobile = new Automobile("Fiat", "Unto", "FR239CS", 2005);
		// lo lego al primo proprietario che trovo
		nuovoAutomobile.setProprietario(listaProprietariPresenti.get(listaProprietariPresenti.size()-1));

		// salvo il nuovo automobile
		automobileService.inserisciNuovo(nuovoAutomobile);

		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id

		if (nuovoAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		// il test fallisce anche se non è riuscito a legare i due oggetti
		if (nuovoAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il proprietario ");
		
		List<Automobile> listaAutomobiliPresenti = automobileService.listAllAutomobili();
		Long idAutomobileDaAggiornare = listaAutomobiliPresenti.get(listaAutomobiliPresenti.size()-1).getId();
		
		String nuovoModello = "Punto";
		Automobile toBeUpdated = automobileService.caricaSingolaAutomobile(idAutomobileDaAggiornare);
		toBeUpdated.setModello(nuovoModello);
		automobileService.aggiorna(toBeUpdated);
		if (!toBeUpdated.getModello().equals(nuovoModello))
			throw new RuntimeException("testAggiornaPropriet)ario FAILED ");

	}
	
	private static void testRimozioneProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testRimozioneProprietario inizio.............");
		System.out.println("");
		
		Date dataPerInserimento = new SimpleDateFormat("dd-MM-yyyy").parse("20-06-1999");
		Proprietario nuovoProprietario = new Proprietario("Calogero", "Corsello", " CRSCGR99H20B602J", dataPerInserimento);
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id

		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		// inserisco un automobile che rimuoverò
		// creo nuovo automobile ma prima mi serve un proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();

		proprietarioService.rimuovi(listaProprietariPresenti.get(listaProprietariPresenti.size()-1));
		// proviamo a vedere se è stato rimosso
		if (proprietarioService.caricaSingoloProprietario(listaProprietariPresenti.get(listaProprietariPresenti.size()-1).getId()) != null)
			throw new RuntimeException("testRimozioneProprietario fallito: record non cancellato ");
		System.out.println(".......testRimozioneProprietario fine: PASSED.............");
		System.out.println("");
	}
	
	private static void testRimozioneAutomobile(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testRimozioneAutomobile inizio.............");
		System.out.println("");

		// inserisco un automobile che rimuoverò
		// creo nuovo automobile ma prima mi serve un proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testRimozioneAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Renault", "Scenic", "SE104WL", 2014);
		// lo lego al primo proprietario che trovo
		nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo automobile
		automobileService.inserisciNuovo(nuovaAutomobile);

		Long idAutomobileInserita = nuovaAutomobile.getId();
		automobileService.rimuovi(automobileService.caricaSingolaAutomobile(idAutomobileInserita));
		// proviamo a vedere se è stato rimosso
		if (automobileService.caricaSingolaAutomobile(idAutomobileInserita) != null)
			throw new RuntimeException("testRimozioneAutomobile fallito: record non cancellato ");
		System.out.println(".......testRimozioneAutomobile fine: PASSED.............");
		System.out.println("");
	}
	
	private static void testLazyInitExc(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testLazyInitExc inizio.............");
		System.out.println("");

		// prima mi serve un proprietario
		List<Proprietario> listaMunicipiPresenti = proprietarioService.listAllProprietari();
		if (listaMunicipiPresenti.isEmpty())
			throw new RuntimeException("testLazyInitExc fallito: non ci sono proprietari a cui collegarci ");

		Proprietario proprietarioSuCuiFareIlTest = listaMunicipiPresenti.get(0);
		// se interrogo la relazione devo ottenere un'eccezione visto che sono LAZY
		try {
			proprietarioSuCuiFareIlTest.getAutomobili().size();
			// se la riga sovrastante non da eccezione il test fallisce
			throw new RuntimeException("testLazyInitExc fallito: eccezione non lanciata ");
		} catch (LazyInitializationException e) {
			// 'spengo' l'eccezione per il buon fine del test
		}
		// una LazyInitializationException in quanto il contesto di persistenza è chiuso
		// se usiamo un caricamento EAGER risolviamo...dipende da cosa ci serve!!!
		// proprietarioService.caricaSingoloProprietarioConAbitanti(...);
		System.out.println(".......testLazyInitExc fine: PASSED.............");
		System.out.println("");
	}

}
