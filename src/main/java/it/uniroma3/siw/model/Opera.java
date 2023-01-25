package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;



@Entity
public  class Opera {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length = 100)
	private String titolo;
	
	@Column(nullable=false, length=4)
	private Long anno;
	
	@Column(nullable = false,length=299)
	private String descrizione;
	
	@ManyToOne
	private Collezione collezione;
	
	@ManyToOne
	private Artista artista;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@Column(updatable = false, nullable = false)
	private LocalDateTime creationTimestamp;

	@Column(nullable = false)
	private LocalDateTime lastUpdateTimestamp;
	
	public Opera() {
	}
	
	public Opera(String titolo, Long anno, String descrizione, String photos, LocalDateTime lastUpdateTimestamp, LocalDateTime creationTimestamp) {
		this.titolo = titolo;
		this.anno = anno;
		this.descrizione = descrizione;
		this.photos = photos;
		this.creationTimestamp = LocalDateTime.now();
		this.lastUpdateTimestamp = LocalDateTime.now();
	}
	
	/*
	 * qualsiasi metodo marcato con questa annotazione in una @
		Entity
		viene eseguito sempre quando una nuova istanza viene salvata in DB
	 */
	@PrePersist
	protected void onPersists() {
		this.creationTimestamp = LocalDateTime.now();
		this.lastUpdateTimestamp = LocalDateTime.now();
	}

	/* Qualsiasi metodo marcato con questa annotazione in una @
	Entity
	viene eseguito sempre quando delle modifiche su una istanza
	preesistente vengono salvate in DB
	 */
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdateTimestamp = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Long getAnno() {
		return anno;
	}

	public void setAnno(Long anno) {
		this.anno = anno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	

	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public LocalDateTime getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anno, creationTimestamp, descrizione, lastUpdateTimestamp, titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opera other = (Opera) obj;
		return Objects.equals(anno, other.anno) && Objects.equals(creationTimestamp, other.creationTimestamp)
				&& Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(lastUpdateTimestamp, other.lastUpdateTimestamp)
				&& Objects.equals(titolo, other.titolo);
	}

	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"opera-photos" + "/"+ id + "/" + photos;
    }


	
}
