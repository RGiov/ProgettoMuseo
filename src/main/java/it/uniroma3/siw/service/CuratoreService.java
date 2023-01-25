package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.repository.CuratoreRepository;

@Service
public class CuratoreService {
	
	@Autowired
	private CuratoreRepository curatoreRepository;
	@Autowired
	private CredentialsService credentialsService;
	
	
	@Transactional
	public Curatore inserisci(Curatore curatore) {
		return curatoreRepository.save(curatore);
	}
	
	@Transactional
	public List<Curatore> curatoriPerNomeAndCognome(String nome, String cognome) {
		return curatoreRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public List<Curatore> tutti() {
		return (List<Curatore>) curatoreRepository.findAll();
	}

	@Transactional
	public Curatore curatorePerId(Long id) {
		Optional<Curatore> optional = curatoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public Curatore curatorePerMatricola(Long matricola) {
		Optional<Curatore> optional = curatoreRepository.findByMatricola(matricola);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Curatore curatore) {
		List<Curatore> curatori = this.curatoreRepository.findByNomeAndCognome(curatore.getNome(), curatore.getCognome());
		if (curatori.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public boolean deletedCuratore(Long id) {
		try {
			this.curatoreRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}