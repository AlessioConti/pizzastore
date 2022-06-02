package it.prova.pizzastore.web.listener;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.RuoloService;
import it.prova.pizzastore.service.UtenteService;

public class LocalEntityManagerFactoryListener implements ServletContextListener {
	
	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("pizzastore_unit");
			// questa chiamata viene fatta qui per semplicità ma in genere è meglio trovare
			// altri modi per fare init
			initAdminUserAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}
	
	private void initAdminUserAndRuoli() throws Exception{
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ADMIN_ROLE"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "PIZZAIOLO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", "PIZZAIOLO_ROLE"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", "FATTORINO_ROLE"));
		}

		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("pizza", "pizza") == null) {
			Utente pizza = new Utente("pizza", "pizza", "Alessio", "Bianchi", new Date());
			pizza.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(pizza);
			utenteServiceInstance.aggiungiRuolo(pizza,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "PIZZAIOLO_ROLE"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("fattorino", "fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Giulio", "Verdi", new Date());
			fattorino.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(fattorino);
			utenteServiceInstance.aggiungiRuolo(fattorino,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE"));
		}
	}
	
}
