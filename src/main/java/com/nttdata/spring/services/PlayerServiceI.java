package com.nttdata.spring.services;

import java.util.List;

import com.nttdata.spring.persistence.Match;
import com.nttdata.spring.persistence.Player;
/**
 * Servicio para administrar jugadores
 * @author Manu Fdez
 *
 */
public interface PlayerServiceI {

	void save(Player p);

	void delete(Player p);

	Player findById(Long id);

	List<Player> findAll();
	
	void saveAll(Player...players);
	
	List<Match> findWonMatches(Player p);

	Long totalMatches(Player p);

}
