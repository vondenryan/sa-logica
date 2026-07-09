package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import repository.EquipamentoRepository;
import repository.ManutencaoRepository;
import repository.TecnicoRepository;
import services.EquipamentoService;
import services.ManutencaoService;
import services.RelatorioService;
import services.TecnicoService;

public class MenuController {
    private final Scanner input = new Scanner(System.in);
    private final RelatorioService relatorioService = new RelatorioService();
    
    private final EquipamentoController equipamentoController;
    private final TecnicoController tecnicoController;
    private final ManutencaoController manutencaoController;

    private final EquipamentoRepository equipamentoRepository;
    private final TecnicoRepository tecnicoRepository;
    private final ManutencaoRepository manutencaoRepository;

    public MenuController(EquipamentoRepository equipamentoRepository,
            TecnicoRepository tecnicoRepository, ManutencaoRepository manutencaoRepository, TecnicoService tecnicoService, ManutencaoService manutencaoService,
            EquipamentoService equipamentoService) {
        this.tecnicoController = new TecnicoController(tecnicoService);
        this.equipamentoController = new EquipamentoController(equipamentoService);
        this.manutencaoController = new ManutencaoController(manutencaoService, equipamentoService, tecnicoService);
        this.equipamentoRepository = equipamentoRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.manutencaoRepository = manutencaoRepository;
    }

    public void startMenu() {
        boolean sair = false;

        while(!sair) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gerenciar Equipamentos");
            System.out.println("2. Gerenciar Técnicos");
            System.out.println("3. Gerenciar Manutenções");
            System.out.println("4. Gerar Relatório");
            System.out.println("0. Sair");
            System.out.print("-> ");

            try {
                int opcao = input.nextInt();
                input.nextLine();
    
                switch (opcao) {
                    case 1:
                        equipamentoController.startMenu();
                        break;
                    case 2:
                        tecnicoController.startMenu();
                        break;
                    case 3:
                        manutencaoController.startMenu();
                        break;
                    case 4:
                        relatorioService.apresentarRelatorio(manutencaoRepository, equipamentoRepository, tecnicoRepository);;
                        break;
                    case 0:
                        System.out.println("\nSaindo...");
                        sair = true;
                        break;
                    default:
                        System.out.println("\nErro: Opção inválida!");
                }
            } catch(InputMismatchException e) {
                System.out.println("\nErro: opção deve ser um número inteiro!");
                input.nextLine();
            }
        }
    }
}
