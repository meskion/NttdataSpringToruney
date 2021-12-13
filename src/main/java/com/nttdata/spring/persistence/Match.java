package com.nttdata.spring.persistence;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * Clase de una partida entre dos jugadores 
 * @author Manu
 *
 */
@Entity
@Table(name = "NTTDATAMATCH")
public class Match implements Serializable, AbstractEntity<Match> {

	/*
	 * Atributos
	 */
	private static final long serialVersionUID = 4907200939473578466L;
	private Long id;
	private Tourney tourney;
	private Player playerOne, playerTwo;
	private Boolean playerOneWon;

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Match() {}

	public Match(Tourney tourney, Player playerOne, Player playerTwo, Boolean winner) {
		super();
		this.tourney = tourney;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		playerOneWon = winner;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TOURNEY_ID")
	public Tourney getTourney() {
		return tourney;
	}

	public void setTourney(Tourney tourney) {
		this.tourney = tourney;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "PLAYER1_ID",referencedColumnName = "id")
	public Player getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "PLAYER2_ID",referencedColumnName = "id")
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
	
	@Column(name = "PLAYER1_WON")
	public Boolean getPlayerOneWon() {
		return playerOneWon;
	}

	public void setPlayerOneWon(Boolean playerOneWon) {
		this.playerOneWon = playerOneWon;
	}
	
	private Player winner() {
		if (playerOneWon)
			return playerOne;
		return playerTwo;
	}
	
	private Player loser() {
		if (playerOneWon)
			return playerTwo;
		return playerOne;
	}

	@Override
	public String toString() {
		Player w = winner();
		Player l = loser();
		return w.getName() + " 1 - 0 " + l.getName();
	}
	
	


}
