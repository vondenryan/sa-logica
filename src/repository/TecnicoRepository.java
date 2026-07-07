package repository;

import java.util.ArrayList;

import models.Tecnico;

public class TecnicoRepository {
    private final ArrayList<Tecnico> tecnicos = new ArrayList<>();

    public boolean create(Tecnico tecnico) {
        return tecnicos.add(tecnico);
    }

    public boolean update(Tecnico tecnico) {
        for(Tecnico t : tecnicos) {
            if(t.getCodigo().equals(tecnico.getCodigo())) {
                t.setMatricula(tecnico.getMatricula());
                t.setNome(tecnico.getNome());
                t.setSetor(tecnico.getSetor());
                t.setTelefone(tecnico.getTelefone());
                return true;
            }
        }
        return false;
    }

    public boolean delete(Tecnico tecnico) {
        return tecnicos.remove(tecnico);
    }

    public Tecnico buscarPorCodigo(String codigo) {
        for(Tecnico t : tecnicos) {
            if(t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Tecnico> listarTodos() {
        return new ArrayList<>(tecnicos);
    }
}
