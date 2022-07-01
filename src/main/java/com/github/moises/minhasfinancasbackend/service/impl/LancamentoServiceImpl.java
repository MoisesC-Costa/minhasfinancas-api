package com.github.moises.minhasfinancasbackend.service.impl;

import com.github.moises.minhasfinancasbackend.advice.exception.RegraNegocioException;
import com.github.moises.minhasfinancasbackend.model.entities.Lancamento;
import com.github.moises.minhasfinancasbackend.model.entities.Usuario;
import com.github.moises.minhasfinancasbackend.model.enums.StatusLancamento;
import com.github.moises.minhasfinancasbackend.model.enums.TipoLancamento;
import com.github.moises.minhasfinancasbackend.model.repositories.LancamentoRepository;
import com.github.moises.minhasfinancasbackend.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class LancamentoServiceImpl implements LancamentoService {
    @Autowired
    private LancamentoRepository repository;

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lancamento> buscar(Lancamento lancamento) {
        var example = Example.of(
                lancamento,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatus(status);
        atualizar(lancamento);
    }

    @Override
    public void validar(Lancamento lancamento) {
        if (lancamento.getStatus() == null) {
            throw new RegraNegocioException("Informe o Status do Lançamento");
        }
        if (lancamento.getTipo() == null) {
            throw new RegraNegocioException("Informe o Tipo do Lançamento");
        }
        if (lancamento.getAno() == null ||
                lancamento.getAno().toString().length() < 4) {
            throw new RegraNegocioException("Informe um ano valido");
        }
        if (lancamento.getMes() == null ||
                lancamento.getMes() > 13 ||
                lancamento.getMes() < 1 ) {
            throw new RegraNegocioException("Informe um ano valido");
        }
        if (lancamento.getValor() == null ||
            lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
            throw new RegraNegocioException("Informe um Valor valido.");
        }
        if (lancamento.getDescricao() == null ||
                lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Informe uma Descricao valida.");
        }
    }
}
