package com.nttdata.spring.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomizedPlayerRepositoryImpl implements CustomizedPlayerRepository {

	@Autowired
	EntityManager em;

	/**
	 * Dado un jugador, devuelve una lista con las partidas que ha ganado en todos
	 * los torneos que ha ido
	 */
	@Override
	public List<Match> findWonMatches(Player p) {

		Session s = em.unwrap(Session.class);

		final CriteriaBuilder cb = s.getCriteriaBuilder();
		final CriteriaQuery<Match> mquery = cb.createQuery(Match.class);
		final Root<Match> mroot = mquery.from(Match.class);

		/*
		 * iba a hacer el join pero entonces me he dado cuenta de que criteria te deja
		 * recoger el jugador en si pues esta haciendo la query en java, entonces ni he
		 * tenido que declarar el join para hacer la consulta, aunque el equivalent en
		 * SQL si lo requeriria.
		 */

//		final Join<Match,Player> mJoinP1 = mroot.join("playerOne");
//		final Join<Match,Player> mJoinP2 = mroot.join("playerTwo");

		Predicate isP1 = cb.equal(mroot.<Player>get("playerOne"), p);
		Predicate isP2 = cb.equal(mroot.<Player>get("playerTwo"), p);
		Predicate p1Won = cb.equal(mroot.<Boolean>get("playerOneWon"), true);
		Predicate p2Won = p1Won.not();
		Predicate where = cb.or(cb.and(p1Won, isP1), cb.and(p2Won, isP2));
		mquery.select(mroot).where(where);

		return s.createQuery(mquery).getResultList();
	}
	@Override
	public Long totalMatches(Player p) {

		Session s = em.unwrap(Session.class);

		final CriteriaBuilder cb = s.getCriteriaBuilder();
		final CriteriaQuery<Long> mquery = cb.createQuery(Long.class);
		final Root<Match> mroot = mquery.from(Match.class);
		Predicate isP1 = cb.equal(mroot.<Player>get("playerOne"), p);
		Predicate isP2 = cb.equal(mroot.<Player>get("playerTwo"), p);
		mquery.select(cb.count(mroot)).where(cb.or(isP1,isP2));
		return s.createQuery(mquery).getSingleResult();
	}

}
