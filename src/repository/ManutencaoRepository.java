package repository;

import java.util.ArrayList;

import models.Manutencao;

public class ManutencaoRepository {
    private final ArrayList<Manutencao> manutencoes = new ArrayList<>();

    public boolean create(Manutencao manutencao) {
        return manutencoes.add(manutencao);
    }

    public boolean update(Manutencao manutencao) {
        for(Manutencao m : manutencoes) {
            if(m.getCodigo().equals(manutencao.getCodigo())) {
                m.setEquipamentoRelacionado(manutencao.getEquipamentoRelacionado());
                m.setTecnicoResponsavel(manutencao.getTecnicoResponsavel());
                m.setDataAbertura(manutencao.getDataAbertura());
                m.setDataEncerramento(manutencao.getDataEncerramento());
                m.setTipoManutencao(manutencao.getTipoManutencao());
                m.setDescricaoProblema(manutencao.getDescricaoProblema());
                m.setSituacao(manutencao.getSituacao());
                return true;
            }
        }
        return false;
    }

    public boolean delete(String codigo) {
        return manutencoes.removeIf(m -> m.getCodigo().equals(codigo));
    }

    public Manutencao buscarPorCodigo(String codigo) {
        for(Manutencao m : manutencoes) {
            if(m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Manutencao> listarTodos() {
        return new ArrayList<>(manutencoes);
    }
}
