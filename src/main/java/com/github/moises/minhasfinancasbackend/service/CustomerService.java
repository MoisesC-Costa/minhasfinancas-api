package com.github.moises.minhasfinancasbackend.service;

import com.github.moises.minhasfinancasbackend.model.entities.Usuario;

public interface CustomerService {
    public Usuario autenticar(String email, String senha);

    public Usuario salvarCliente(Usuario usuario);

    public void validarEmail(String email);
}
