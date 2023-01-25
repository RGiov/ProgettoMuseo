package it.uniroma3.siw.model;



import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;



@Entity
public  class Collezione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	@Column(length=299)
	private String descrizione;
	
	@OneToMany(mappedBy="collezione",cascade = CascadeType.ALL)
	private List<Opera> opere;
	
	@ManyToOne
	private Curatore curatore;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	
	public Collezione(Long id, String nome, String descrizione, List<Opera> opere, Curatore curatore, String photos) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.opere = opere;
		this.curatore = curatore;
		this.photos = photos;
	}
	public Collezione() {
		super();
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
	public Curatore getCuratore() {
		return curatore;
	}
	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(curatore, descrizione, id, nome, opere, photos);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collezione other = (Collezione) obj;
		return Objects.equals(curatore, other.curatore) && Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(opere, other.opere) && Objects.equals(photos, other.photos);
	}
	
	

	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"collezione-photos" + "/"+ id + "/" + photos;
    }





}
