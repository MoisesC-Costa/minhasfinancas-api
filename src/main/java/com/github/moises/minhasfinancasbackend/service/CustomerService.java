package com.github.moises.minhasfinancasbackend.service;

import com.github.moises.minhasfinancasbackend.advice.exception.ErroAutenticacao;
import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioEmailException;
import com.github.moises.minhasfinancasbackend.model.entities.Customer;

import javax.transaction.Transactional;

public interface CustomerService {
    public Customer autenticar(String email, String senha);

    public Customer salvarCliente(Customer usuario);

    public void validarEmail(String email);
}
