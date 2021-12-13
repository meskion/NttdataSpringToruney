package com.nttdata.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.spring.persistence.Match;
import com.nttdata.spring.persistence.Player;
import com.nttdata.spring.persistence.PlayerRepository;

@Service
public class PlayerServiceImpl extends AbstractServiceImpl<Player> implements PlayerServiceI {

	@Autowired
	PlayerRepository repo;
	
	

	@Override
	public List<Match> findWonMatches(Player p) {
		
		return repo.findWonMatches(p);
	}
	@Override
	public Long totalMatches(Player p) {
		return repo.totalMatches(p);
	}

	

}
