package com.github.moises.minhasfinancasbackend.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.moises.minhasfinancasbackend.model.entities.Customer;
import com.github.moises.minhasfinancasbackend.model.repositories.CustomerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTest {
	
	@Autowired
	public CustomerRepository repository;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// Cenario
		var usuario = Customer.builder().name("usuario").email("usuario@email.com").build();
		repository.save(usuario);
		
		// Ação
		boolean result = repository.existsByEmail("usuario@email.com");

		// Teste
		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void deveVerificarANaoExistenciaDeUmEmail() {
		// Ação
		repository.deleteAll();

		// Teste
		Assertions.assertThat(repository.existsByEmail("usuario@email.com")).isFalse();
	}
}
