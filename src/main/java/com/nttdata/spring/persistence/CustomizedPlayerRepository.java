package com.nttdata.spring.persistence;

import java.util.List;

public interface CustomizedPlayerRepository {

	List<Match> findWonMatches(Player p);

	Long totalMatches(Player p);

}
