package com.github.moises.minhasfinancasbackend.service.impl;

import com.github.moises.minhasfinancasbackend.advice.exception.ErroAutenticacao;
import com.github.moises.minhasfinancasbackend.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioEmailException;
import com.github.moises.minhasfinancasbackend.model.entities.Customer;
import com.github.moises.minhasfinancasbackend.model.repositories.CustomerRepository;

import javax.transaction.Transactional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	public Customer autenticar(String email, String senha) {
		var usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new RegraNegocioEmailException("Não existe usuario cadastrado com esse email.");
		}

		if (!usuario.get().getPassword().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}

		return usuario.get();
	}

	@Transactional
	public Customer salvarCliente(Customer usuario) {
		return repository.save(usuario);
	}
	
	public void validarEmail(String email) {
		if (repository.existsByEmail(email)) {
			throw new RegraNegocioEmailException("Já existe um usuário com este email.");
		}
	}
}
