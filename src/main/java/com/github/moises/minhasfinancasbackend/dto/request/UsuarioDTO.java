package com.github.moises.minhasfinancasbackend.dto.request;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String email;
    private String senha;
    private String nome;
}
