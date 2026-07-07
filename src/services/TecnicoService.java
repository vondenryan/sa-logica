package services;

import java.util.ArrayList;

import exceptions.CodigoInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.ObjetoIncompletoException;
import models.Tecnico;
import repository.TecnicoRepository;

public class TecnicoService {
    private final ManutencaoService manutencaoService;
    private final TecnicoRepository repository;

    public TecnicoService(ManutencaoService manutencaoService, TecnicoRepository repository) {
        this.manutencaoService = manutencaoService;
        this.repository = repository;
    }

    public void create(Tecnico tecnico) throws ObjetoIncompletoException {
        //todo validar matricula e codigo unico
        validarTecnico(tecnico);
        repository.create(tecnico);
    }

    public void update(Tecnico tecnico) throws ObjetoIncompletoException, CodigoInvalidoException {
        if(repository.buscarPorCodigo(tecnico.getCodigo()) == null) { throw new CodigoInvalidoException("Erro: código inválido!"); }
        validarTecnico(tecnico);
        repository.update(tecnico);
    }

    public void delete(String codigo) throws ListaVaziaException, CodigoInvalidoException {
        if(repository.listarTodos().isEmpty()) { throw new ListaVaziaException("Erro: lista vazia!"); }
        //todo veriicar manutenção em aberto
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
}
