package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javax.persistence.Query;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;



public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Utente> list() throws Exception {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Optional<Utente> findOne(Long id) throws Exception {
		Utente result = entityManager.find(Utente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		utenteInstance = entityManager.merge(utenteInstance);
	}

	@Override
	public void insert(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(utenteInstance);
	}

	@Override
	public void delete(Utente utenteInstance) throws Exception {
		if (utenteInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(utenteInstance));
	}

	// questo metodo ci torna utile per capire se possiamo rimuovere un ruolo non
	// essendo collegato ad un utente
	public List<Utente> findAllByRuolo(Ruolo ruoloInput) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u join u.ruoli r where r = :ruolo",
				Utente.class);
		query.setParameter("ruolo", ruoloInput);
		return query.getResultList();
	}

	@Override
	public Optional<Utente> findByUsernameAndPassword(String username, String password) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u  " + "where u.username = :username and u.password=:password ", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<Utente> login(String username, String password) throws Exception {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u join fetch u.ruoli r "
						+ "where u.username = :username and u.password=:password and u.stato=:statoUtente",
				Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		query.setParameter("statoUtente", StatoUtente.ATTIVO);
		return query.getResultStream().findFirst();
	}
	
	public List<Utente> findAllFattorini() throws Exception{
		Query q = entityManager.createNativeQuery("select * from utente u right join utente_ruolo ur ON u.id=ur.utente_id inner join ruolo r on ur.ruolo_id=r.id WHERE r.id=3");
		return q.getResultList();
	}

}
