package com.github.moises.minhasfinancasbackend.service;


import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioException;
import com.github.moises.minhasfinancasbackend.model.entities.Usuario;
import com.github.moises.minhasfinancasbackend.model.repositories.UsuarioRepository;
import com.github.moises.minhasfinancasbackend.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

// Inicializa o test com o container do spring
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    CustomerService service;

    @MockBean
    UsuarioRepository repository;

    @BeforeEach
    public void setup() {
        service = new CustomerServiceImpl(repository);
    }

    @Test
    public void deveAutenticarUmUsuarioComSucesso() {
        // cenário
        String email = "email@email.com";
        String senha = "password";

        var usuario = Usuario.builder().id(1L).name("nome").email(email).password(senha).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // ação
        var result = service.autenticar(email, senha);

        // verificação
        Assertions.assertNotNull(result);
    }

    @Test
    public void deveLancarExceptionAoAutenticarOUsuario() {
        // cenário
        String senha = "password";

        var usuario = Usuario.builder().id(1L).name("nome").email("email@email.com").password(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

    }

    @Test
    public void deveValidarEmail() {
        // cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
        // ação
        Assertions.assertDoesNotThrow(() -> service.validarEmail("email@email.com"));
    }

    @Test
    public void deveLancarErroAoVlaidarEmail() {
        // cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        // ação
        Assertions.assertThrows(RegraNegocioException.class, () -> service.validarEmail("email@email.com"));
    }
}
