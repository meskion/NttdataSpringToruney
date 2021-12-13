package com.nttdata.spring.services;

import java.util.List;

import com.nttdata.spring.persistence.Tourney;

public interface TourneyServiceI {

	void save(Tourney t);

	void delete(Tourney t);

	Tourney findById(Long id);

	List<Tourney> findAll();

}
