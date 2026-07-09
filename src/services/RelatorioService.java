package services;

import models.Equipamento;
import models.Manutencao;
import repository.EquipamentoRepository;
import repository.ManutencaoRepository;
import repository.TecnicoRepository;

public class RelatorioService {
    public void apresentarRelatorio(ManutencaoRepository manRep, EquipamentoRepository eqRep, TecnicoRepository tecRep) {
        //pegando informacoes para relatorio
        int qtdEquipamentos = eqRep.listarTodos().size();
        int qtdTecnicos = tecRep.listarTodos().size();
        int equipamentosAtivos = 0, equipamentosEmManutencao = 0, equipamentosInativos = 0;

        for(Equipamento e : eqRep.listarTodos()) {
            String status = e.getStatus();
            if(status.equals("Operando")) {
                equipamentosAtivos++;
            } else if(status.equals("Em manutenção")) {
                equipamentosEmManutencao++;
            } else if(status.equals("Inativo")) {
                equipamentosInativos++;
            }
        }

        int manutencoesAbertas = 0, manutencoesFinalizadas = 0;
        for(Manutencao m : manRep.listarTodos()) {
            String status = m.getSituacao();
            if(status.equals("Aberta") || status.equals("Em andamento")) {
                manutencoesAbertas++;
            } else if(status.equals("Finalizada")) {
                manutencoesFinalizadas++;
            }
        }

        StringBuilder buffer = new StringBuilder();
        for(Equipamento e : eqRep.listarTodos()) {
            int qtdManutencoes = 0;
            for(Manutencao m : manRep.listarTodos()) {
                if(e == m.getEquipamentoRelacionado()) {
                    qtdManutencoes++;
                }
            }
            buffer.append("\nEquipamento - " + e.getCodigo() + "\nManutenções: " + qtdManutencoes + "\n");
        }

        System.out.println("\n--- Relatório ---");
        System.out.println("Qtd. total de equipamentos: " + qtdEquipamentos);
        System.out.println("Qtd. total de técnicos: " + qtdTecnicos);
        System.out.println("Equipamentos operando: " + equipamentosAtivos);
        System.out.println("Equipamentos em manutenção: " + equipamentosEmManutencao);
        System.out.println("Equipamentos inativos: " + equipamentosInativos);
        System.out.println("Manutenções abertas: " + manutencoesAbertas);
        System.out.println("Manutenções finalizadas: " + manutencoesFinalizadas);
        System.out.println("Manutenções por equipamento: ");
        System.out.println(buffer.toString());
    }
}
