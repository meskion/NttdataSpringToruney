package com.nttdata.spring.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Representa un jugador que participa en torneos, jugando partidas contra otros
 * participantes del mismo torneo.
 * 
 * @author Manu Fdez
 *
 */
@Entity
@Table(name = "NTTDATAPLAYER")
public class Player implements Serializable, AbstractEntity<Player> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -308088465905064571L;
	// Atributos
	private Long id;
	private String name;
	private Integer rank;
	private List<Tourney> tourneys;

	public Player() {}
	
	public Player(String name, int rank) {
		this.name = name;
		this.rank = rank;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Column(name = "PLAYERRANK", nullable = false)
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer ranking) {
		this.rank = ranking;
	}

	@ManyToMany(mappedBy = "participants")
	public List<Tourney> getTourneys() {
		return tourneys;
	}

	public void setTourneys(List<Tourney> tourneys) {
		this.tourneys = tourneys;
	}

	public void addTourney(Tourney tourney) {
		if (tourneys == null)
			tourneys = new ArrayList<Tourney>();
		
		tourneys.add(tourney);
		
	}

}
