package com.nttdata.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.spring.persistence.Match;
import com.nttdata.spring.persistence.MatchRepository;

@Service
public class MatchServiceImpl extends AbstractServiceImpl<Match> implements MatchServiceI {
	
	@Autowired
	MatchRepository repo;
	
	
	

}
