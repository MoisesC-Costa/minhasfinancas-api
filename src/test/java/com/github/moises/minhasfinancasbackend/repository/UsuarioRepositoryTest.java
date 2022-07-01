package com.github.moises.minhasfinancasbackend.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.moises.minhasfinancasbackend.model.entities.Usuario;
import com.github.moises.minhasfinancasbackend.model.repositories.UsuarioRepository;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	public UsuarioRepository repository;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// Cenario
		var usuario = Usuario.builder().name("usuario").email("usuario@email.com").build();
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
