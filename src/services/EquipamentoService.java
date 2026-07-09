package services;

import java.util.ArrayList;

import exceptions.CodigoInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.ManutencaoAbertaException;
import exceptions.ObjetoIncompletoException;
import models.Equipamento;
import repository.EquipamentoRepository;
import repository.ManutencaoRepository;

public class EquipamentoService {
    private final ManutencaoRepository manRep;
    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository, ManutencaoRepository manRep) {
        this.repository = repository;
        this.manRep = manRep;
    }

    public void create(Equipamento equipamento) throws ObjetoIncompletoException, CodigoInvalidoException {
        validarEquipamento(equipamento);
        validarUnicidade(equipamento);
        repository.create(equipamento);
    }

    public void update(Equipamento equipamento) throws ObjetoIncompletoException, CodigoInvalidoException {
        if(repository.buscarPorCodigo(equipamento.getCodigo()) == null) { throw new CodigoInvalidoException("Erro: código inválido!"); }
        validarEquipamento(equipamento);
        repository.update(equipamento);
    }

    public void delete(String codigo) throws ListaVaziaException, CodigoInvalidoException, ManutencaoAbertaException {
        if(repository.listarTodos().isEmpty()) { throw new ListaVaziaException("Erro: lista vazia!"); }
        if(!manRep.validarExclusaoEquipamento(repository.buscarPorCodigo(codigo))) { throw new ManutencaoAbertaException("Erro: equipamento relacionado à manutenção aberta!"); }
        boolean result = repository.delete(codigo);
        if(!result) { throw new CodigoInvalidoException("Erro: código inválido!"); }
    }

    public Equipamento buscarPorCodigo(String codigo) throws CodigoInvalidoException {
        Equipamento result = repository.buscarPorCodigo(codigo);
        if(result == null) { throw new CodigoInvalidoException("Erro: código inválido!"); }
        return result;
    }

    public ArrayList<Equipamento> listarTodos() throws ListaVaziaException {
        ArrayList<Equipamento> result = repository.listarTodos();
        if(result.isEmpty()) { throw new ListaVaziaException("Erro: lista vazia!"); }
        return result;
    }

    private void validarEquipamento(Equipamento e) throws ObjetoIncompletoException {
        if(e.getCodigo().isEmpty() || e.getCodigo() == null) { throw new ObjetoIncompletoException("Erro: codigo vazio!"); }
        if(e.getCategoria().isEmpty() || e.getCategoria() == null) { throw new ObjetoIncompletoException("Erro: categoria vazia!"); }
        if(e.getDataInstalacao() == null) { throw new ObjetoIncompletoException("Erro: data instalação vazia!"); }
        if(e.getFabricante().isEmpty() || e.getFabricante() == null) { throw new ObjetoIncompletoException("Erro: fabricante vazio!"); }
        if(e.getModelo().isEmpty() || e.getModelo() == null) { throw new ObjetoIncompletoException("Erro: modelo vazio!"); }
        if(e.getNome().isEmpty() || e.getNome() == null) { throw new ObjetoIncompletoException("Erro: nome vazio!"); }
        if(e.getSetor().isEmpty() || e.getSetor() == null) { throw new ObjetoIncompletoException("Erro: setor vazio!"); }
        if(e.getStatus().isEmpty() || e.getStatus() == null) { throw new ObjetoIncompletoException("Erro: status vazio!"); }
    }

    private void validarUnicidade(Equipamento e) throws CodigoInvalidoException {
        for(Equipamento equipamento : repository.listarTodos()) {
            if(e.getCodigo().equals(equipamento.getCodigo())) { throw new CodigoInvalidoException("Erro: código duplicado!"); }
        }
    }
}
