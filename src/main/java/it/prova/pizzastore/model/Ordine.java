package it.prova.pizzastore.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name="codice")
	private String codice;
	@Column(name="data")
	private Date data;
	@Column(name="closed")
	private boolean closed;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private Set<Pizza> pizze = new HashSet<Pizza>();
	
	public Ordine() {}

	public Ordine(Long id, String codice, Date data, boolean closed, Set<Pizza> pizze) {
		super();
		this.id = id;
		this.codice = codice;
		this.data = data;
		this.closed = closed;
		this.pizze = pizze;
	}

	public Ordine(String codice, Date data, boolean closed) {
		super();
		this.codice = codice;
		this.data = data;
		this.closed = closed;
	}
	
	public Ordine(String codice, Date data) {
		super();
		this.codice = codice;
		this.data = data;
	}
	
	public Ordine(String codice) {
		super();
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
	}
	
}
