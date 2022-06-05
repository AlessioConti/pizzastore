package it.prova.pizzastore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;
	
	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {
		Ordine result = entityManager.find(Ordine.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.merge(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Optional<Ordine> findOneEager(Long id) {
		return entityManager.createQuery("select o from Ordine o left join fetch o.cliente c left join fetch o.utente u where o.id=:idOrdine", Ordine.class)
				.setParameter("idOrdine", id).getResultList().stream().findFirst();
	}
	
	public List<Ordine> findByExample(Ordine example) throws Exception{
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");
		
		if(StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if(example.getData() != null) {
			whereClauses.add(" o.data >= :data");
			paramaterMap.put("data", example.getData());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
	
	public List<Ordine> listOrdiniAperti(Long id) throws Exception{
		return entityManager.createQuery("select o from Ordine o left join o.utente u where o.closed = 0 and u.id = :idUtente", Ordine.class).setParameter("idUtente", id).getResultList();
	}
	
	public Ordine chiudi(Ordine input) throws Exception{
		input.setClosed(true);
		return input;
	}

}
