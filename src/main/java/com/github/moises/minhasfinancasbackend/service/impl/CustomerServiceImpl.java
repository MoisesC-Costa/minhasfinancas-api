package com.github.moises.minhasfinancasbackend.service.impl;

import com.github.moises.minhasfinancasbackend.advice.exception.ErroAutenticacao;
import com.github.moises.minhasfinancasbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioException;
import com.github.moises.minhasfinancasbackend.model.entities.Usuario;
import com.github.moises.minhasfinancasbackend.model.repositories.UsuarioRepository;

import javax.transaction.Transactional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario autenticar(String email, String senha) {
		var usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Não existe usuario cadastrado com esse email.");
		}

		if (!usuario.get().getPassword().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}

		return usuario.get();
	}

	@Transactional
	public Usuario salvarCliente(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}
	
	public void validarEmail(String email) {
		if (repository.existsByEmail(email)) {
			throw new RegraNegocioException("Já existe um usuário com este email.");
		}
	}
}
