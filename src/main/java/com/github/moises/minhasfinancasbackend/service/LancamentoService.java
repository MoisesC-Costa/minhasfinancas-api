package com.github.moises.minhasfinancasbackend.service;

import com.github.moises.minhasfinancasbackend.model.entities.Lancamento;
import com.github.moises.minhasfinancasbackend.model.enums.StatusLancamento;

import java.util.List;


public interface LancamentoService {
    public Lancamento salvar(Lancamento lancamento);

    public Lancamento atualizar(Lancamento lancamento);

    public void deletar(Lancamento lancamento);

    public List<Lancamento> buscar(Lancamento lancamento);

    public void atualizarStatus(Lancamento lancamento, StatusLancamento status);

    public void validar(Lancamento lancamento);
}
