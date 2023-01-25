package it.uniroma3.siw.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public  class Artista {
	
	/**
	 *  Unique identifier for this Artist
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length = 100)
	private String nome;
	
	@Column(nullable=false, length = 100)
	private String cognome;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDiNascita;
	
	@Column(nullable=true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDiMorte;
	
	@Column(nullable=false)
	private String luogoNascita;
	
	@Column(nullable=true)
	private String luogoMorte;
	
	@Column(nullable=false)
	private String nazionalita;
	
	@Column(nullable=false)
	private String descrizione;
	
	@OneToMany(mappedBy="artista",cascade= {CascadeType.ALL})
	private List<Opera> opere;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	public Artista() {
		this.opere = new ArrayList<>();
	}

	public Artista(String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiMorte,
			String luogoNascita, String luogoMorte, String nazionalita, String descrizione, String photos) {

		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.dataDiMorte = dataDiMorte;
		this.luogoNascita = luogoNascita;
		this.luogoMorte = luogoMorte;
		this.nazionalita = nazionalita;
		this.descrizione = descrizione;
		this.photos = photos;
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

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public LocalDate getDataDiMorte() {
		return dataDiMorte;
	}

	public void setDataDiMorte(LocalDate dataDiMorte) {
		this.dataDiMorte = dataDiMorte;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getLuogoMorte() {
		return luogoMorte;
	}

	public void setLuogoMorte(String luogoMorte) {
		this.luogoMorte = luogoMorte;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(cognome, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(nome, other.nome);
	}
	

	@Transient
	public String getPhotosImagePath() {
		if (this.getPhotos() == null || this.getId() == null) return null;
		
		return "/"+"artista-photos" + "/"+ id + "/" + photos;
	}
	
}
