package it.proprietarioautomobiliJPA.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proprietario")

public class Proprietario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codicefiscale")
	private String codiceFiscale;
	@Column(name = "datadinascita")
	private Date dataDiNascita;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proprietario")
	private Set<Automobile> automobili = new HashSet<>();
	
	public Proprietario() {
	}

	public Proprietario(Long id, String nome, String cognome, String codiceFiscale, Date dataDiNascita,
			Set<Automobile> automobili) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.automobili = automobili;
	}

	public Proprietario(String nome, String cognome, String codiceFiscale, Date dataDiNascita,
			Set<Automobile> automobili) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.automobili = automobili;
	}

	public Proprietario(Long id, String nome, String cognome, String codiceFiscale, Date dataDiNascita) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
	}

	public Proprietario(String nome, String cognome, String codiceFiscale, Date dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Set<Automobile> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(Set<Automobile> automobili) {
		this.automobili = automobili;
	}
	
	public String toString() {
		String dateCreatedString = dataDiNascita != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataDiNascita) : " N.D.";

		return this.getNome() + " " + this.getCognome() + "\n" + this.getCodiceFiscale() + "\n" + dateCreatedString 
				+ "\n\nAutomobili possedute:\n\n" + this.getAutomobili().toString();
	}		
}