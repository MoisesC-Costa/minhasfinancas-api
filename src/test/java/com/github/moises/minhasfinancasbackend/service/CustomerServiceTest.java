package com.github.moises.minhasfinancasbackend.service;


import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioEmailException;
import com.github.moises.minhasfinancasbackend.model.entities.Customer;
import com.github.moises.minhasfinancasbackend.model.repositories.CustomerRepository;
import com.github.moises.minhasfinancasbackend.service.impl.CustomerServiceIMPL;
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
public class CustomerServiceTest {

    CustomerService service;

    @MockBean
    CustomerRepository repository;

    @BeforeEach
    public void setup() {
        service = new CustomerServiceIMPL(repository);
    }

    @Test
    public void deveAutenticarUmUsuarioComSucesso() {
        // cenário
        String email = "email@email.com";
        String senha = "password";

        var usuario = Customer.builder().id(1L).name("nome").email(email).password(senha).build();
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

        var usuario = Customer.builder().id(1L).name("nome").email("email@email.com").password(senha).build();
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
        Assertions.assertThrows(RegraNegocioEmailException.class, () -> service.validarEmail("email@email.com"));
    }
}
