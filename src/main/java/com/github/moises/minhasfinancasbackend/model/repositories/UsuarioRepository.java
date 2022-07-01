package com.github.moises.minhasfinancasbackend.model.repositories;

import com.github.moises.minhasfinancasbackend.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public boolean existsByEmail(String email);

	public Optional<Usuario> findByEmail(String email);

}
