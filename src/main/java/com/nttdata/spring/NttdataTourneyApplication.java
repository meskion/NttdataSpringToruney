package com.nttdata.spring;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdata.spring.persistence.Match;
import com.nttdata.spring.persistence.Player;
import com.nttdata.spring.persistence.Tourney;
import com.nttdata.spring.services.MatchServiceI;
import com.nttdata.spring.services.PlayerServiceI;
import com.nttdata.spring.services.TourneyServiceI;

@SpringBootApplication
public class NttdataTourneyApplication implements CommandLineRunner {

	@Autowired
	PlayerServiceI playerService;
	@Autowired
	TourneyServiceI tourneyService;
	@Autowired
	MatchServiceI matchService;

	public static void main(String[] args) {
		SpringApplication.run(NttdataTourneyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tourney t1 = new Tourney();
		t1.setName("Redfox 1");
		t1.setBeginDate(new Date());

		Player p1 = new Player("Manu", 1);
		Player p2 = new Player("Carlos", 2);
		Player p3 = new Player("Alberto", 3);
		Player p4 = new Player("Guille", 4);

		playerService.saveAll(p1, p2, p3, p4);

		t1.setParticipants(List.of(p1, p2, p3, p4));

		Match t1m1 = new Match(t1, p1, p2, true);
		Match t1m2 = new Match(t1, p3, p4, true);
		Match t1m3 = new Match(t1, p1, p3, true);
		Match t1m4 = new Match(t1, p2, p4, true);
		Match t1m5 = new Match(t1, p1, p2, false);
		matchService.saveAll(t1m1, t1m2, t1m3, t1m4, t1m5);

		t1.setMatches(List.of(t1m1, t1m2, t1m3, t1m4, t1m5));

		tourneyService.save(t1);

		Tourney t2 = new Tourney();
		t2.setName("Redfox 2");
		t2.setBeginDate(new Date());

		Player p5 = new Player("Trif", 5);
		playerService.save(p5);

		t2.setParticipants(List.of(p5, p2, p3, p4));

		Match t2m1 = new Match(t2, p5, p2, true);
		Match t2m2 = new Match(t2, p3, p4, true);
		Match t2m3 = new Match(t2, p5, p3, true);
		Match t2m4 = new Match(t2, p2, p4, true);
		Match t2m5 = new Match(t2, p5, p2, true);
		matchService.saveAll(t1m1, t1m2, t1m3, t1m4, t1m5);
		t2.setMatches(List.of(t2m1, t2m2, t2m3, t2m4, t2m5));

		tourneyService.save(t2);

		tourneyService.findAll().forEach(System.out::println);

		playerService.findWonMatches(p2).forEach(System.out::println);
		System.out.println(p3.getName()+ " total matches: " +playerService.totalMatches(p3));

	}

}
