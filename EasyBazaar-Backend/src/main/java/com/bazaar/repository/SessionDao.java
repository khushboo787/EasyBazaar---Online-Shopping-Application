package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.CurrentSession;

public interface SessionDao extends JpaRepository<CurrentSession, Integer>{

	public CurrentSession findByUuid(String uuid);
}
