package services;

import exceptions.CodigoInvalidoException;
import exceptions.EquipamentoInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.ObjetoIncompletoException;
import exceptions.TecnicoInvalidoException;
import models.Manutencao;
import repository.EquipamentoRepository;
import repository.ManutencaoRepository;
import repository.TecnicoRepository;

public class ManutencaoService {
    private final TecnicoRepository tecnicoRepository = new TecnicoRepository();
    private final EquipamentoRepository equipamentoRepository = new EquipamentoRepository();
    private final ManutencaoRepository repository;

    public ManutencaoService(ManutencaoRepository repository) {
        this.repository = repository;
    }

    public void create(Manutencao manutencao) throws ObjetoIncompletoException, TecnicoInvalidoException, EquipamentoInvalidoException, CodigoInvalidoException {
        //validar codigo unico
        validarManutencao(manutencao);
        repository.create(manutencao);
    }

    public void update(Manutencao manutencao) throws ObjetoIncompletoException, CodigoInvalidoException, TecnicoInvalidoException, EquipamentoInvalidoException {
        if(repository.buscarPorCodigo(manutencao.getCodigo()) == null) { throw new CodigoInvalidoException("Erro: código inválido!"); }
        validarManutencao(manutencao);
        repository.update(manutencao);
    }

    public void delete(String codigo) throws ListaVaziaException, CodigoInvalidoException {
        if(repository.listarTodos().isEmpty()) { throw new ListaVaziaException("Erro: lista vazia!"); }
        boolean result = repository.delete(codigo);
        if(!result) { throw new CodigoInvalidoException("Erro: código inválido!"); }
    }

    private void validarManutencao(Manutencao m) throws ObjetoIncompletoException, TecnicoInvalidoException, EquipamentoInvalidoException, CodigoInvalidoException {
        //validacao de existencia tecnico e equipamento
        if(tecnicoRepository.buscarPorCodigo(m.getTecnicoResponsavel().getCodigo()) == null) { throw new TecnicoInvalidoException("Erro: técnico inválido!"); }
        if(equipamentoRepository.buscarPorCodigo(m.getEquipamentoRelacionado().getCodigo())  == null) { throw new EquipamentoInvalidoException("Erro:"); }

        //validacao regras de negocio
        if(m.getCodigo().isEmpty() || m.getCodigo() == null) { throw new ObjetoIncompletoException("Erro: código inválido!"); }
        if(m.getDataAbertura() == null) { throw new ObjetoIncompletoException("Erro: data abertura vazia!"); }
        if(m.getTipoManutencao().isEmpty() || m.getTipoManutencao() == null) { throw new ObjetoIncompletoException("Erro: tipo manutenção vazio!"); }
        if(m.getDescricaoProblema().isEmpty() || m.getDescricaoProblema() == null) { throw new ObjetoIncompletoException("Erro: descrição problema vazio!"); }
        if(m.getSituacao().isEmpty() || m.getSituacao() == null) { throw new ObjetoIncompletoException("Erro: situação vazia!"); }

        if(m.getSituacao().equals("Finalizada")) {
            if(m.getDataEncerramento() == null) { throw new ObjetoIncompletoException("Erro: data encerramento vazia!"); }
        }
    }

    public void validarUnicidade(Manutencao m) {

    }
}
