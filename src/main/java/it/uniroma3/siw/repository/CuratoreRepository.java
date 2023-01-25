package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Curatore;


public interface CuratoreRepository extends CrudRepository<Curatore, Long> {
	
	public List<Curatore> findByNome(String nome);

	public List<Curatore> findByNomeAndCognome(String nome, String cognome);

	public List<Curatore> findByNomeOrCognome(String nome, String cognome);

	public Optional<Curatore> findByMatricola(Long matricola);
}
