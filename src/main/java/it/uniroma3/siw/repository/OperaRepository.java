package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Opera;


public interface OperaRepository extends CrudRepository<Opera, Long> {
	
	public List<Opera> findByTitolo(String titolo);

	public List<Opera> findByTitoloAndAnno(String titolo, Long anno);

	public List<Opera> findByTitoloOrAnno(String titolo, Long anno);
	
	
}
