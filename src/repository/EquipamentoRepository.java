package repository;

import java.util.ArrayList;

import models.Equipamento;

public class EquipamentoRepository {
    private final ArrayList<Equipamento> equipamentos = new ArrayList<>();

    public boolean create(Equipamento equipamento) {
        return equipamentos.add(equipamento);
    }

    public boolean update(Equipamento equipamento) {
        for(Equipamento e : equipamentos) {
            if(e.getCodigo().equals(equipamento.getCodigo())) {
                e.setCategoria(equipamento.getCategoria());
                e.setDataInstalacao(equipamento.getDataInstalacao());
                e.setFabricante(equipamento.getFabricante());
                e.setModelo(equipamento.getModelo());
                e.setNome(equipamento.getNome());
                e.setSetor(equipamento.getSetor());
                e.setStatus(equipamento.getStatus());
                return true;
            }
        }
        return false;
    }

    public boolean delete(String codigo) {
        for(Equipamento e : equipamentos) {
            if(e.getCodigo().equals(codigo)) {
                return equipamentos.remove(e);
            }
        }
        return false;
    }

    public Equipamento buscarPorCodigo(String codigo) {
        for(Equipamento e : equipamentos) {
            if(e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Equipamento> listarTodos() {
        return new ArrayList<>(equipamentos);
    }
}
