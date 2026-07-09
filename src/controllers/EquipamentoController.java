package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.Equipamento;
import services.EquipamentoService;

public class EquipamentoController {
    private final Scanner input = new Scanner(System.in);
    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    public void startMenu() {
        boolean sair = false;

        while(!sair) {
            System.out.println("\n--- Menu Equipamento ---");
            System.out.println("1. Cadastrar equipamento");
            System.out.println("2. Consultar equipamento");
            System.out.println("3. Alterar informações");
            System.out.println("4. Excluir equipamento");
            System.out.println("5. Listar todos os equipamentos"); 
            System.out.println("0. Voltar"); 
            System.out.print("-> ");

            try {
                int opcao = input.nextInt();
                input.nextLine();

                switch(opcao) {
                    case 1:
                        //cadastrar
                        service.create(cadastrarEquipamento());
                        System.out.println("\nEquipamento cadastrado!");
                        break;
                    case 2:
                        //consultar
                        System.out.print("\nDigite o código do equipamento: ");
                        String codConsulta = input.nextLine();

                        System.out.println("\n" + service.buscarPorCodigo(codConsulta));
                        break;
                    case 3:
                        //atualizar
                        System.out.print("\nDigite o código do equipamento: ");
                        String codAtualizacao = input.nextLine();

                        service.update(atualizarEquipamento(service.buscarPorCodigo(codAtualizacao)));
                        System.out.println("\nEquipamento atualizado!");
                        break;
                    case 4:
                        //excluir
                        System.out.print("\nDigite o código de equipamento: ");
                        String codExclusao = input.nextLine();

                        service.delete(codExclusao);
                        System.out.println("\nEquipamento excluido!");
                        break;
                    case 5:
                        //listar todos
                        for(Equipamento e : service.listarTodos()) {
                            System.out.println("\n" + e);
                        }
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("\nErro: opção inválida!");
                }
            } catch(InputMismatchException e) {
                System.out.println("\nErro: opção deve ser um númeo inteiro!");
                input.nextLine();
            } catch(DateTimeParseException e) {
                System.out.println("\nErro: formato de data inválido!");
            } catch(Exception e) {
                System.out.println("\n" + e.getMessage());
            }
        }
    }

    private Equipamento cadastrarEquipamento() throws DateTimeParseException {
        System.out.print("\nDigite o código: ");
        String codigo = input.nextLine();
        System.out.print("Digite o nome: ");
        String nome = input.nextLine();
        System.out.print("Digite a categoria: ");
        String categoria = input.nextLine();
        System.out.print("Digite o fabricante: ");
        String fabricante = input.nextLine();
        System.out.print("Digite o modelo: ");
        String modelo = input.nextLine();
        System.out.print("Digite o setor: ");
        String setor = input.nextLine();
        System.out.print("Digite a data de instalação (yyyy-MM-DD HH:mm): ");
        String dataInstalacao = input.nextLine();
        
        LocalDateTime dateTime;

        if(dataInstalacao.isEmpty()) {
            dateTime = LocalDateTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(dataInstalacao, formatter);
        }

        return new Equipamento(codigo, nome, categoria, fabricante, modelo, setor, dateTime, "Inativo");
    }

    private Equipamento atualizarEquipamento(Equipamento e) {
        System.out.print("\nDigite o setor (Enter para continuar): ");
        String setor = input.nextLine();
        System.out.print("Alterar status (s para alterar): ");
        String status = input.nextLine();

        if(!setor.isEmpty()) {
            e.setSetor(setor);
        }
        if(status.toLowerCase().equals("s")) {
            if(e.getStatus() == "Operando") {
                e.setStatus("Inativo");
            } else if(e.getStatus().equals("Inativo")) {
                e.setStatus("Operando");
            }
        }

        return e;
    }
}
