package it.proprietarioautomobiliJPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "automobile")

public class Automobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modello")
	private String modello;
	@Column(name = "targa")
	private String targa;
	@Column(name = "annoimmatricolazione")
	private Integer annoImmatricolazione;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proprietario_id")
	private Proprietario proprietario;
	
	public Automobile() {
	}

	public Automobile(Long id, String marca, String modello, String targa, Integer annoImmatricolazione, Proprietario proprietario) {
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
		this.proprietario = proprietario;
	}

	public Automobile(Long id, String marca, String modello, String targa, Integer annoImmatricolazione) {
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
	}

	public Automobile(String marca, String modello, String targa, Integer annoImmatricolazione, Proprietario proprietario) {
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
		this.proprietario = proprietario;
	}

	public Automobile(String marca, String modello, String targa, Integer annoImmatricolazione) {
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Integer getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}

	public void setAnnoImmatricolazione(Integer annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	
	@Override
	public String toString() {
		return "Marca: " + this.getMarca() + "\nModello: " + this.getModello() + "\nTarga: " + this.getTarga() 
			 + "\nAnno di immatricolazione: " + this.getAnnoImmatricolazione();
	}
}