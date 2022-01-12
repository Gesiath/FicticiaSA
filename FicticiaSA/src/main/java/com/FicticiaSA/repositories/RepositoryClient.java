package com.FicticiaSA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FicticiaSA.entities.Client;

@Repository
public interface RepositoryClient extends JpaRepository<Client, String> {
	
	@Query("SELECT COUNT(c) > 0 FROM Client c WHERE c.email = :email")
	public boolean existByEmail(@Param("email") String email);

	@Query("SELECT c FROM Client c WHERE c.email = :email")
	public Client findByEmail(@Param("email") String email);
	
}
