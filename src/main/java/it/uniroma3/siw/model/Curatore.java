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
public class Curatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	//dd-MM-yyyy
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDiNascita;
	
	@Column(nullable=false)
	private String luogoNascita;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=true)
	private Long telefono;
	
	@Column(nullable=false)
	private Long matricola;
	@OneToMany(mappedBy="curatore",cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	private List<Collezione> collezioni;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	public Curatore() {
		this.collezioni = new ArrayList<>();
	}
	
	public Curatore(String nome, String cognome, LocalDate dataDiNascita, String luogoNascita, String email,
			Long telefono, Long matricola, String photos) {
		
		this();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.luogoNascita = luogoNascita;
		this.email = email;
		this.telefono = telefono;
		this.matricola = matricola;
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


	public String getLuogoNascita() {
		return luogoNascita;
	}


	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getTelefono() {
		return telefono;
	}


	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}


	public Long getMatricola() {
		return matricola;
	}


	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}


	public List<Collezione> getCollezioni() {
		return collezioni;
	}


	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cognome, collezioni, dataDiNascita, email, id, luogoNascita, matricola, nome, photos,
				telefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curatore other = (Curatore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(collezioni, other.collezioni)
				&& Objects.equals(dataDiNascita, other.dataDiNascita) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(luogoNascita, other.luogoNascita)
				&& Objects.equals(matricola, other.matricola) && Objects.equals(nome, other.nome)
				&& Objects.equals(photos, other.photos) && Objects.equals(telefono, other.telefono);
	}
	
	
	
	
	

	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"curatore-photos" + "/"+ id + "/" + photos;
    }

	
	
	
}
