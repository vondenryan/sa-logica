package controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.Tecnico;
import services.TecnicoService;

public class TecnicoController {
    private final Scanner input = new Scanner(System.in);
    private final TecnicoService service;

    public TecnicoController(TecnicoService service) {
        this.service = service;
    }

    public void startMenu() {
        boolean sair = false;

        while(!sair) {
            System.out.println("\n--- Menu Técnicos ---");
            System.out.println("1. Cadastrar técnico");
            System.out.println("2. Consultar técnico");
            System.out.println("3. Alterar informações");
            System.out.println("4. Excluir técnico");
            System.out.println("5. Listar todos os técnicos"); 
            System.out.println("0. Voltar"); 
            System.out.print("-> ");

            try {
                int opcao = input.nextInt();
                input.nextLine();

                switch (opcao) {
                    case 1:
                        //cadastrar
                        service.create(cadastrarTecnico());
                        System.out.println("\nTécnico cadastrado!");
                        break;
                    case 2:
                        //consultar
                        System.out.print("\nDigite o código do técnico: ");
                        String codConsulta = input.nextLine();

                        System.out.println("\n" + service.buscarPorCodigo(codConsulta));
                        break;
                    case 3:
                        //atualizar
                        System.out.print("\nDigite o código do técnico: ");
                        String codAtualizacao = input.nextLine();

                        service.update(atualizarTecnico(service.buscarPorCodigo(codAtualizacao)));
                        System.out.println("\nTécnico atualizado!");
                        break;
                    case 4:
                        //excluir
                        System.out.print("\nDigite o código do técnico: ");
                        String codExclusao = input.nextLine();

                        service.delete(codExclusao);
                        System.out.println("\nTécnico excluido!");
                        break;
                    case 5:
                        //listar todos
                        ArrayList<Tecnico> tecnicos = service.listarTodos();
                        for(Tecnico t : tecnicos) {
                            System.out.println("\n" + t);
                        }
                        break;
                    case 0:
                        //saida
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

    private Tecnico cadastrarTecnico() {
        System.out.print("\nDigite o código: ");
        String cod = input.nextLine();
        System.out.print("Digite o nome: ");
        String nome = input.nextLine();
        System.out.print("Digite a matrícula: ");
        String matricula = input.nextLine();
        System.out.print("Digite o setor: ");
        String setor = input.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = input.nextLine();

        return new Tecnico(cod, nome, matricula, setor, telefone);
    }

    private Tecnico atualizarTecnico(Tecnico t) {
        System.out.print("\nDigite o nome (Enter para continuar): ");
        String nome = input.nextLine();
        System.out.print("Digite o setor (Enter para continuar): ");
        String setor = input.nextLine();
        System.out.print("Digite o telefone (Enter para continuar): ");
        String telefone = input.nextLine();

        if(!nome.isEmpty()) {
            t.setNome(nome);
        }
        if(!setor.isEmpty()) {
            t.setSetor(setor);
        }
        if(!telefone.isEmpty()) {
            t.setTelefone(telefone);
        }
        return t;
    }
}
