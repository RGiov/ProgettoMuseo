package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository; 
	@Autowired
	private CollezioneService collezioneService;
	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private CredentialsService credentialsService;
	
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public List<Opera> operePerTitolo(String titolo) {
		return operaRepository.findByTitolo(titolo);
	}

	@Transactional
	public List<Opera> tutti() {
		return (List<Opera>) operaRepository.findAll();
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public boolean deletedOpera(Long id) {
		try {
			this.operaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> opere = this.operaRepository.findByTitolo(opera.getTitolo());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public List<Opera> opereSenzaCollezione(){
		List<Opera> lista=(List<Opera>) operaRepository.findAll();
		List<Opera> listaSenzaCollezione=new ArrayList<>();
		for(Opera opera:lista) {
			if(opera.getCollezione()==null)
				listaSenzaCollezione.add(opera);
		}
		return listaSenzaCollezione;
	}

	public CollezioneService getCollezioneService() {
		return this.collezioneService;
	}

	public ArtistaService getArtistaService() {
		
		return this.artistaService;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
