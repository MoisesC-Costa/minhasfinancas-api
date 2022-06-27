package com.github.moises.minhasfinancasbackend.model.repositories;

import com.github.moises.minhasfinancasbackend.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public boolean existsByEmail(String email);

	public Optional<Customer> findByEmail(String email);

}
