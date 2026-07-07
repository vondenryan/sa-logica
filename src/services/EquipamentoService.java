package services;

import java.util.ArrayList;

import exceptions.CodigoInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.ObjetoIncompletoException;
import models.Equipamento;
import repository.EquipamentoRepository;

public class EquipamentoService {
    private final EquipamentoRepository repository;
    
    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public void create(Equipamento equipamento) {
        try {
            if(equipamento.getCategoria().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: categoria vazia!");
            }
            if(equipamento.getDataInstalacao().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: data instalação vazia!");
            }
            if(equipamento.getFabricante().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: fabricante vazio!");
            }
            if(equipamento.getModelo().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: modelo vazio!");
            }
            if(equipamento.getNome().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: nome vazio!");
            }
            if(equipamento.getSetor().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: setor vazio!");
            }
            if(equipamento.getStatus().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: status vazio!");
            }
            if(equipamento.getCodigo().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: codigo vazio!");
            }

            repository.create(equipamento);
        } catch(ObjetoIncompletoException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void update(Equipamento equipamento) {
        try {
            if(equipamento.getCategoria().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: categoria vazia!");
            }
            if(equipamento.getDataInstalacao().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: data instalação vazia!");
            }
            if(equipamento.getFabricante().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: fabricante vazio!");
            }
            if(equipamento.getModelo().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: modelo vazio!");
            }
            if(equipamento.getNome().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: nome vazio!");
            }
            if(equipamento.getSetor().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: setor vazio!");
            }
            if(equipamento.getStatus().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: status vazio!");
            }
            if(equipamento.getCodigo().isEmpty()) {
                throw new ObjetoIncompletoException("Erro: codigo vazio!");
            }

            repository.update(equipamento);
        } catch(ObjetoIncompletoException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void delete(String codigo) {
        try {
            if(repository.listarTodos().isEmpty()) {
                throw new ListaVaziaException("Erro: lista vazia!");
            }
            if(repository.buscarPorCodigo(codigo) == null) {
                throw new CodigoInvalidoException("Erro: código inválido!");
            }
            //verificar manutenção em aberto

            repository.delete(codigo);
        } catch(Exception e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public Equipamento buscarPorCodigo(String codigo) {
        try {
            Equipamento result = repository.buscarPorCodigo(codigo);

            if(result == null) {
                throw new CodigoInvalidoException("Erro: código inválido!");
            }

            return result;
        } catch(CodigoInvalidoException e) {
            System.out.println("\n" + e.getMessage());
            return null;
        }
    }

    public ArrayList<Equipamento> listarTodos() {
        try {
            ArrayList<Equipamento> result = repository.listarTodos();
    
            if(result.isEmpty()) {
                throw new ListaVaziaException("Erro: lista vazia!");
            }

            return result;
        } catch(ListaVaziaException e) {
            System.out.println("\n" + e.getMessage());
            return null;
        }
    }
}
