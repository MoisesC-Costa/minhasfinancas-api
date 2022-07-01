package com.github.moises.minhasfinancasbackend.controller;

import com.github.moises.minhasfinancasbackend.advice.exception.ErroAutenticacao;
import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioException;
import com.github.moises.minhasfinancasbackend.dto.request.UsuarioDTO;
import com.github.moises.minhasfinancasbackend.model.entities.Usuario;
import com.github.moises.minhasfinancasbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class CustomerController {
    @Autowired
    CustomerService service;

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
        try {
            var result = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (ErroAutenticacao ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        var customer = Usuario.builder()
                .email(dto.getEmail())
                .name(dto.getNome())
                .password(dto.getSenha())
                .build();
        try {
            var result = service.salvarCliente(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
