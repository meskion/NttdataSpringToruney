package com.nttdata.spring.services;

import java.util.List;

import com.nttdata.spring.persistence.Match;

public interface MatchServiceI {

	void save(Match m);

	void delete(Match m);

	Match findById(Long id);

	List<Match> findAll();

	void saveAll(Match... matchs);

}
