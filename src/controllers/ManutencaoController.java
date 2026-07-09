package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

import exceptions.CodigoInvalidoException;
import exceptions.ObjetoIncompletoException;
import models.Equipamento;
import models.Manutencao;
import services.EquipamentoService;
import services.ManutencaoService;
import services.TecnicoService;

public class ManutencaoController {
    private final Scanner input = new Scanner(System.in);
    private final ManutencaoService service;
    private final EquipamentoService equipamentoService;
    private final TecnicoService tecnicoService;

    public ManutencaoController(ManutencaoService service, EquipamentoService equipamentoService, TecnicoService tecnicoService) {
        this.service = service;
        this.tecnicoService = tecnicoService;
        this.equipamentoService = equipamentoService;
    }

    public void startMenu() {
        boolean sair = false;

        while(!sair) {
            System.out.println("\n--- Menu Manutenção ---");
            System.out.println("1. Registrar manutenção");
            System.out.println("2. Consultar manutenção");
            System.out.println("3. Alterar situação");
            System.out.println("4. Finalizar manutenção");
            System.out.println("5. Listar todas as manutenções");
            System.out.println("0. Voltar");
            System.out.print("-> ");

            try {
                int opcao = input.nextInt();
                input.nextLine();

                switch(opcao) {
                    case 1:
                        //registrar
                        Manutencao man = registrarManutencao();
                        service.create(man);
                        alterarStatus(man);
                        System.out.println("\nManutenção registrada!");
                        break;
                    case 2:
                        //consultar
                        System.out.print("\nDigite o código da manutenção: ");
                        String codConsulta = input.nextLine();

                        System.out.println("\n" + service.buscarPorCodigo(codConsulta));
                        break;
                    case 3:
                        //alterar situacao
                        System.out.print("\nDigite o código da manutenção: ");
                        String codAlter = input.nextLine();

                        service.update(alterarSituacao(service.buscarPorCodigo(codAlter)));
                        System.out.println("\nSituação alterada!");
                        break;
                    case 4:
                        //finalizar manutencao
                        System.out.print("\nDigite o código da manutenção: ");
                        String codFinalizacao = input.nextLine();

                        service.update(finalizarManutencao(service.buscarPorCodigo(codFinalizacao)));
                        System.out.println("\nManutenção finalizada!");
                        break;
                    case 5:
                        //listar todas as manutencoes
                        for(Manutencao m : service.listarTodos()) {
                            System.out.println("\n" + m);
                        }
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("\nErro: opção inválida!");
                }
            } catch(InputMismatchException e) {
                System.out.println("\nErro: opção deve ser um número inteiro!");
                input.nextLine();
            } catch(Exception e) {
                System.out.println("\n" + e.getMessage());
            }
        }
    }

    private Manutencao registrarManutencao() throws InvalidAttributeValueException, CodigoInvalidoException {
        //pegando valores normais
        System.out.print("\nDigite o código: ");
        String codigo = input.nextLine();
        System.out.print("Digite o cod. do equipamento: ");
        String codEquipamento = input.nextLine();
        System.out.print("Digite o cod. do tecnico: ");
        String codTecnico = input.nextLine();
        System.out.print("Descreva o problema: ");
        String descricao = input.nextLine();

        //Pegando tipo de manutenção
        System.out.print("\nTipo Manutenção:\n1. Preventiva | 2. Corretiva\n-> ");
        int escolha = input.nextInt();
        input.nextLine();

        String tipoManutencao = escolha == 1 ? "Preventiva" : (escolha == 2 ? "Corretiva" : null);
        if(tipoManutencao == null) { throw new InvalidAttributeValueException("Erro: tipo manutenção vazio!"); }

        //pegando data de abertura
        System.out.print("Digite a data de abertura (yyyy-MM-DD HH:mm | Enter para data atual): ");
        String dataInstalacao = input.nextLine();
        
        LocalDateTime dateTime;

        if(dataInstalacao.isEmpty()) {
            dateTime = LocalDateTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(dataInstalacao, formatter);
        }

        //retornando objeto
        return new Manutencao(codigo, equipamentoService.buscarPorCodigo(codEquipamento), tecnicoService.buscarPorCodigo(codTecnico), dateTime, tipoManutencao, descricao, "Aberta");
    }

    private void alterarStatus(Manutencao m) throws CodigoInvalidoException, ObjetoIncompletoException {
        Equipamento e = equipamentoService.buscarPorCodigo(m.getEquipamentoRelacionado().getCodigo());
        e.setStatus("Em manutenção");
        equipamentoService.update(e);
    }

    private Manutencao alterarSituacao(Manutencao m) {
        if(m.getSituacao().equals("Aberta")) {
            m.setSituacao("Em andamento");
        } else if(m.getSituacao().equals("Em andamento")) {
            m.setSituacao("Aberta");
        }

        return m;
    }

    private Manutencao finalizarManutencao(Manutencao m) throws CodigoInvalidoException, ObjetoIncompletoException {
        Equipamento e = equipamentoService.buscarPorCodigo(m.getEquipamentoRelacionado().getCodigo());
        e.setStatus("Operando");
        equipamentoService.update(e);

        m.setSituacao("Finalizada");
        m.setDataEncerramento(LocalDateTime.now());
        
        return m;
    }
}
