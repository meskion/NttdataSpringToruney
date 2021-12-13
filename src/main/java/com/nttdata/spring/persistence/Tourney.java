package com.nttdata.spring.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * @author Usuario
 *
 */
@Entity
@Table(name="TOURNEY")
public class Tourney implements Serializable, AbstractEntity<Tourney> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466753073174807318L;
	private Long id;
	private String name;
	private Date beginDate;
	private List<Player> participants;
	private List<Match> matches;
	
	public Tourney() {}
	
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
	@Column(name="BEGINDATE", nullable = false) 
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@ManyToMany
	@JoinTable(
			  name = "TOURNEY_PLAYER", 
			  joinColumns = @JoinColumn(name = "tourney_id"), 
			  inverseJoinColumns = @JoinColumn(name = "player_id"))
	public List<Player> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Player> participants) {
		this.participants = participants;
//		participants.forEach(p -> p.addTourney(this));
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tourney")
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
//		matches.forEach(m -> m.setTourney(this));
	}

}
