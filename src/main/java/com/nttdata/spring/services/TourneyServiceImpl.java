package com.nttdata.spring.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.spring.persistence.Tourney;
import com.nttdata.spring.persistence.TourneyRepository;
@Service
public class TourneyServiceImpl implements TourneyServiceI {
	
	@Autowired
	private TourneyRepository repo;
	
	public void save(Tourney m) {
		if (m != null)
			repo.saveAndFlush(m);

	}
	
	public void delete(Tourney m) {
		if (m != null)
			repo.delete(m);
	}
	
	public Tourney findById(Long id) {
		Tourney res = null;
		try {
			res = repo.findById(id).get();
		} catch (NoSuchElementException e) {
			//TODO cambiar por logger
			System.err.println("entidad no encontrada");
		}
		return res;
	}
	
	public List<Tourney> findAll(){
		return repo.findAll();
	}
	
	

	
	
	

}
