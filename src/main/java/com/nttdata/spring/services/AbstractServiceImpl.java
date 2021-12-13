package com.nttdata.spring.services;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.spring.persistence.AbstractEntity;

public abstract class AbstractServiceImpl<T extends AbstractEntity<T>> {

	@Autowired
	protected JpaRepository<T, Long> repo;

	public void save(T m) {
		if (m != null)
			repo.saveAndFlush(m);

	}
	@SuppressWarnings("unchecked")
	public void saveAll(T... m) {
		repo.saveAllAndFlush(Arrays.asList(m));
	}
	
	public void delete(T p) {
		if (p != null && p.getId() != null)
			repo.delete(p);
	}
	
	public T findById(Long id) {
		T res = null;
		try {
			res = repo.findById(id).get();
		} catch (NoSuchElementException e) {
			//TODO cambiar por logger
			System.err.println("entidad no encontrada");
		}
		return res;
	}
	
	public List<T> findAll(){
		return repo.findAll();
	}

}
