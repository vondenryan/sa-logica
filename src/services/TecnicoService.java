package services;

import java.util.ArrayList;

import exceptions.CodigoInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.ManutencaoAbertaException;
import exceptions.MatriculaInvalidaException;
import exceptions.ObjetoIncompletoException;
import models.Tecnico;
import repository.ManutencaoRepository;
import repository.TecnicoRepository;

public class TecnicoService {
    private final ManutencaoRepository manRep;
    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository, ManutencaoRepository manRep) {
        this.repository = repository;
        this.manRep = manRep;
    }

    public void create(Tecnico tecnico) throws ObjetoIncompletoException, MatriculaInvalidaException, CodigoInvalidoException {
        validarTecnico(tecnico);
        validarUnicidade(tecnico);
        repository.create(tecnico);
    }

    public void update(Tecnico tecnico) throws ObjetoIncompletoException, CodigoInvalidoException {
        if(repository.buscarPorCodigo(tecnico.getCodigo()) == null) { throw new CodigoInvalidoException("Erro: código inválido!"); }
        validarTecnico(tecnico);
        repository.update(tecnico);
    }

    public void delete(String codigo) throws ListaVaziaException, CodigoInvalidoException, ManutencaoAbertaException {
        if(repository.listarTodos().isEmpty()) { throw new ListaVaziaException("Erro: lista vazia!"); }
        if(!manRep.validarExclusaoTecnico(repository.buscarPorCodigo(codigo))) { throw new ManutencaoAbertaException("Erro: técnico relacionado à manutenção aberta!"); }
        boolean result = repository.delete(codigo);
        if(!result) { throw new CodigoInvalidoException("Erro: código inválido!"); }
    }

    public Tecnico buscarPorCodigo(String codigo) throws CodigoInvalidoException {
        Tecnico result = repository.buscarPorCodigo(codigo);
        if(result == null) { throw new CodigoInvalidoException("Erro: código inválido!"); }
        return result;
    }

    public ArrayList<Tecnico> listarTodos() throws ListaVaziaException {
        ArrayList<Tecnico> result = repository.listarTodos();
        if(result.isEmpty()) { throw new ListaVaziaException("Erro: lista vazia!"); }
        return result;
    }

    private void validarTecnico(Tecnico t) throws ObjetoIncompletoException {
        if(t.getCodigo().isEmpty() || t.getCodigo() == null) { throw new ObjetoIncompletoException("Erro: código vazio!"); }
        if(t.getNome().isEmpty() || t.getNome() == null) { throw new ObjetoIncompletoException("Erro: nome vazio!"); }
        if(t.getMatricula().isEmpty() || t.getMatricula() == null) { throw new ObjetoIncompletoException("Erro: matrícula vazia!"); }
        if(t.getSetor().isEmpty() || t.getSetor() == null) { throw new ObjetoIncompletoException("Erro: setor vazio!"); }
        if(t.getTelefone().isEmpty() || t.getTelefone() == null) { throw new ObjetoIncompletoException("Erro: telefone vazio!"); }
    }

    private void validarUnicidade(Tecnico t) throws CodigoInvalidoException, MatriculaInvalidaException {
        for(Tecnico tecnico : repository.listarTodos()) {
            if(t.getCodigo().equals(tecnico.getCodigo())) { throw new CodigoInvalidoException("Erro: código duplicado!"); }
            if(t.getMatricula().equals(tecnico.getMatricula())) { throw new MatriculaInvalidaException("Erro: matrícula duplicada!"); }
        }
    }
}
